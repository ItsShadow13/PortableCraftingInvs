package com.itsschatten.portablecrafting.configs;

import com.itsschatten.libs.Utils;
import com.itsschatten.libs.configutils.SimpleConfig;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Sets the messages to be used throughout the plugin.
 */
public class Messages extends SimpleConfig {

    public static String
            PREFIX,
            NO_PERMS,
            HELP_MESSAGE,
            RELOAD_MESSAGE,
            RELOAD_SPECIFIC,
            WRONG_ARGS,
            TOOMANY_ARGS,
            NOTENOUGH_ARGS,
            FEATURE_DISABLED,
            DOESENT_EXIST,
            PLAYER_DOSENT_EXIST,
            UPDATE_AVALIABLE_MESSAGE,
            OPENED_ENDERCHEST,
            OPEN_TARGET_ECHEST,
            OPENED_CRAFTING,
            OPENED_CRAFTING_OTHER,
            OPENED_ANVIL,
            OPENED_ANVIL_OTHER,
            OPENED_ENCHANTTABLE,
            OPENED_ENCHANTTABLE_OTHER,
            OPENED_GRINDSTONE,
            OPENED_GRINDSTONE_OTHER,
            OPENED_LOOM,
            OPENED_LOOM_OTHER,
            OPENED_CARTOGRAPHY,
            OPENED_CARTOGRAPHY_OTHER,
            OPENED_STONECUTTER,
            OPENED_STONECUTTER_OTHER,
            CANT_RETRIEVE_ITEM_FROM_ENDER;
    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    private static Messages instance;

    private Messages(String fileName) {
        super(fileName);

        setHeader(new String[]{
                "--------------------------------------------------------",
                " This configuration file has been automatically updated!",
                "",
                " Unfortunately, due to the way Bukkit saves .yml files,",
                " all comments in your file where lost. To read them,",
                " please open " + fileName + " directly to browse the default values.",
                " Don't know how to do this? You can also check our github",
                " page for the default file.",
                "(https://bitbucket.org/itsschatten/portablecraftinginvs/)",
                "--------------------------------------------------------"});

        setInstance(this);
    }

    public static void init() {
        new Messages("messages.yml").onLoad();
        Utils.setPrefix(PREFIX);
        Utils.setUpdateAvailableMessage(Messages.UPDATE_AVALIABLE_MESSAGE);
        Utils.setNoPermsMessage(NO_PERMS);
        Utils.debugLog(Settings.DEBUG, "Loaded the file messages.yml");
    }

    private void onLoad() {
        PREFIX = getString("prefix");
        NO_PERMS = getString("no-perms");
        HELP_MESSAGE = (String) get("help-message");
        RELOAD_MESSAGE = getString("reload-message");
        RELOAD_SPECIFIC = getString("reload-message-specific");
        WRONG_ARGS = getString("wrong-args");
        TOOMANY_ARGS = getString("too-many-args");
        NOTENOUGH_ARGS = getString("not-enough-args");
        DOESENT_EXIST = getString("doesnt-exist");

        PLAYER_DOSENT_EXIST = getString("player-doesnt-exist");

        FEATURE_DISABLED = getString("feature-disabled");

        UPDATE_AVALIABLE_MESSAGE = (String) get("update-available");

        OPENED_ENDERCHEST = getString("opened-enderchest");
        OPEN_TARGET_ECHEST = getString("open-target-enderchest");
        OPENED_CRAFTING = getString("opened-craftingtable");
        OPENED_CRAFTING_OTHER = getString("opened-craftingtable-other");
        OPENED_ANVIL = getString("opened-anvil");
        OPENED_ANVIL_OTHER = getString("opened-anvil-other");
        OPENED_ENCHANTTABLE = getString("opened-enchanttable");
        OPENED_ENCHANTTABLE_OTHER = getString("opened-enchanttable-other");

        CANT_RETRIEVE_ITEM_FROM_ENDER = getString("cant-retrieve-from-enderchest");


        OPENED_CARTOGRAPHY = getString("opened-cartography");
        OPENED_CARTOGRAPHY_OTHER = getString("opened-cartography-other");


        OPENED_GRINDSTONE = getString("opened-grindstone");
        OPENED_GRINDSTONE_OTHER = getString("opened-grindstone-other");


        OPENED_LOOM = getString("opened-loom");
        OPENED_LOOM_OTHER = getString("opened-loom-other");


        OPENED_STONECUTTER = getString("opened-stonecutter");
        OPENED_STONECUTTER_OTHER = getString("opened-stonecutter-other");

    }

    public void reload() {
        setInstance(null);

        init();
        Utils.debugLog(Settings.DEBUG, "Reloaded the messages.yml file.");
    }


}
