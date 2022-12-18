package me.heroostech.admincommands.commands;

import me.heroostech.admincommands.AdminCommands;
import me.heroostech.citystom.utils.ChatUtil;
import me.window.next.command.Command;
import net.minestom.server.command.ConsoleSender;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.minecraft.ArgumentEntity;
import net.minestom.server.command.builder.arguments.relative.ArgumentRelativeVec3;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;

public class TeleportCommand extends Command {

    public TeleportCommand() {
        super(AdminCommands.provider, "teleport", "tp");

        ArgumentRelativeVec3 positionArg = ArgumentType.RelativeVec3("position");
        ArgumentEntity entity = ArgumentType.Entity("player").onlyPlayers(true);

        addSyntax((sender, context) -> {
            if(sender instanceof ConsoleSender) {
                sender.sendMessage(ChatUtil.format("<red>Please provide player argument</red>"));
                return;
            }

            Player player = (Player) sender;
            Pos position = context.get(positionArg).from(player.getPosition()).asPosition();

            if(!AdminCommands.provider.hasPermission(player, "teleport.position")) {
                player.sendMessage(ChatUtil.format("<red>You do not have permission to position teleport"));
                return;
            }

            player.teleport(position);
            player.sendMessage(ChatUtil.format("<green>Teleported you to </green><dark_green>" + position.x() + " " + position.y() + " " + position.z() + "</dark_green>"));
        }, positionArg);
    }
    
}
