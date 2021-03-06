package com.itsschatten.portablecrafting.configs;

import com.itsschatten.libs.Utils;
import com.itsschatten.libs.configutils.SimpleConfig;
import com.itsschatten.portablecrafting.PortableCraftingInvsPlugin;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Sets values to be used throughout the plugin.
 */
public class Settings extends SimpleConfig {

    public static boolean
            SILENT_START_UP,
            DEBUG,
            USE_MYSQL,
            USE_PERMISSIONS,
            USE_UPDATER,
            USE_METRICS,
            USE_MESSAGES,
            USE_TOO_MANY_ARGS,
            ALLOW_ESSENTIALS,
            USE_HELP_IF_WRONG_ARGS,
            USE_RANDOM_SOUND_PITCH,
            USE_SIGNS,
            REQUIRE_SIGHT_CLICK_BREAK_SIGN,

    // Feature booleans.
    USE_ENDERCHEST,
            USE_ENDERCHEST_RESTRICTION,
            USE_OLD_ENDERCHEST,
            USE_ENCHANT_TABLE,
            USE_ENCHANT_MAX_LEVEL_ARGUMENT,
            USE_CRAFTING,
            USE_ANVIL,
            USE_GRINDSTONE,
            USE_LOOM,
            USE_CARTOGRAPHY,
            USE_STONE_CUTTER,
            USE_SMITHING_TABLE,
            USE_FURNACE,
            USE_BLAST_FURNACE,
            USE_SMOKER,
            USE_BREWING,

    // Sound booleans.
    USE_ANVIL_SOUNDS,
            USE_CRAFTING_SOUNDS,
            USE_ENCHANT_TABLE_SOUNDS,
            USE_ENDERCHEST_SOUNDS,
            USE_GRINDSTONE_SOUNDS,
            USE_LOOM_SOUNDS,
            USE_CARTOGRAPHY_SOUNDS,
            USE_STONE_CUTTER_SOUNDS,
            USE_SMITHING_TABLE_SOUNDS,

    // Sign booleans.
    USE_ANVIL_SIGN,
            USE_CARTOGRAPHY_SIGN,
            USE_CRAFTING_SIGN,
            USE_ENDERCHEST_SIGN,
            USE_ENCHANT_TABLE_SIGN,
            USE_GRINDSTONE_SIGN,
            USE_LOOM_SIGN,
            USE_STONE_CUTTER_SIGN,
            USE_SMITHING_SIGN;

    // The interval in which we should check for an update for the plugin.
    public static int UPDATE_CHECK_INTERVAL,
    // The max level of enchantment that should be allowed in Enchantment tables.
    ENCHANT_MAX_LEVEL;


    public static String
            // MYSQL Stuff.
            MYSQL_USER,
            MYSQL_HOST,
            MYSQL_PASS,
            MYSQL_DATABASE,
    // The sounds that are played when opening a specific inventory.
    ENDER_CHEST_OPEN_SOUND,
            ENCHANT_TABLE_OPEN_SOUND,
            CRAFTING_OPEN_SOUND,
            ANVIL_OPEN_SOUND,
            GRINDSTONE_OPEN_SOUND,
            LOOM_OPEN_SOUND,
            CARTOGRAPHY_OPEN_SOUND,
            STONE_CUTTER_OPEN_SOUND,
            SMITHING_TABLE_OPEN_SOUND;

    public static int MYSQL_PORT;

    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    private static Settings instance;

    public Settings(String fileName) {
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
                "(https://github.com/itsschatten/portablecraftinginvs/)",
                "--------------------------------------------------------"});

        setInstance(this);
    }

    public static void init() {
        new Settings("settings.yml").onLoad();
        Utils.debugLog(DEBUG, "Loaded the settings.yml file.");
    }

    public static void setUseMysql(boolean useMysql) {
        USE_MYSQL = useMysql;
    }

    private void onLoad() {
        /* Features */
        SILENT_START_UP = (boolean) get("silent-start");
        DEBUG = (boolean) get("debug");
        UPDATE_CHECK_INTERVAL = getInt("update-check-interval");
        USE_PERMISSIONS = (boolean) get("use-permissions");
        ALLOW_ESSENTIALS = (boolean) get("allow-essentials");
        USE_MESSAGES = (boolean) get("use-messages");

        USE_MYSQL = (boolean) get("use-sql");

        MYSQL_HOST = getString("sql-host");
        MYSQL_DATABASE = getString("sql-database");
        MYSQL_USER = getString("sql-user");
        MYSQL_PASS = getString("sql-pass");
        MYSQL_PORT = getInt("sql-port");

        USE_FURNACE = (boolean) get("use-furnace");
        USE_BLAST_FURNACE = (boolean) get("use-blast-furnace");
        USE_SMOKER = (boolean) get("use-smoker");
        USE_BREWING = (boolean) get("use-brewing");

        USE_TOO_MANY_ARGS = (boolean) get("use-too-many-args");
        USE_HELP_IF_WRONG_ARGS = (boolean) get("use-help-if-no-args");
        USE_UPDATER = (boolean) get("use-updater");
        USE_METRICS = (boolean) get("metrics");
        USE_CRAFTING = (boolean) get("use-crafting");
        USE_ENDERCHEST = (boolean) get("use-enderchest");
        USE_OLD_ENDERCHEST = (boolean) get("old-enderchest");
        USE_ENCHANT_TABLE = (boolean) get("use-enchanttable");
        USE_ENCHANT_MAX_LEVEL_ARGUMENT = (boolean) get("allow-max-level-argument");
        ENCHANT_MAX_LEVEL = getInt("maximum-enchant-level");
        USE_ANVIL = (boolean) get("use-anvil");
        USE_ENDERCHEST_RESTRICTION = (boolean) get("ender-chest-restrictions");
        USE_GRINDSTONE = (boolean) get("use-grindstone");
        USE_LOOM = (boolean) get("use-loom");
        USE_CARTOGRAPHY = (boolean) get("use-cartography");
        USE_STONE_CUTTER = (boolean) get("use-stonecutter");
        USE_SMITHING_TABLE = (boolean) get("use-smithing-table");

        /* Sound Booleans */
        USE_ANVIL_SOUNDS = (boolean) get("use-anvil-sounds");
        USE_CRAFTING_SOUNDS = (boolean) get("use-crafting-sounds");
        USE_ENCHANT_TABLE_SOUNDS = (boolean) get("use-enchanttable-sounds");
        USE_ENDERCHEST_SOUNDS = (boolean) get("use-enderchest-sounds");
        USE_GRINDSTONE_SOUNDS = (boolean) get("use-grindstone-sounds");
        USE_LOOM_SOUNDS = (boolean) get("use-loom-sounds");
        USE_CARTOGRAPHY_SOUNDS = (boolean) get("use-cartography-sounds");
        USE_STONE_CUTTER_SOUNDS = (boolean) get("use-stonecutter-sounds");
        USE_SMITHING_TABLE_SOUNDS = (boolean) get("use-smithing-table-sounds");

        /* Sounds */
        ENDER_CHEST_OPEN_SOUND = getString("enderchest-open-sound");
        ENCHANT_TABLE_OPEN_SOUND = getString("enchanttable-open-sound");
        CRAFTING_OPEN_SOUND = getString("crafting-open-sound");
        ANVIL_OPEN_SOUND = getString("anvil-open-sound");
        GRINDSTONE_OPEN_SOUND = getString("grindstone-open-sound");
        LOOM_OPEN_SOUND = getString("loom-open-sound");
        CARTOGRAPHY_OPEN_SOUND = getString("cartography-open-sound");
        STONE_CUTTER_OPEN_SOUND = getString("stonecutter-open-sound");
        SMITHING_TABLE_OPEN_SOUND = getString("smithing-open-sound");

        USE_RANDOM_SOUND_PITCH = (boolean) get("use-random-sound-pitch");

        /* Sign Stuff */
        USE_SIGNS = (boolean) get("use-signs");
        REQUIRE_SIGHT_CLICK_BREAK_SIGN = (boolean) get("require-shift-click-to-break-signs");

        USE_ANVIL_SIGN = (boolean) get("use-anvil-sign");
        USE_CARTOGRAPHY_SIGN = (boolean) get("use-cartography-sign");
        USE_CRAFTING_SIGN = (boolean) get("use-crafting-sign");
        USE_ENDERCHEST_SIGN = (boolean) get("use-enderchest-sign");
        USE_ENCHANT_TABLE_SIGN = getBoolean("use-enchanttable-sign");
        USE_GRINDSTONE_SIGN = (boolean) get("use-grindstone-sign");
        USE_LOOM_SIGN = (boolean) get("use-loom-sign");
        USE_STONE_CUTTER_SIGN = (boolean) get("use-stonecutter-sign");
        USE_SMITHING_SIGN = (boolean) get("use-smithingtable-sign");
    }

    public void reload() {
        setInstance(null);

        init();
        PortableCraftingInvsPlugin.getFakeContainers().setDebug(Settings.DEBUG);

        if (!USE_MYSQL && (PortableCraftingInvsPlugin.getDatabase() != null && PortableCraftingInvsPlugin.getDatabase().mysql.getConnection() != null)) {
            PortableCraftingInvsPlugin.getDatabase().mysql.close();
            PortableCraftingInvsPlugin.setDatabase(null);
            PortableCraftingInvsPlugin.getFakeContainers().setUsingMysql(Settings.USE_MYSQL);
        }

        Utils.debugLog(Settings.DEBUG, "Reloaded the settings.yml file.");
    }

}
