package com.reggarf.mods.StonevaultsFabric.register;

import com.reggarf.mods.Stonevaults.register.StructureAdvancement;
import com.reggarf.mods.Stonevaults.util.StructureUtils;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class StructureEnterEvents {

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {

            for (ServerPlayer player : server.getPlayerList().getPlayers()) {

                if (!(player.level() instanceof ServerLevel level))
                    continue;

                if (StructureUtils.isInsideDungeon(level, player.blockPosition())) {
                    StructureAdvancement.awardDungeon(player);
                }

                if (StructureUtils.isInsideMageTower(level, player.blockPosition())) {
                    StructureAdvancement.awardMageTower(player);
                }

                if (StructureUtils.isInsideIgloo(level, player.blockPosition())) {
                    StructureAdvancement.awardIgloo(player);
                }

                if (StructureUtils.isInsidePillagerDungeon(level, player.blockPosition())) {
                    StructureAdvancement.awardPillagerDungeon(player);
                }
            }
        });
    }
}