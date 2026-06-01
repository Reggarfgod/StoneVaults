package com.reggarf.mods.Stonevaults.register;

import com.reggarf.mods.Stonevaults.Constants;
import com.reggarf.mods.Stonevaults.structures.processors.LecternProcessor;
import com.reggarf.mods.Stonevaults.structures.processors.NoWaterProcessor;
import com.reggarf.mods.Stonevaults.structures.processors.VineWallProcessor;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;


public class StonevaultsProcessors {

    public static final StructureProcessorType<VineWallProcessor> VINEWALL_PROCESSOR =
            () -> VineWallProcessor.CODEC;

    public static final StructureProcessorType<NoWaterProcessor> NOWATER_PROCESSOR =
            () -> NoWaterProcessor.CODEC;

    public static final StructureProcessorType<LecternProcessor> LECTERN_PROCESSOR =
            () -> LecternProcessor.CODEC;

    public static void registerProcessors() {

        Registry.register(
                BuiltInRegistries.STRUCTURE_PROCESSOR,
                new ResourceLocation("stonevaults", "vinewall_processor"),
                VINEWALL_PROCESSOR
        );

        Registry.register(
                BuiltInRegistries.STRUCTURE_PROCESSOR,
                new ResourceLocation("stonevaults", "nowater_processor"),
                NOWATER_PROCESSOR
        );

        Registry.register(
                BuiltInRegistries.STRUCTURE_PROCESSOR,
                new ResourceLocation("stonevaults", "lectern_processor"),
                LECTERN_PROCESSOR
        );
    }
}