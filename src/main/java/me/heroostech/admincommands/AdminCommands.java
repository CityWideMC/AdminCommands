package me.heroostech.admincommands;

import me.heroostech.admincommands.commands.GamemodeCommand;
import me.heroostech.admincommands.commands.OpCommand;
import me.heroostech.admincommands.commands.TeleportCommand;
import me.window.next.extension.Extension;
import me.window.permissions.PermissionProvider;

public class AdminCommands extends Extension {

    @Override
    public void initialize() {
        registerCommand(new GamemodeCommand());
        registerCommand(new TeleportCommand());
        registerCommand(new OpCommand());
    }

    @Override
    public void terminate() {}
}