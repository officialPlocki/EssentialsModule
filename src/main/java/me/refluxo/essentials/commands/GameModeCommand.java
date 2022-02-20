package me.refluxo.essentials.commands;

import me.refluxo.moduleloader.module.ModuleCommand;
import me.refluxo.moduleloader.module.ModuleCommandExecutor;
import me.refluxo.serverlibrary.util.player.PlayerAPI;
import me.refluxo.serverlibrary.util.player.PlayerManager;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@ModuleCommand(
        command = "gamemode",
        aliases = {"gm"},
        permissions = {"refluxo.command.gamemode", "refluxo.command.gamemode.other"},
        description = "GameMode command",
        usage = "/gamemode <0, 1, 2, 3>",
        tabCompleterIsEnabled = true
)
public class GameModeCommand extends ModuleCommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        if(commandSender instanceof Player player) {
            PlayerAPI api = new PlayerManager().getPlayer(player);
            if(!player.hasPermission("refluxo.command.gamemode")) {
                api.sendMessage(PlayerAPI.MessageType.NORMAL, ChatMessageType.CHAT, "Du hast dazu keine Rechte.", "command_no_permission");
            } else {
                if(strings.length == 0) {
                    api.sendMessage(PlayerAPI.MessageType.NORMAL, ChatMessageType.CHAT, "Bitte verwende /%p <0, 1, 2, 3> <Spieler>", "command_gamemode_help", "gamemode");
                } else if(strings.length == 1) {
                    if(strings[0].equalsIgnoreCase("0") || strings[0].equalsIgnoreCase("survival")) {
                        api.sendMessage(PlayerAPI.MessageType.NORMAL, ChatMessageType.CHAT, "Dein Spielmodus wurde auf Überlebensmodus gesetzt.", "command_gamemode_survival");
                        player.setGameMode(GameMode.SURVIVAL);
                    } else if(strings[0].equalsIgnoreCase("1") || strings[0].equalsIgnoreCase("creative")) {
                        api.sendMessage(PlayerAPI.MessageType.NORMAL, ChatMessageType.CHAT, "Dein Spielmodus wurde auf Kreativ gesetzt.", "command_gamemode_survival");
                        player.setGameMode(GameMode.SURVIVAL);
                    } else if(strings[0].equalsIgnoreCase("2") || strings[0].equalsIgnoreCase("adventure")) {
                        api.sendMessage(PlayerAPI.MessageType.NORMAL, ChatMessageType.CHAT, "Dein Spielmodus wurde auf Abenteuermodus gesetzt.", "command_gamemode_survival");
                        player.setGameMode(GameMode.SURVIVAL);
                    } else if(strings[0].equalsIgnoreCase("3") || strings[0].equalsIgnoreCase("spectator")) {
                        api.sendMessage(PlayerAPI.MessageType.NORMAL, ChatMessageType.CHAT, "Dein Spielmodus wurde auf Zuschauermodus gesetzt.", "command_gamemode_survival");
                        player.setGameMode(GameMode.SURVIVAL);
                    } else {
                        api.sendMessage(PlayerAPI.MessageType.NORMAL, ChatMessageType.CHAT, "Bitte verwende /%p <0, 1, 2, 3> <Spieler>", "command_gamemode_help", "gamemode");
                    }
                } else if(s.length() == 2) {
                    if(player.hasPermission("refluxo.command.gamemode.other")) {
                        Player other = Bukkit.getPlayer(strings[1]);
                        if(strings[0].equalsIgnoreCase("0") || strings[0].equalsIgnoreCase("survival")) {
                            api.sendMessage(PlayerAPI.MessageType.NORMAL, ChatMessageType.CHAT, "Der Spielmodus wurde auf Überlebensmodus gesetzt.", "command_gamemode_survival_other");
                            assert other != null;
                            other.setGameMode(GameMode.SURVIVAL);
                        } else if(strings[0].equalsIgnoreCase("1") || strings[0].equalsIgnoreCase("creative")) {
                            api.sendMessage(PlayerAPI.MessageType.NORMAL, ChatMessageType.CHAT, "Der Spielmodus wurde auf Kreativ gesetzt.", "command_gamemode_survival_other");
                            assert other != null;
                            other.setGameMode(GameMode.SURVIVAL);
                        } else if(strings[0].equalsIgnoreCase("2") || strings[0].equalsIgnoreCase("adventure")) {
                            api.sendMessage(PlayerAPI.MessageType.NORMAL, ChatMessageType.CHAT, "Der Spielmodus wurde auf Abenteuermodus gesetzt.", "command_gamemode_survival_other");
                            assert other != null;
                            other.setGameMode(GameMode.SURVIVAL);
                        } else if(strings[0].equalsIgnoreCase("3") || strings[0].equalsIgnoreCase("spectator")) {
                            api.sendMessage(PlayerAPI.MessageType.NORMAL, ChatMessageType.CHAT, "Der Spielmodus wurde auf Zuschauermodus gesetzt.", "command_gamemode_survival_other");
                            assert other != null;
                            other.setGameMode(GameMode.SURVIVAL);
                        } else {
                            api.sendMessage(PlayerAPI.MessageType.NORMAL, ChatMessageType.CHAT, "Bitte verwende /%p <0, 1, 2, 3> <Spieler>", "command_gamemode_help", "gamemode");
                        }
                    }
                } else {
                    api.sendMessage(PlayerAPI.MessageType.NORMAL, ChatMessageType.CHAT, "Bitte verwende /%p <0, 1, 2, 3> <Spieler>", "command_gamemode_help", "gamemode");
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public List<String> getTabCompletions(String[] args) {
        List<String> completions = new ArrayList<>();
        if(args.length == 0) {
            completions.add("0");
            completions.add("1");
            completions.add("2");
            completions.add("3");
            completions.add("survival");
            completions.add("creative");
            completions.add("adventure");
            completions.add("spectator");
        } else if(args.length == 1) {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                completions.add(onlinePlayer.getName());
            }
        }
        return completions;
    }
}
