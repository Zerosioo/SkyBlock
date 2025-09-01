package me.zerosio.npcs.hub.merchants;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.zerosio.npcs.build.AbstractNPC;

public class Amelia extends AbstractNPC {

    public Amelia() {
        super("Amelia");
    }

    @Override
    public void onInteract(Player player) {
        if (isFirstInteraction(player)) {
            // TODO: Handle first-time interaction
        }
    }

    @Override
    public Location getInitialLocation() {
        return new Location(Bukkit.getWorld("world"), -44, 85, -4);
    }

    @Override
    public List<String> getHologramLines() {
        return Arrays.asList("§fAmelia", "§e§lCLICK");
    }

    @Override
    public String getTexture() {
        return "ewogICJ0aW1lc3RhbXAiIDogMTYxNzgxMTAyMDE0NywKICAicHJvZmlsZUlkIiA6ICJmM2IwYzEwNzcyYjg0OGE5YTQwMmJkYTVhOTg0MmJmOSIsCiAgInByb2ZpbGVOYW1lIiA6ICJaS29uaWtpc2hpIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzJkNTk1NWNjNWFmNmZhMzM5MGRmMzZjOWEyM2EzMjY0Yzg0MjY5OTFlNzUzM2FkMmQwODFmNDFlYTEzMGM2M2IiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==";
    }

    @Override
    public String getSignature() {
        return "punSOySQJ89et2TxGHDHjUDUvDwjJZVlYbnXyoK10FSfwsfR6wO8aPuTCEZMRrxb1xtIe5+d+7Ojile68jhQFXKOz32Cx+EhVuRiMh8hqJt7A42rRuE5DiFrOLIKY0l3B+ppJDMSQMPqTPTHvSKTB3XzGzNtPo14TIMhXuCbQzSu1M4Jr4PedmdEo40zVDTm1xxxPcghhU0jmRErgbGLAM0J0O99Ye28I3wwI7in2mPsdvV4PwGc+z5FaWxnmL32GlSgfElrr/tehuaJO4f3Y0fb0S7a5pPS4ax0/Ht9/ysRQGmAcARHqpZKosb4JMD8YLxDYX55SnDihn1sSiSGh8uJpqjZFm25WDpkJbtg6Itv0350P20Xtt8nLqlAWOKQ/hURN3QMV6P+IwDtEwlESi/A2t2MKUgvfOg60CL0kIhw7rwaXgPmpjN4NN3ggpuU4zZhTF7aTtfV3InhYJul11ITxyYA6t/MUb3XZmSCPfGD4h2urR50YSHvmcbkRz2jizEgsRgvxBpkKDMnZAfPIhz6yu+54MUXjx9RORwHoaMYFHG3hWojLSCiX3XO0oaNWIU8vTapArDo2eFz3mBUVygAx/Wi+ecuG4PXzLHAyJycB2oUz8JS8rikVXDfPGSp905AurELVgegRqHDufS9JFS7lZB8VqR2jeBNs7Ohxsk=";
    }
}