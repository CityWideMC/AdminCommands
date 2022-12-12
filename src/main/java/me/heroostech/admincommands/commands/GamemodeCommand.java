package me.heroostech.admincommands.commands;

import me.heroostech.admincommands.AdminCommands;
import me.heroostech.citystom.utils.ChatUtil;
import net.minestom.server.command.ConsoleSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;

public class GamemodeCommand extends Command {
    public GamemodeCommand() {
        super("gamemode", "gm");

        var gamemode = ArgumentType.Integer("gamemode");
        var playerArg = ArgumentType.Entity("player").onlyPlayers(true);

        setDefaultExecutor((sender, context) -> {
            sender.sendMessage(ChatUtil.format("<red>Usage: /gamemode <gamemode> [<player>]"));
        });

        addSyntax((sender, context) -> {
            if(sender instanceof ConsoleSender) {
                sender.sendMessage(ChatUtil.format("<red>Please provide player argument</red>"));
                return;
            }
            
            var player = (Player) sender;
            var gmNum = context.get(gamemode);

            if (gmNum < 0 || gmNum > 3) return;
                
            if(!AdminCommands.provider.hasExtensionPermission(player, "gamemode." + gmNum)) {
                player.sendMessage(ChatUtil.format("<red>You do have permission to gamemode " + gmNum));
            }

            player.setGameMode(GameMode.fromId(gmNum));
        }, gamemode);

        addSyntax((sender, context) -> {
            if(sender instanceof ConsoleSender || AdminCommands.provider.hasExtensionPermission((Player) sender, "gamemode.others")) {}
            else {
                sender.sendMessage(ChatUtil.format("<red>You do not have permission to set the gamemode of other players</red>"));
                return;
            }
            
            var player = context.get(playerArg).findFirstPlayer(sender);
            assert player != null;
            var gmNum = context.get(gamemode);

            if (gmNum < 0 || gmNum > 3) return;
                
            if(!AdminCommands.provider.hasExtensionPermission(player, "gamemode." + gmNum)) {
                player.sendMessage(ChatUtil.format("<red>You do not have permission to gamemode " + gmNum));
            }

            player.setGameMode(GameMode.fromId(gmNum));
        }, gamemode, playerArg);

        setCondition((sender, commandString) -> sender instanceof ConsoleSender || AdminCommands.provider.hasExtensionPermission((Player) sender, "gamemode"));
    }
}
