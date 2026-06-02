package com.reggarf.mods.StonevaultsForge.register;

import com.reggarf.mods.Stonevaults.Constants;
import com.reggarf.mods.Stonevaults.register.StructureAdvancement;
import com.reggarf.mods.Stonevaults.util.StructureUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class StructureEnterEvents {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {

        if (event.phase != TickEvent.Phase.END)
            return;

        if (!(event.player instanceof ServerPlayer player))
            return;

        if (!(player.level() instanceof ServerLevel level))
            return;

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
}