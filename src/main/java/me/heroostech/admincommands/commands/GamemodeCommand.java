package me.heroostech.admincommands.commands;

import me.heroostech.admincommands.AdminCommands;
import me.heroostech.citystom.utils.ChatUtil;
import me.window.next.command.Command;
import net.minestom.server.command.ConsoleSender;
import net.minestom.server.command.builder.arguments.ArgumentEnum;
import net.minestom.server.command.builder.arguments.ArgumentString;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.minecraft.ArgumentEntity;
import net.minestom.server.command.builder.arguments.number.ArgumentInteger;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;

import java.util.Objects;

public class GamemodeCommand extends Command {
    public GamemodeCommand() {
        super(AdminCommands.provider, "gamemode", "gm");

        ArgumentEnum<GameMode> gamemode = ArgumentType.Enum("gamemode", GameMode.class);
        ArgumentInteger gamemodeNum = ArgumentType.Integer("gamemodenum");
        ArgumentString gamemodeStr = ArgumentType.String("gamemodestr");
        ArgumentEntity playerArg = ArgumentType.Entity("player").onlyPlayers(true);

        setDefaultExecutor((sender, context) -> sender.sendMessage(ChatUtil.format("<red>Usage: /gamemode <gamemode> [<player>]")));

        addSyntax((sender, context) -> {
            if(sender instanceof ConsoleSender) {
                sender.sendMessage(ChatUtil.format("<red>Please provide player argument</red>"));
                return;
            }
            
            Player player = (Player) sender;
            GameMode gm = context.get(gamemode);

            if(!AdminCommands.provider.hasPermission(player, "gamemode." + gm.id())) {
                player.sendMessage(ChatUtil.format("<red>You do not have permission to gamemode " + gm));
                return;
            }

            player.setGameMode(gm);
            player.sendMessage(ChatUtil.format("<green>Your gamemode has been set to </green><dark_green>" + gm + "</dark_green>"));
        }, gamemode);

        addSyntax((sender, context) -> {
            Player player = context.get(playerArg).findFirstPlayer(sender);
            if(player == null) {
                sender.sendMessage(ChatUtil.format("<red>Cannot find player </red><dark_red>" + context.get(playerArg)));
                return;
            }
            if(!(sender instanceof ConsoleSender || AdminCommands.provider.hasPermission((Player) sender, "gamemode.others") || ((Player) sender).getUsername().equals(player.getUsername()))) {
                sender.sendMessage(ChatUtil.format("<red>You do not have permission to set the gamemode of other players</red>"));
                return;
            }

            GameMode gm = context.get(gamemode);
                
            if(!AdminCommands.provider.hasPermission(player, "gamemode." + gm.id())) {
                player.sendMessage(ChatUtil.format("<red>You do not have permission to gamemode " + gm));
                return;
            }

            player.setGameMode(gm);
            player.sendMessage(ChatUtil.format("<green>Your gamemode has been set to </green><dark_green>" + gm + "</dark_green>"));

            if(sender instanceof ConsoleSender || !(((Player) sender).getUsername().equals(player.getUsername())))
                sender.sendMessage(ChatUtil.format("<dark_green>" + player.getUsername() +
                        "'s gamemode has been set to </green><dark_green>" + gm + "</dark_green>"));
        }, gamemode, playerArg);

        addSyntax((sender, context) -> {
            if(sender instanceof ConsoleSender) {
                sender.sendMessage(ChatUtil.format("<red>Please provide player argument</red>"));
                return;
            }

            Player player = (Player) sender;
            GameMode gm = GameMode.fromId(context.get(gamemodeNum));

            if(!AdminCommands.provider.hasPermission(player, "gamemode." + gm.id())) {
                player.sendMessage(ChatUtil.format("<red>You do not have permission to gamemode " + gm));
                return;
            }

            player.setGameMode(gm);
            player.sendMessage(ChatUtil.format("<green>Your gamemode has been set to </green><dark_green>" + gm + "</dark_green>"));
        }, gamemodeNum);

        addSyntax((sender, context) -> {
            Player player = context.get(playerArg).findFirstPlayer(sender);
            if(player == null) {
                sender.sendMessage(ChatUtil.format("<red>Cannot find player </red><dark_red>" + context.get(playerArg)));
                return;
            }
            if(!(sender instanceof ConsoleSender || AdminCommands.provider.hasPermission((Player) sender, "gamemode.others") || ((Player) sender).getUsername().equals(player.getUsername()))) {
                sender.sendMessage(ChatUtil.format("<red>You do not have permission to set the gamemode of other players</red>"));
                return;
            }

            GameMode gm = GameMode.fromId(context.get(gamemodeNum));

            if(!AdminCommands.provider.hasPermission(player, "gamemode." + gm.id())) {
                player.sendMessage(ChatUtil.format("<red>You do not have permission to gamemode " + gm));
                return;
            }

            player.setGameMode(gm);
            player.sendMessage(ChatUtil.format("<green>Your gamemode has been set to </green><dark_green>" + gm + "</dark_green>"));
            if(sender instanceof ConsoleSender || !(((Player) sender).getUsername().equals(player.getUsername())))
                sender.sendMessage(ChatUtil.format("<dark_green>" + player.getUsername() +
                        "'s gamemode has been set to </green><dark_green>" + gm + "</dark_green>"));
        }, gamemodeNum, playerArg);

        addSyntax((sender, context) -> {
            if(sender instanceof ConsoleSender) {
                sender.sendMessage(ChatUtil.format("<red>Please provide player argument</red>"));
                return;
            }

            GameMode gm;

            Player player = (Player) sender;
            switch (context.get(gamemodeStr)) {
                case "s" -> gm = GameMode.SURVIVAL;
                case "c" -> gm = GameMode.CREATIVE;
                case "a" -> gm = GameMode.ADVENTURE;
                case "sp" -> gm = GameMode.SPECTATOR;
                default -> {
                    sender.sendMessage(ChatUtil.format("<red>Unknown gamemode</red>"));
                    return;
                }
            }

            if(!AdminCommands.provider.hasPermission(player, "gamemode." + gm.id())) {
                player.sendMessage(ChatUtil.format("<red>You do not have permission to gamemode " + gm));
                return;
            }

            player.setGameMode(gm);
            player.sendMessage(ChatUtil.format("<green>Your gamemode has been set to </green><dark_green>" + gm + "</dark_green>"));
        }, gamemodeStr);

        addSyntax((sender, context) -> {
            Player player = context.get(playerArg).findFirstPlayer(sender);
            if(player == null) {
                sender.sendMessage(ChatUtil.format("<red>Cannot find player </red><dark_red>" + context.get(playerArg)));
                return;
            }
            if(!(sender instanceof ConsoleSender || AdminCommands.provider.hasPermission((Player) sender, "gamemode.others") || Objects.equals(((Player) sender).getUsername(), player.getUsername()))) {
                sender.sendMessage(ChatUtil.format("<red>You do not have permission to set the gamemode of other players</red>"));
                return;
            }

            GameMode gm;
            switch (context.get(gamemodeStr)) {
                case "s" -> gm = GameMode.SURVIVAL;
                case "c" -> gm = GameMode.CREATIVE;
                case "a" -> gm = GameMode.ADVENTURE;
                case "sp" -> gm = GameMode.SPECTATOR;
                default -> {
                    sender.sendMessage(ChatUtil.format("<red>Unknown gamemode</red>"));
                    return;
                }
            }

            if(!AdminCommands.provider.hasPermission(player, "gamemode." + gm.id())) {
                player.sendMessage(ChatUtil.format("<red>You do not have permission to gamemode " + gm));
                return;
            }

            player.setGameMode(gm);
            player.sendMessage(ChatUtil.format("<green>Your gamemode has been set to </green><dark_green>" + gm + "</dark_green>"));
            if(sender instanceof ConsoleSender || !(((Player) sender).getUsername().equals(player.getUsername())))
                sender.sendMessage(ChatUtil.format("<dark_green>" + player.getUsername() +
                        "'s gamemode has been set to </green><dark_green>" + gm + "</dark_green>"));
        }, gamemodeStr, playerArg);

        setCondition((sender, commandString) -> sender instanceof ConsoleSender || AdminCommands.provider.hasPermission((Player) sender, "gamemode"));
    }
}
