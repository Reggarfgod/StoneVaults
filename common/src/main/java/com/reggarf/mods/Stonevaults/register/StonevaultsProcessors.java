package com.reggarf.mods.Stonevaults.register;

import com.reggarf.mods.Stonevaults.structures.processors.LecternProcessor;
import com.reggarf.mods.Stonevaults.structures.processors.NoWaterProcessor;
import com.reggarf.mods.Stonevaults.structures.processors.VineWallProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public class StonevaultsProcessors {

    public static StructureProcessorType<VineWallProcessor> VINEWALL_PROCESSOR;
    public static StructureProcessorType<NoWaterProcessor> NOWATER_PROCESSOR;
    public static StructureProcessorType<LecternProcessor> LECTERN_PROCESSOR;
}