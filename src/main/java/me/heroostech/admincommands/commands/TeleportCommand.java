package me.heroostech.admincommands.commands;

import me.heroostech.admincommands.AdminCommands;
import me.window.next.command.Command;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;

public class TeleportCommand extends Command {

    public TeleportCommand() {
        super(AdminCommands.provider, "teleport", "tp");
        setPermissionLevel(3);
        var positionArg = ArgumentType.RelativeVec3("position");

        addSyntax((sender, context) -> {
            Pos position = context.get(positionArg).from(Pos.ZERO).asPosition();
        }, positionArg);
    }
    
}