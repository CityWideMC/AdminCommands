package me.heroostech.admincommands.commands;

import me.heroostech.admincommands.AdminCommands;
import me.window.next.command.Command;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.ConsoleSender;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;

public class OpCommand extends Command {

    public OpCommand() {
        super(AdminCommands.provider, "op", "setpermlevel", "permlevel", "permissionlevel", "setpermissionlevel");
        setPermissionLevel(5);

        setDefaultExecutor((CommandSender sender, CommandContext context) -> sender.sendMessage(Component.text("Usage: op <player> [level]", NamedTextColor.RED)));

        var playerArg = ArgumentType.Entity("player").onlyPlayers(true);
        var levelArg = ArgumentType.Integer("level");

        addSyntax((CommandSender sender, CommandContext context) -> {
            Player player = (Player) context.get(playerArg).findFirstPlayer(sender);
            if(sender instanceof ConsoleSender || provider.hasPermission((Player) sender, "4")) player.setPermissionLevel(4);
            else if(provider.hasPermission((Player) sender, "3")) player.setPermissionLevel(3);
            else if(provider.hasPermission((Player) sender, "2")) player.setPermissionLevel(2);
            else if(provider.hasPermission((Player) sender, "1")) player.setPermissionLevel(1);
            else if(provider.hasPermission((Player) sender, "0")) player.setPermissionLevel(0);
            else {
                sender.sendMessage(Component.text("You do not have any op permissions!", NamedTextColor.RED));
                return;
            }
            sender.sendMessage(Component.text(player.getUsername() + " is now permission level " + player.getPermissionLevel(), NamedTextColor.GREEN));
            player.sendMessage(Component.text("Your op level has been set to " + player.getPermissionLevel()));

        }, playerArg);

        addSyntax((CommandSender sender, CommandContext context) -> {
            Player player = (Player) context.get(playerArg).findFirstPlayer(sender);
            if(sender instanceof ConsoleSender || provider.hasPermission((Player) sender, String.valueOf(context.get(levelArg)))) player.setPermissionLevel(context.get(levelArg));
            else {
                sender.sendMessage(Component.text("You do not have permission to this op level!", NamedTextColor.RED));
                return;
            }
            sender.sendMessage(Component.text(player.getUsername() + " is now permission level " + player.getPermissionLevel(), NamedTextColor.GREEN));
            player.sendMessage(Component.text("Your op level has been set to " + player.getPermissionLevel()));
        }, playerArg, levelArg);
    }

}