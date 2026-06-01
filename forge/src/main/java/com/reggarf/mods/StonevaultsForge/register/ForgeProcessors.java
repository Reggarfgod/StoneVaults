package com.reggarf.mods.StonevaultsForge.register;

import com.reggarf.mods.Stonevaults.Constants;
import com.reggarf.mods.Stonevaults.register.StonevaultsProcessors;
import com.reggarf.mods.Stonevaults.structures.processors.LecternProcessor;
import com.reggarf.mods.Stonevaults.structures.processors.NoWaterProcessor;
import com.reggarf.mods.Stonevaults.structures.processors.VineWallProcessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ForgeProcessors {

    public static final DeferredRegister<StructureProcessorType<?>> PROCESSORS =
            DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, Constants.MOD_ID);

    public static final RegistryObject<StructureProcessorType<VineWallProcessor>> VINEWALL =
            PROCESSORS.register("vinewall_processor",
                    () -> () -> VineWallProcessor.CODEC);

    public static final RegistryObject<StructureProcessorType<NoWaterProcessor>> NOWATER =
            PROCESSORS.register("nowater_processor",
                    () -> () -> NoWaterProcessor.CODEC);

    public static final RegistryObject<StructureProcessorType<LecternProcessor>> LECTERN =
            PROCESSORS.register("lectern_processor",
                    () -> () -> LecternProcessor.CODEC);

    public static void register(IEventBus bus) {
        PROCESSORS.register(bus);
    }

    public static void finish() {
        StonevaultsProcessors.VINEWALL_PROCESSOR = VINEWALL.get();
        StonevaultsProcessors.NOWATER_PROCESSOR = NOWATER.get();
        StonevaultsProcessors.LECTERN_PROCESSOR = LECTERN.get();
    }
}