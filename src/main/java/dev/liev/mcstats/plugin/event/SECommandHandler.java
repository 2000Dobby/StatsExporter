package dev.liev.mcstats.plugin.event;

import dev.liev.mcstats.plugin.StatsExporter;
import dev.liev.mcstats.plugin.config.Config;
import dev.liev.mcstats.webserver.StatsServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SECommandHandler implements CommandExecutor {
    private final StatsServer server;
    private final StatsExporter main;


    public SECommandHandler(StatsServer server, StatsExporter main) {
        this.server = server;
        this.main = main;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage("Only players or the console may run this command");
            return true;
        }

        if (args.length == 1) {
            switch (args[0]) {
                case "start" -> startServer(sender);
                case "stop" -> stopServer(sender);
                default -> { return false; }
            }

            return true;
        }

        return false;
    }

    private void startServer(CommandSender sender) {
        if (server.isRunning()) {
            senderMessage(sender, "Web API is already running", false);
        } else if (server.start()) {
            senderMessage(sender, "Web API is now running and accessible under " + Config.HOSTNAME + ":" + Config.PORT, true);
        }
    }

    private void stopServer(CommandSender sender) {
        if (!server.isRunning()) {
            sender.sendMessage();
            senderMessage(sender, "Web API is already offline", false);
        } else if (server.stop()) {
            senderMessage(sender, "Web API is now no longer running", true);
        }
    }

    private void senderMessage(CommandSender sender, String message, boolean consoleLog) {
        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.AQUA + message);

            if (consoleLog) {
                main.getLogger().info(message);
            }
        } else if (sender instanceof ConsoleCommandSender) {
            if (consoleLog) {
                main.getLogger().info(message);
            } else {
                Bukkit.getLogger().info(message);
            }
        }
    }

}
