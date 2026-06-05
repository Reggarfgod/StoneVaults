package com.reggarf.mods.Stonevaults.register;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;

public class StructureAdvancement {

    public static void awardDungeon(ServerPlayer player) {
        award(player, "enter_dungeon");
    }

    public static void awardMageTower(ServerPlayer player) {
        award(player, "enter_mage_tower");
    }

    public static void awardIgloo(ServerPlayer player) {
        award(player, "enter_igloo");
    }

    public static void awardPillagerDungeon(ServerPlayer player) {
        award(player, "enter_pillager_dungeon");
    }

    private static void award(ServerPlayer player, String advancementName) {

        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(
                "stonevault",
                advancementName
        );

        MinecraftServer server = player.level().getServer();

        if (server == null)
            return;

        AdvancementHolder advancement =
                server.getAdvancements().get(id);

        if (advancement == null)
            return;

        PlayerAdvancements advancements =
                player.getAdvancements();

        AdvancementProgress progress =
                advancements.getOrStartProgress(advancement);

        if (!progress.isDone()) {
            for (String criterion : progress.getRemainingCriteria()) {
                advancements.award(advancement, criterion);
            }
        }
    }
}