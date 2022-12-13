package me.heroostech.admincommands.commands;

import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;

public class TeleportCommand extends Command {

    public TeleportCommand() {
        super("teleport", "tp");
        setCondition(this::condition);
    }

    private boolean condition(CommandSender sender, String command) {
        return true;
    }
    
}