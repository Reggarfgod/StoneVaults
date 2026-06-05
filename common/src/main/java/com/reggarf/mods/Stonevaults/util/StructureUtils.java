package com.reggarf.mods.Stonevaults.util;

import com.reggarf.mods.Stonevaults.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;

public class StructureUtils {

    public static boolean isInsideStructure(
            ServerLevel level,
            BlockPos pos,
            String structureName
    ) {
        Holder<Structure> structure =
                level.registryAccess()
                        .lookupOrThrow(Registries.STRUCTURE)
                        .getOrThrow(
                                ResourceKey.create(
                                        Registries.STRUCTURE,
                                        ResourceLocation.fromNamespaceAndPath(
                                                Constants.MOD_ID,
                                                structureName
                                        )
                                )
                        );

        StructureStart start =
                level.structureManager()
                        .getStructureWithPieceAt(
                                pos,
                                structure.value()
                        );

        return start.isValid();
    }

    public static boolean isInsideDungeon(
            ServerLevel level,
            BlockPos pos
    ) {
        return isInsideStructure(level, pos, "dungeon");
    }

    public static boolean isInsideMageTower(
            ServerLevel level,
            BlockPos pos
    ) {
        return isInsideStructure(level, pos, "magetower");
    }

    public static boolean isInsideIgloo(
            ServerLevel level,
            BlockPos pos
    ) {
        return isInsideStructure(level, pos, "igloo");
    }

    public static boolean isInsidePillagerDungeon(
            ServerLevel level,
            BlockPos pos
    ) {
        return isInsideStructure(level, pos, "pillager_dungeon");
    }
}