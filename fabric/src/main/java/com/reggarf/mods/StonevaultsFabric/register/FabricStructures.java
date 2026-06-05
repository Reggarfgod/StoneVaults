package com.reggarf.mods.StonevaultsFabric.register;

import com.reggarf.mods.Stonevaults.Constants;
import com.reggarf.mods.Stonevaults.register.StonevaultStructures;
import com.reggarf.mods.Stonevaults.structures.DungeonStructure;
import com.reggarf.mods.Stonevaults.structures.IglooStructure;
import com.reggarf.mods.Stonevaults.structures.MageTowerStructure;
import com.reggarf.mods.Stonevaults.structures.PillagerDungeonStructure;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;


public class FabricStructures {

    public static void register() {

        StonevaultStructures.MAGETOWER = Registry.register(
                BuiltInRegistries.STRUCTURE_TYPE,
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "magetower"),
                () -> MageTowerStructure.CODEC
        );

        StonevaultStructures.IGLOO = Registry.register(
                BuiltInRegistries.STRUCTURE_TYPE,
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "igloo"),
                () -> IglooStructure.CODEC
        );

        StonevaultStructures.DUNGEON = Registry.register(
                BuiltInRegistries.STRUCTURE_TYPE,
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "dungeon"),
                () -> DungeonStructure.CODEC
        );

        StonevaultStructures.PILLAGER_DUNGEON = Registry.register(
                BuiltInRegistries.STRUCTURE_TYPE,
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "pillager_dungeon"),
                () -> PillagerDungeonStructure.CODEC
        );
    }
}