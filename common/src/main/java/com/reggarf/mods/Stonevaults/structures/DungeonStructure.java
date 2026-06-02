package com.reggarf.mods.Stonevaults.structures;

import com.mojang.serialization.MapCodec;
import com.reggarf.mods.Stonevaults.CommonClass;
import com.reggarf.mods.Stonevaults.register.StonevaultStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasLookup;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.Optional;

public class DungeonStructure extends Structure {

    public static final MapCodec<DungeonStructure> CODEC =
            simpleCodec(DungeonStructure::new);

    public static final Identifier START_POOL =
            Identifier.fromNamespaceAndPath(
                    "stonevaults",
                    "startpool_dungeon"
            );

    public DungeonStructure(StructureSettings settings) {
        super(settings);
    }

    @Override
    public Optional<GenerationStub> findGenerationPoint(GenerationContext context) {

        int x = context.chunkPos().getMiddleBlockX();
        int z = context.chunkPos().getMiddleBlockZ();

        int terrainHeight = context.chunkGenerator().getFirstFreeHeight(
                x,
                z,
                Heightmap.Types.WORLD_SURFACE_WG,
                context.heightAccessor(),
                context.randomState()
        );

        int seaLevel = context.chunkGenerator().getSeaLevel();

        // Avoid mountains
        if (terrainHeight > seaLevel + 40) {
            return Optional.empty();
        }
        int dungeonY = terrainHeight;

        Holder<StructureTemplatePool> startPool =
                context.registryAccess()
                        .lookupOrThrow(Registries.TEMPLATE_POOL)
                        .getOrThrow(
                                ResourceKey.create(
                                        Registries.TEMPLATE_POOL,
                                        START_POOL
                                )
                        );

        BlockPos startPos = new BlockPos(
                x,
                dungeonY,
                z
        );

        return JigsawPlacement.addPieces(
                context,
                startPool,
                Optional.empty(),
                CommonClass.CONFIG.DUNGEON.SIZE,
                startPos,
                false,
                Optional.empty(),
                new JigsawStructure.MaxDistance(128),
                PoolAliasLookup.EMPTY,
                JigsawStructure.DEFAULT_DIMENSION_PADDING,
                LiquidSettings.IGNORE_WATERLOGGING
        );
    }

    @Override
    public StructureType<?> type() {
        return StonevaultStructures.DUNGEON;
    }
}