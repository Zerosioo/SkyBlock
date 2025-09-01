package me.zerosio.schematic;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class FastSchematicManager {

    private static final File FOLDER = new File("plugins/SkyBlock/schematics");

    static {
        if (!FOLDER.exists()) FOLDER.mkdirs();
    }

    public static void saveSchematic(String name, Location pos1, Location pos2) throws IOException {
        CompletableFuture.runAsync(() -> {
            try {
                File file = new File(FOLDER, name + ".zerosio");

                int minX = Math.min(pos1.getBlockX(), pos2.getBlockX());
                int minY = Math.min(pos1.getBlockY(), pos2.getBlockY());
                int minZ = Math.min(pos1.getBlockZ(), pos2.getBlockZ());

                int maxX = Math.max(pos1.getBlockX(), pos2.getBlockX());
                int maxY = Math.max(pos1.getBlockY(), pos2.getBlockY());
                int maxZ = Math.max(pos1.getBlockZ(), pos2.getBlockZ());

                List<SchematicBlock> blocks = new ArrayList<>();
                World world = pos1.getWorld();

                for (int x = minX; x <= maxX; x++) {
                    for (int y = minY; y <= maxY; y++) {
                        for (int z = minZ; z <= maxZ; z++) {
                            org.bukkit.block.Block b = world.getBlockAt(x, y, z);
                            int id = b.getTypeId();
                            if (id == 0) continue;
                            byte data = b.getData();
                            blocks.add(new SchematicBlock(x - minX, y - minY, z - minZ, id, data));
                        }
                    }
                }

                try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
                    out.writeInt(maxX - minX + 1);
                    out.writeInt(maxY - minY + 1);
                    out.writeInt(maxZ - minZ + 1);
                    out.writeInt(blocks.size());
                    for (SchematicBlock block : blocks) {
                        out.writeShort(block.x);
                        out.writeShort(block.y);
                        out.writeShort(block.z);
                        out.writeInt(block.typeId);
                        out.writeByte(block.data);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void pasteSchematic(String name, Location pasteAt, int rotation) throws IOException {
        File file = new File(FOLDER, name + ".zerosio");
        if (!file.exists()) throw new FileNotFoundException("Missing schematic: " + name);

        int baseX = pasteAt.getBlockX();
        int baseY = pasteAt.getBlockY();
        int baseZ = pasteAt.getBlockZ();

        CompletableFuture.runAsync(() -> {
            int sizeX, sizeY, sizeZ;
            List<SchematicBlock> blocks = new ArrayList<>();

            try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
                sizeX = in.readInt();
                sizeY = in.readInt();
                sizeZ = in.readInt();
                int count = in.readInt();

                for (int i = 0; i < count; i++) {
                    int dx = in.readShort();
                    int dy = in.readShort();
                    int dz = in.readShort();
                    int typeId = in.readInt();
                    byte data = in.readByte();
                    blocks.add(new SchematicBlock(dx, dy, dz, typeId, data));
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugins()[0], () -> {
                World world = pasteAt.getWorld();

                Set<String> forcedChunks = new HashSet<>();
                for (SchematicBlock block : blocks) {
                    int[] rotated = rotate(block.x, block.z, sizeX, sizeZ, rotation);
                    int finalX = baseX + rotated[0];
                    int finalZ = baseZ + rotated[1];
                    int chunkX = finalX >> 4;
                    int chunkZ = finalZ >> 4;
                    String key = chunkX + "," + chunkZ;
                    if (forcedChunks.add(key)) {
                        world.getChunkAt(chunkX, chunkZ);
                    }
                }

                WorldServer nmsWorld = ((CraftWorld) world).getHandle();

                for (SchematicBlock block : blocks) {
                    int[] rotated = rotate(block.x, block.z, sizeX, sizeZ, rotation);
                    int finalX = baseX + rotated[0];
                    int finalZ = baseZ + rotated[1];
                    int finalY = baseY + block.y;

                    if (finalY < 0 || finalY > 255) continue;

                    BlockPosition pos = new BlockPosition(finalX, finalY, finalZ);
                    Chunk chunk = nmsWorld.getChunkAtWorldCoords(pos);
                    ChunkSection section = chunk.getSections()[finalY >> 4];

                    if (section == null) {
                        section = new ChunkSection((finalY >> 4) << 4, true);
                        chunk.getSections()[finalY >> 4] = section;
                    }

                    Block nmsBlock = Block.getById(block.typeId);
                    if (nmsBlock == null) continue;

                    IBlockData blockData = nmsBlock.fromLegacyData(block.data);
                    section.setType(finalX & 15, finalY & 15, finalZ & 15, blockData);
                }

                int cx1 = baseX >> 4;
                int cz1 = baseZ >> 4;
                int cx2 = (baseX + sizeX) >> 4;
                int cz2 = (baseZ + sizeZ) >> 4;

                for (Player player : world.getPlayers()) {
                    EntityPlayer nmsPlayer = ((CraftPlayer) player).getHandle();
                    for (int cx = cx1; cx <= cx2; cx++) {
                        for (int cz = cz1; cz <= cz2; cz++) {
                            PacketPlayOutMapChunk packet = new PacketPlayOutMapChunk(nmsWorld.getChunkAt(cx, cz), true, 65535);
                            nmsPlayer.playerConnection.sendPacket(packet);
                        }
                    }
                }
            });
        });
    }

    private static int[] rotate(int x, int z, int sizeX, int sizeZ, int rotation) {
        switch ((rotation % 360 + 360) % 360) {
            case 90:
                return new int[]{sizeZ - 1 - z, x};
            case 180:
                return new int[]{sizeX - 1 - x, sizeZ - 1 - z};
            case 270:
                return new int[]{z, sizeX - 1 - x};
            default:
                return new int[]{x, z};
        }
    }

    private static class SchematicBlock {
        int x, y, z;
        int typeId;
        byte data;

        public SchematicBlock(int x, int y, int z, int typeId, byte data) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.typeId = typeId;
            this.data = data;
        }
    }
}