package com.itsschatten.portablecrafting.utils;


import net.minecraft.server.v1_15_R1.*;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class FakeContainers {

    public static class FakeGrindstone extends ContainerGrindstone {

        public FakeGrindstone(int containerId, Player player) {
            super(containerId, ((CraftPlayer) player).getHandle().inventory, ContainerAccess.at(((CraftWorld) player.getWorld()).getHandle(), new BlockPosition(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ())));
            this.checkReachable = false;
            this.setTitle(new ChatMessage("Repair & Disenchant"));
        }
    }

    public static class FakeCartography extends ContainerCartography {

        public FakeCartography(int containerId, Player player) {
            super(containerId, ((CraftPlayer) player).getHandle().inventory, ContainerAccess.at(((CraftWorld) player.getWorld()).getHandle(), new BlockPosition(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ())));
            this.checkReachable = false;
            this.setTitle(new ChatMessage("Cartography Table"));
        }
    }

    public static class FakeLoom extends ContainerLoom {

        public FakeLoom(int containerId, Player player) {
            super(containerId, ((CraftPlayer) player).getHandle().inventory, ContainerAccess.at(((CraftWorld) player.getWorld()).getHandle(), new BlockPosition(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ())));
            this.checkReachable = false;
            this.setTitle(new ChatMessage("Loom"));
        }
    }

    public static class FakeStoneCutter extends ContainerStonecutter {

        public FakeStoneCutter(int containerId, Player player) {
            super(containerId, ((CraftPlayer) player).getHandle().inventory, ContainerAccess.at(((CraftWorld) player.getWorld()).getHandle(), new BlockPosition(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ())));
            this.checkReachable = false;
            this.setTitle(new ChatMessage("Stonecutter"));
        }
    }

    public static class FakeAnvil extends ContainerAnvil {

        public FakeAnvil(int containerID, Player player) {
            super(containerID, ((CraftPlayer) player).getHandle().inventory, ContainerAccess.at(((CraftWorld) player.getWorld()).getHandle(), new BlockPosition(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ())));
            this.checkReachable = false; // ignore if the block is reachable, otherwise open regardless of distance.
            this.setTitle(new ChatMessage("Repair & Name"));
        }

    }

    public static class FakeEnchant extends ContainerEnchantTable {

        public FakeEnchant(int i, Player player) {
            super(i, ((CraftPlayer) player).getHandle().inventory, ContainerAccess.at(((CraftWorld) player.getWorld()).getHandle(), new BlockPosition(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ())));
            this.checkReachable = false;
            this.setTitle(new ChatMessage("Enchant"));
        }
    }

}