package com.reggarf.mods.Stonevaults.register;


import com.reggarf.mods.Stonevaults.Constants;
import com.reggarf.mods.Stonevaults.structures.DungeonStructure;
import com.reggarf.mods.Stonevaults.structures.IglooStructure;
import com.reggarf.mods.Stonevaults.structures.MageTowerStructure;
import com.reggarf.mods.Stonevaults.structures.PillagerDungeonStructure;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class StonevaultStructures {

    public static final StructureType<MageTowerStructure> MAGETOWER =
            () -> MageTowerStructure.CODEC;

    public static final StructureType<IglooStructure> IGLOO =
            () -> IglooStructure.CODEC;

    public static final StructureType<DungeonStructure> DUNGEON =
            () -> DungeonStructure.CODEC;

    public static final StructureType<PillagerDungeonStructure> PILLAGER_DUNGEON =
            () -> PillagerDungeonStructure.CODEC;

    public static void registerStructures() {

        Registry.register(
                BuiltInRegistries.STRUCTURE_TYPE,
                new ResourceLocation("stonevaults", "magetower"),
                MAGETOWER
        );

        Registry.register(
                BuiltInRegistries.STRUCTURE_TYPE,
                new ResourceLocation("stonevaults", "igloo"),
                IGLOO
        );

        Registry.register(
                BuiltInRegistries.STRUCTURE_TYPE,
                new ResourceLocation("stonevaults", "dungeon"),
                DUNGEON
        );

        Registry.register(
                BuiltInRegistries.STRUCTURE_TYPE,
                new ResourceLocation("stonevaults", "pillager_dungeon"),
                PILLAGER_DUNGEON
        );
    }
}