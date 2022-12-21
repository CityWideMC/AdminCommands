package me.heroostech.admincommands;

import me.heroostech.admincommands.commands.GamemodeCommand;
import me.heroostech.admincommands.commands.TeleportCommand;
import me.window.next.extension.Extension;

public class AdminCommands extends Extension {

    @Override
    public void initialize() {
        registerCommand(new GamemodeCommand());
        registerCommand(new TeleportCommand());
    }

    @Override
    public void terminate() {}
}