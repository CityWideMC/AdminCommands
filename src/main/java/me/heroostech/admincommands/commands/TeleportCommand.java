package me.heroostech.admincommands.commands;

import me.heroostech.admincommands.AdminCommands;
import xyz.citywide.citystom.utils.ChatUtil;
import me.window.next.command.Command;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.ConsoleSender;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.minecraft.ArgumentEntity;
import net.minestom.server.command.builder.arguments.relative.ArgumentRelativeBlockPosition;
import net.minestom.server.command.builder.arguments.relative.ArgumentRelativeVec3;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;

public class TeleportCommand extends Command {

    public TeleportCommand() {
        super(AdminCommands.provider, "teleport", "tp");

        ArgumentRelativeBlockPosition positionArg = ArgumentType.RelativeBlockPosition("position");
        ArgumentEntity playerArg = ArgumentType.Entity("player").onlyPlayers(true);
        ArgumentEntity playerArg2 = ArgumentType.Entity("player2").onlyPlayers(true);

        setDefaultExecutor((CommandSender sender, CommandContext context) -> {
            if(sender instanceof ConsoleSender) {
                sender.sendMessage(Component.text("Usage: teleport <position|player> <player to teleport>", NamedTextColor.RED));
            } else {
                sender.sendMessage(Component.text("Usage: teleport <position|player> [player to teleport]", NamedTextColor.RED));
            }
        });

        addSyntax((sender, context) -> {
            if(sender instanceof ConsoleSender) {
                sender.sendMessage(ChatUtil.format("<red>Please provide player argument</red>"));
                return;
            }

            Player player = (Player) sender;
            Pos position = context.get(positionArg).from(player.getPosition()).asPosition();

            if(!provider.hasPermission(player, "position")) {
                player.sendMessage(ChatUtil.format("<red>You do not have permission to position teleport!"));
                return;
            }

            player.teleport(position);
            player.sendMessage(ChatUtil.format("<green>Teleported you to </green><dark_green>" + position.x() + " " + position.y() + " " + position.z() + "</dark_green>"));
        }, positionArg);

        addSyntax((sender, context) -> {
            if(sender instanceof ConsoleSender) {
                sender.sendMessage(ChatUtil.format("<red>Please provide player argument</red>"));
                return;
            }

            Player player = (Player) sender;
            Pos position = context.get(positionArg).from(player.getPosition()).asPosition();

            if(!provider.hasPermission(player, "position")) {
                player.sendMessage(ChatUtil.format("<red>You do not have permission to position teleport!"));
                return;
            }

            player.teleport(position);
            player.sendMessage(ChatUtil.format("<green>Teleported you to </green><dark_green>" + position.x() + " " + position.y() + " " + position.z() + "</dark_green>"));
        }, positionArg);

        addSyntax((sender, context) -> {
            if(sender instanceof ConsoleSender) {
                sender.sendMessage(ChatUtil.format("<red>Please provide player argument</red>"));
                return;
            }

            Player player = (Player) sender;
            Pos position = context.get(playerArg).findFirstPlayer(sender).getPosition();

            if(!provider.hasPermission(player, "player")) {
                player.sendMessage(ChatUtil.format("<red>You do not have permission to player teleport!"));
                return;
            }

            player.teleport(position);
            player.sendMessage(ChatUtil.format("<green>Teleported you to </green><dark_green>" + position.x() + " " + position.y() + " " + position.z() + "</dark_green>"));
        }, playerArg);

        addSyntax((sender, context) -> {
            Player player = context.get(playerArg).findFirstPlayer(sender);
            Pos position = context.get(playerArg2).findFirstPlayer(sender).getPosition();

            if(!provider.hasPermission(player, "player")) {
                player.sendMessage(ChatUtil.format("<red>You do not have permission to player teleport!"));
                return;
            }

            if(!provider.hasPermission(player, "others") && !(sender instanceof ConsoleSender) && !player.getUuid().equals(((Player) sender).getUuid())) {
                player.sendMessage(ChatUtil.format("<red>You do not have permission to teleport other players!"));
                return;
            }

            player.teleport(position);
            player.sendMessage("You have been teleported to " + context.get(playerArg2).findFirstPlayer(sender) + " (" + position.x() + " " + position.y() + " " + position.z() + ")");
            sender.sendMessage(ChatUtil.format("<green>Teleported" + player.getUsername() + " to </green><dark_green> " +
                    context.get(playerArg2).findFirstPlayer(sender) + " (" + position.x() + " " + position.y() + " " + position.z() + ")</dark_green>"));
        }, playerArg2, playerArg);

        addSyntax((sender, context) -> {
            Player player = context.get(playerArg).findFirstPlayer(sender);
            Pos position = context.get(positionArg).from(player.getPosition()).asPosition();

            if(!provider.hasPermission(player, "position")) {
                player.sendMessage(ChatUtil.format("<red>You do not have permission to player teleport!"));
                return;
            }

            if(!provider.hasPermission(player, "others") && !(sender instanceof ConsoleSender) && !player.getUuid().equals(((Player) sender).getUuid())) {
                player.sendMessage(ChatUtil.format("<red>You do not have permission to teleport other players!"));
                return;
            }

            player.teleport(position);
            player.sendMessage("You have been teleported to " + position.x() + " " + position.y() + " " + position.z());
            sender.sendMessage(ChatUtil.format("<green>Teleported" + player.getUsername() + " to </green><dark_green> " +
                    context.get(playerArg2).findFirstPlayer(sender) + " (" + position.x() + " " + position.y() + " " + position.z() + ")</dark_green>"));
        }, positionArg, playerArg);
    }
    
}
