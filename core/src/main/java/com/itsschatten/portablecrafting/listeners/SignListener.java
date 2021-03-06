package com.itsschatten.portablecrafting.listeners;

import com.itsschatten.libs.Utils;
import com.itsschatten.portablecrafting.Permissions;
import com.itsschatten.portablecrafting.PortableCraftingInvsPlugin;
import com.itsschatten.portablecrafting.configs.Messages;
import com.itsschatten.portablecrafting.configs.Settings;
import com.itsschatten.portablecrafting.configs.SignsConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignListener implements Listener {

    @EventHandler
    public void onSign(final SignChangeEvent event) {
        if (Settings.USE_PERMISSIONS && !event.getPlayer().hasPermission(Permissions.SIGN_CREATE.getPermission()))
            return;

        if (event.getLine(0) == null) {
            return;
        }

        switch (event.getLine(0).toLowerCase()) {
            case "[anvil]": {
                if (Settings.USE_PERMISSIONS && !event.getPlayer().hasPermission(Permissions.SIGN_CREATE_ANVIL.getPermission()))
                    return;
                if (!Settings.USE_ANVIL_SIGN)
                    return;
                event.setLine(0, Utils.colorize(Messages.ANVIL_SIGN));

                makeSign(event, SignTypes.ANVIL);

                Utils.tell(event.getPlayer(), Messages.ANVIL_SIGN_CREATED);
                break;
            }

            case "[cartography]": {
                if (Settings.USE_PERMISSIONS && !event.getPlayer().hasPermission(Permissions.SIGN_CREATE_CARTOGRAPHY.getPermission()))
                    return;
                if (!Settings.USE_CARTOGRAPHY_SIGN)
                    return;
                event.setLine(0, Utils.colorize(Messages.CARTOGRAPHY_SIGN));

                makeSign(event, SignTypes.CARTOGRAPHY);

                Utils.tell(event.getPlayer(), Messages.CARTOGRAPHY_SIGN_CREATED);
                break;
            }

            case "[crafting]": {
                if (Settings.USE_PERMISSIONS && !event.getPlayer().hasPermission(Permissions.SIGN_CREATE_CRAFTING.getPermission()))
                    return;
                if (!Settings.USE_CRAFTING_SIGN)
                    return;
                event.setLine(0, Utils.colorize(Messages.CRAFTING_SIGN));

                makeSign(event, SignTypes.CRAFTING_TABLE);

                Utils.tell(event.getPlayer(), Messages.CRAFTING_SIGN_CREATED);
                break;
            }

            case "[enchanttable]": {
                if (Settings.USE_PERMISSIONS && !event.getPlayer().hasPermission(Permissions.SIGN_CREATE_ENCHANT_TABLE.getPermission()))
                    return;
                if (!Settings.USE_ENCHANT_TABLE_SIGN)
                    return;
                event.setLine(0, Utils.colorize(Messages.ENCHANT_TABLE_SIGN));

                makeSign(event, SignTypes.ENCHANTMENT_TABLE);

                Utils.tell(event.getPlayer(), Messages.ENCHANT_TABLE_SIGN_CREATED);
                break;
            }

            case "[enderchest]": {
                if (Settings.USE_PERMISSIONS && !event.getPlayer().hasPermission(Permissions.SIGN_CREATE_ENDERCHEST.getPermission()))
                    return;
                if (!Settings.USE_ENDERCHEST_SIGN)
                    return;
                event.setLine(0, Utils.colorize(Messages.ENDERCHEST_SIGN));

                makeSign(event, SignTypes.ENDER_CHEST);

                Utils.tell(event.getPlayer(), Messages.ENDERCHEST_SIGN_CREATED);
                break;
            }

            case "[grindstone]": {
                if (Settings.USE_PERMISSIONS && !event.getPlayer().hasPermission(Permissions.SIGN_CREATE_GRINDSTONE.getPermission()))
                    return;
                if (!Settings.USE_GRINDSTONE_SIGN)
                    return;
                event.setLine(0, Utils.colorize(Messages.GRINDSTONE_SIGN));

                makeSign(event, SignTypes.GRINDSTONE);

                Utils.tell(event.getPlayer(), Messages.GRINDSTONE_SIGN_CREATED);
                break;
            }

            case "[loom]": {
                if (Settings.USE_PERMISSIONS && !event.getPlayer().hasPermission(Permissions.SIGN_CREATE_LOOM.getPermission()))
                    return;
                if (!Settings.USE_LOOM_SIGN)
                    return;
                event.setLine(0, Utils.colorize(Messages.LOOM_SIGN));

                makeSign(event, SignTypes.LOOM);

                Utils.tell(event.getPlayer(), Messages.LOOM_SIGN_CREATED);
                break;
            }

            case "[stonecutter]": {
                if (Settings.USE_PERMISSIONS && !event.getPlayer().hasPermission(Permissions.SIGN_CREATE_STONE_CUTTER.getPermission()))
                    return;
                if (!Settings.USE_STONE_CUTTER_SIGN)
                    return;
                event.setLine(0, Utils.colorize(Messages.STONE_CUTTER_SIGN));

                makeSign(event, SignTypes.STONE_CUTTER);

                Utils.tell(event.getPlayer(), Messages.STONE_CUTTER_SIGN_CREATED);
                break;
            }

            case "[smithing]": {
                if (Settings.USE_PERMISSIONS && !event.getPlayer().hasPermission(Permissions.SIGN_CREATE_SMITHING.getPermission()))
                    return;
                if (!Settings.USE_SMITHING_SIGN)
                    return;
                event.setLine(0, Utils.colorize(Messages.SMITHING_TABLE_SIGN));

                makeSign(event, SignTypes.SMITHING);

                Utils.tell(event.getPlayer(), Messages.SMITHING_TABLE_SIGN_CREATED);
                break;
            }

            default: {
                break;
            }
        }
    }

    @EventHandler
    public void onSignInteract(final PlayerInteractEvent event) {

        if (isSign(event)) {
            if (!Settings.USE_SIGNS)
                return;
            final SignsConfig signsConfig = SignsConfig.getInstance();

            if (Settings.REQUIRE_SIGHT_CLICK_BREAK_SIGN && event.getAction() == Action.LEFT_CLICK_BLOCK && event.getPlayer().isSneaking()) {
                String path;

                for (String key : signsConfig.getConfigurationSection("signs").getKeys(false)) {
                    if (signsConfig.get("signs." + key + ".where.world") == event.getPlayer().getLocation().getWorld().getName()
                            && signsConfig.getInt("signs." + key + ".where.x") == event.getClickedBlock().getX()
                            && signsConfig.getInt("signs." + key + ".where.y") == event.getClickedBlock().getY()
                            && signsConfig.getInt("signs." + key + ".where.z") == event.getClickedBlock().getZ()) {
                        path = "signs." + key;
                        signsConfig.set(path, null);
                        signsConfig.set("sign-amount", SignsConfig.getInstance().getInt("sign-amount") - 1);
                        signsConfig.saveConfig();
                    }
                }
                return;
            }

            if (Settings.REQUIRE_SIGHT_CLICK_BREAK_SIGN && event.getAction() == Action.LEFT_CLICK_BLOCK && event.getPlayer().hasPermission(Permissions.SIGN_CREATE.getPermission())) {
                Utils.tell(event.getPlayer(), Messages.MUST_SHIFT_CLICK_TO_BREAK_SIGN);
                event.setCancelled(true);
            }

            for (String key : signsConfig.getConfigurationSection("signs").getKeys(false)) {
                if (signsConfig.get("signs." + key + ".where.world") == event.getPlayer().getLocation().getWorld().getName()
                        && signsConfig.getInt("signs." + key + ".where.x") == event.getClickedBlock().getX()
                        && signsConfig.getInt("signs." + key + ".where.y") == event.getClickedBlock().getY()
                        && signsConfig.getInt("signs." + key + ".where.z") == event.getClickedBlock().getZ()) {

                    getSign(event, SignTypes.valueOf(signsConfig.getString("signs." + key + ".type")));
                }
            }
        }

    }

    private boolean isSign(final PlayerInteractEvent event) {
        final SignsConfig signsConfig = SignsConfig.getInstance();

        if (event.getClickedBlock() == null) {
            return false;
        }

        if (event.getClickedBlock().getType().name().contains("SIGN")) {

            if (signsConfig.getConfigurationSection("signs") == null || signsConfig.getConfigurationSection("signs").getKeys(false).isEmpty()) {
                return false;
            }

            for (String key : signsConfig.getConfigurationSection("signs").getKeys(false)) {
                if (signsConfig.get("signs." + key + ".where.world") == event.getPlayer().getLocation().getWorld().getName()
                        && signsConfig.getInt("signs." + key + ".where.x") == event.getClickedBlock().getX()
                        && signsConfig.getInt("signs." + key + ".where.y") == event.getClickedBlock().getY()
                        && signsConfig.getInt("signs." + key + ".where.z") == event.getClickedBlock().getZ()) {

                    return true;
                }
            }
        }

        return false;
    }

    private void makeSign(final SignChangeEvent event, final SignTypes signType) {

        final SignsConfig signs = SignsConfig.getInstance();
        signs.set("sign-amount", signs.getInt("sign-amount") + 1);

        signs.set("signs." + signs.getInt("sign-amount") + ".where.world", event.getBlock().getWorld().getName());
        signs.set("signs." + signs.getInt("sign-amount") + ".where.x", event.getBlock().getX());
        signs.set("signs." + signs.getInt("sign-amount") + ".where.y", event.getBlock().getY());
        signs.set("signs." + signs.getInt("sign-amount") + ".where.z", event.getBlock().getZ());
        signs.set("signs." + signs.getInt("sign-amount") + ".type", signType.name());
        signs.set("signs." + signs.getInt("sign-amount") + ".created-by", event.getPlayer().getName());

        signs.saveConfig();

    }

    private void getSign(PlayerInteractEvent event, SignTypes signTypes) {
        final Player player = event.getPlayer();

        switch (signTypes) {
            case ANVIL: {
                if (Settings.USE_PERMISSIONS && !event.getPlayer().hasPermission(Permissions.USE_SIGN_ANVIL.getPermission()))
                    return;
                if (!Settings.USE_ANVIL_SIGN)
                    return;
                PortableCraftingInvsPlugin.getFakeContainers().openAnvil(player);
                break;
            }

            case CARTOGRAPHY: {
                if (Settings.USE_PERMISSIONS && !event.getPlayer().hasPermission(Permissions.USE_SIGN_CARTOGRAPHY.getPermission()))
                    return;
                if (!Settings.USE_CARTOGRAPHY_SIGN)
                    return;
                PortableCraftingInvsPlugin.getFakeContainers().openCartography(player);
                break;
            }

            case CRAFTING_TABLE: {
                if (Settings.USE_PERMISSIONS && !event.getPlayer().hasPermission(Permissions.USE_SIGN_CRAFTING.getPermission()))
                    return;
                if (!Settings.USE_CRAFTING_SIGN)
                    return;
                player.openWorkbench(player.getLocation(), true);
                break;
            }

            case ENCHANTMENT_TABLE: {
                if (Settings.USE_PERMISSIONS && !event.getPlayer().hasPermission(Permissions.USE_SIGN_ENCHANT_TABLE.getPermission()))
                    return;
                if (!Settings.USE_ENCHANT_TABLE_SIGN)
                    return;
                PortableCraftingInvsPlugin.getFakeContainers().openEnchant(player);
                break;
            }

            case ENDER_CHEST: {
                if (Settings.USE_PERMISSIONS && !event.getPlayer().hasPermission(Permissions.USE_SIGN_ENDERCHEST.getPermission()))
                    return;
                if (!Settings.USE_ENDERCHEST_SIGN)
                    return;
                player.openInventory(player.getEnderChest());
                break;
            }

            case GRINDSTONE: {
                if (Settings.USE_PERMISSIONS && !event.getPlayer().hasPermission(Permissions.USE_SIGN_GRINDSTONE.getPermission()))
                    return;
                if (!Settings.USE_GRINDSTONE_SIGN)
                    return;
                PortableCraftingInvsPlugin.getFakeContainers().openGrindStone(player);
                break;
            }

            case LOOM: {
                if (Settings.USE_PERMISSIONS && !event.getPlayer().hasPermission(Permissions.USE_SIGN_LOOM.getPermission()))
                    return;
                if (!Settings.USE_LOOM_SIGN)
                    return;
                PortableCraftingInvsPlugin.getFakeContainers().openLoom(player);
                break;
            }

            case STONE_CUTTER: {
                if (Settings.USE_PERMISSIONS && !event.getPlayer().hasPermission(Permissions.USE_SIGN_STONE_CUTTER.getPermission()))
                    return;
                if (!Settings.USE_STONE_CUTTER_SIGN)
                    return;
                PortableCraftingInvsPlugin.getFakeContainers().openStoneCutter(player);
                break;
            }
            case SMITHING: {
                if (Settings.USE_PERMISSIONS && !event.getPlayer().hasPermission(Permissions.USE_SIGN_SMITHING.getPermission()))
                    return;
                if (!Settings.USE_SMITHING_SIGN)
                    return;
                if (!Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].contains("1_16_R1")) {
                    Utils.debugLog(Settings.DEBUG, "Version is not 1.16+, not attempting to open the smithing table.");
                    return;
                }
                PortableCraftingInvsPlugin.getFakeContainers().openSmithing(player);
            }

            default:
                break;
        }

    }

    enum SignTypes {
        ANVIL,
        CARTOGRAPHY,
        CRAFTING_TABLE,
        ENCHANTMENT_TABLE,
        ENDER_CHEST,
        GRINDSTONE,
        LOOM,
        STONE_CUTTER,
        SMITHING
    }

}
