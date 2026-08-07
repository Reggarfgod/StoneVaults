package com.reggarf.mods.StonevaultsFabric.register;

import com.reggarf.mods.Stonevaults.Constants;
import com.reggarf.mods.Stonevaults.structures.processors.LecternProcessor;
import com.reggarf.mods.Stonevaults.structures.processors.NoWaterProcessor;
import com.reggarf.mods.Stonevaults.structures.processors.VineWallProcessor;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;


public class FabricProcessors {

    public static void register() {
        Registry.register(
                BuiltInRegistries.STRUCTURE_PROCESSOR,
                Identifier.fromNamespaceAndPath(Constants.MOD_ID, "vinewall_processor"),
                VineWallProcessor.CODEC
        );

        Registry.register(
                BuiltInRegistries.STRUCTURE_PROCESSOR,
                Identifier.fromNamespaceAndPath(Constants.MOD_ID, "nowater_processor"),
                NoWaterProcessor.CODEC
        );

        Registry.register(
                BuiltInRegistries.STRUCTURE_PROCESSOR,
                Identifier.fromNamespaceAndPath(Constants.MOD_ID, "lectern_processor"),
                LecternProcessor.CODEC
        );
    }
}
