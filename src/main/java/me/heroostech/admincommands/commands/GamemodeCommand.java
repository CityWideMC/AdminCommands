package me.heroostech.admincommands.commands;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;

public class GamemodeCommand extends Command {
    public GamemodeCommand() {
        super("gamemode", "gm");

        var gamemode = ArgumentType.Integer("gamemode");

        setDefaultExecutor((sender, context) -> {

        });

        addSyntax((sender, context) -> {
            var player = (Player) sender;
            var gmNum = context.get(gamemode);

            if(gmNum < 0 || gmNum > 3) return;

            player.setGameMode(GameMode.fromId(gmNum));
        }, gamemode);

        setCondition((sender, commandString) -> sender.hasPermission("admincommands.gamemode"));
    }
}
