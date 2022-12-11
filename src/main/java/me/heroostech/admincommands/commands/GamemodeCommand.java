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

        setDefaultExecutor((sender, context) -> {

        });

        addSyntax((sender, context) -> {
            if(sender instanceof ConsoleSender) {
                sender.sendMessage(ChatUtil.format("<red>Please provide player argument</red>"));
            } else {
                var player = (Player) sender;
                var gmNum = context.get(gamemode);

                if (gmNum < 0 || gmNum > 3) return;

                player.setGameMode(GameMode.fromId(gmNum));
            }
        }, gamemode);

        setCondition((sender, commandString) -> sender instanceof ConsoleSender || AdminCommands.provider.hasExtensionPermission((Player) sender, "gamemode"));
    }
}
