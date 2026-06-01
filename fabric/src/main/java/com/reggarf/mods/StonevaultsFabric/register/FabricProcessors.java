package com.reggarf.mods.StonevaultsFabric.register;

import com.reggarf.mods.Stonevaults.Constants;
import com.reggarf.mods.Stonevaults.register.StonevaultsProcessors;
import com.reggarf.mods.Stonevaults.structures.processors.LecternProcessor;
import com.reggarf.mods.Stonevaults.structures.processors.NoWaterProcessor;
import com.reggarf.mods.Stonevaults.structures.processors.VineWallProcessor;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class FabricProcessors {

    public static void register() {

        StonevaultsProcessors.VINEWALL_PROCESSOR = Registry.register(
                BuiltInRegistries.STRUCTURE_PROCESSOR,
                new ResourceLocation(Constants.MOD_ID, "vinewall_processor"),
                () -> VineWallProcessor.CODEC
        );

        StonevaultsProcessors.NOWATER_PROCESSOR = Registry.register(
                BuiltInRegistries.STRUCTURE_PROCESSOR,
                new ResourceLocation(Constants.MOD_ID, "nowater_processor"),
                () -> NoWaterProcessor.CODEC
        );

        StonevaultsProcessors.LECTERN_PROCESSOR = Registry.register(
                BuiltInRegistries.STRUCTURE_PROCESSOR,
                new ResourceLocation(Constants.MOD_ID, "lectern_processor"),
                () -> LecternProcessor.CODEC
        );
    }
}