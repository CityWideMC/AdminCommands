package me.heroostech.admincommands;

import me.heroostech.admincommands.commands.GamemodeCommand;
import me.heroostech.citystom.Extension;
import me.window.permissions.PermissionProvider;

public class AdminCommands extends Extension {

    public static PermissionProvider provider;

    @Override
    public void initialize() {
        provider = new PermissionProvider(3, "admincommands");

        registerCommand(new GamemodeCommand());
    }

    @Override
    public void terminate() {

    }
}