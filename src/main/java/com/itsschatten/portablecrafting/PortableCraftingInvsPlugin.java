package com.itsschatten.portablecrafting;

import com.itsschatten.libs.UpdateNotifications;
import com.itsschatten.libs.Utils;
import com.itsschatten.portablecrafting.commands.*;
import com.itsschatten.portablecrafting.configs.Messages;
import com.itsschatten.portablecrafting.configs.Settings;
import com.itsschatten.portablecrafting.listeners.EnderchestListener;
import com.itsschatten.portablecrafting.listeners.PlayerJoinListener;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bstats.bukkit.Metrics;
import org.bukkit.command.Command;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class PortableCraftingInvsPlugin extends JavaPlugin {


    // GRINDSTONE, CARTOGRAPHY, LOOM, AND STONECUTTER

    private static PluginDescriptionFile pdf; // So we can access the pdf in this file.

    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    private static PortableCraftingInvsPlugin instance; // The instance stuffs.

    @Override
    public void onEnable() { // We all know what this does right?
        Utils.setInstance(this);
        setInstance(this);

        pdf = this.getDescription();

        Utils.log("",
                "&9+---------------------------------------------------+ ",
                "",
                "&9 _____   _____ _____ ",
                "&9|  __ \\ / ____|_   _|",
                "&3| |__) | |      | |  ",
                "&3|  ___/| |      | |  ",
                "&b| |    | |____ _| |_ ",
                "&b|_|     \\_____|_____|",
                "",
                "&7Developed by " + String.join(",", pdf.getAuthors()),
                "&7Version " + pdf.getVersion());

        // Register configs.
        Settings.init();
        Messages.init();

        if (Settings.USE_METRICS) {
            Utils.log("&7Metrics are enabled! You can see the information collect at the following link: &chttps://bstats.org/plugin/bukkit/Portable%20Crafting%20Inventories&7", "If you don't wish for this information to be collected you can disable it in the settings.yml.");
            new Metrics(this);
        }

        if (Settings.USE_UPDATER) {
            new UpdateNotifications(61045) {
                @Override
                public void onUpdateAvailable() {
                    Utils.log("There is an update available for the plugin! Current Version " + pdf.getVersion() + " New Version " + getLatestVersion() + " {https://spigotmc.org/resources/" + getProjectId() + ")");
                }
            }.runTaskAsynchronously(this);

            new CheckForUpdate().runTaskTimerAsynchronously(this, 30 * 60 * 20, 30 * 60 * 20); // Wait 30 minutes and check for another update.
            Utils.debugLog(Settings.DEBUG, "Checked for update, and set timer running.");
        }

        if (Settings.USE_ENDERCHEST_RESTRICTION) {
            this.getServer().getPluginManager().registerEvents(new EnderchestListener(), this);
            Utils.debugLog(Settings.DEBUG, "USE_ENDERCHEST_RESTRICTIONS is true; EnderchestListener has been initialized.");
        }

        // Register commands, and JoinListener.
        registerCommands(new AnvilCommand(), new CraftCommand(), new EnchanttableCommand(), new EnderChestCommand(), new PortableCraftingInvsCommand(), new GrindStoneCommand(), new LoomCommand(), new StoneCutterCommand(), new CartographyCommand());
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Utils.debugLog(Settings.DEBUG, "Loaded the PlayerJoinListener.");


        Utils.log("&9+---------------------------------------------------+ ",
                "");
    }

    @Override
    public void onDisable() { // Remove the instance of the plugin, to help prevent memory leaks, and set it to null in the Utils so we can get a new instance of it when it's reloaded.
        setInstance(null);
        Utils.setInstance(null);
    }


    // A utility method so I can register commands on one line.
    private void registerCommands(Command... commands) {
        try {
            for (Command command : commands) {
                Utils.registerCommand(command);
                Utils.debugLog(Settings.DEBUG, "&7Command " + command.getName() + " has been registered.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Utils.log("Some error occurred, please report this immediately to ItsSchatten on Spigot or Github. \n(https://github.com/ItsSchatten/PortableCraftingInvs/issues)");
        }
    }


}
