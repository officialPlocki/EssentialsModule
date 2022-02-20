package me.refluxo.essentials.commands;

import me.refluxo.moduleloader.module.ModuleCommand;
import me.refluxo.moduleloader.module.ModuleCommandExecutor;
import me.refluxo.serverlibrary.util.player.PlayerAPI;
import me.refluxo.serverlibrary.util.player.PlayerManager;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@ModuleCommand(
        command = "fly",
        aliases = {},
        usage = "/fly",
        description = "Fly command",
        permissions = {"refluxo.command.fly"},
        tabCompleterIsEnabled = false
)
public class FlyCommand extends ModuleCommandExecutor {

    /**
     * This function is called when a player uses the command /fly
     *
     * @param commandSender The player who executed the command.
     * @param command The command that was executed.
     * @param s The command name
     * @param strings The arguments that were passed to the command.
     * @return Nothing.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        if(commandSender instanceof Player player) {
            PlayerAPI api = new PlayerManager().getPlayer(player);
            if(!player.hasPermission("refluxo.command.fly")) {
                api.sendMessage(PlayerAPI.MessageType.NORMAL, ChatMessageType.CHAT, "Du hast dazu keine Rechte.", "command_no_permission");
            } else {
                player.setFlying(!player.getAllowFlight());
                api.sendMessage(PlayerAPI.MessageType.NORMAL, ChatMessageType.CHAT, "Flymode has been toggled.", "command_fly");
            }
        }
        return false;
    }

}
