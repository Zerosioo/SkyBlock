package me.zerosio.commands.builder;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;

import me.zerosio.utility.Reflections;

import java.lang.reflect.Field;
import java.util.Arrays;

public class CommandManager {

    private static final String COMMAND_PACKAGE = "me.zerosio.commands";

    public static void registerCommands() {
        int registered = 0;

        for (Class<? extends CommandBase> clazz : Reflections.getSubTypesOf(COMMAND_PACKAGE)) {
            try {
                CommandBase command = clazz.getDeclaredConstructor().newInstance();
                register(command);
                registered++;
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage("§cFailed to register command: " + clazz.getSimpleName());
                e.printStackTrace();
            }
        }

        Bukkit.getConsoleSender().sendMessage("§aSuccessfully registered " + registered + " commands.");
    }

    private static void register(CommandBase command) {
        try {
            Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap map = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            Command cmd = new Command(command.getName()) {
                @Override
                public boolean execute(CommandSender sender, String label, String[] args) {
                    command.executeCommand(sender, args);
                    return true;
                }
            };
            
            if (command.getAliases() != null) {
            cmd.setAliases(command.getAliases());
            }
            
            cmd.setDescription(command.getDescription());
            cmd.setUsage(command.getUsage());

            map.register("zerosio", cmd);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Shit got fucked up");
        }
    }
}
