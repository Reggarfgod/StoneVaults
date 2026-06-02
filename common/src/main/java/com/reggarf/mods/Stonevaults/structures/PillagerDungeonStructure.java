package com.reggarf.mods.Stonevaults.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.reggarf.mods.Stonevaults.CommonClass;
import com.reggarf.mods.Stonevaults.Constants;
import com.reggarf.mods.Stonevaults.register.StonevaultStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasLookup;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;


import java.util.Map;
import java.util.Optional;

public class PillagerDungeonStructure extends Structure {

    public static final MapCodec<PillagerDungeonStructure> CODEC =
            simpleCodec(PillagerDungeonStructure::new);

    public static final Identifier START_POOL =
            Identifier.fromNamespaceAndPath("stonevaults", "startpool_pillager_dungeon");

    public static final Identifier START_POOL_LONG =
            Identifier.fromNamespaceAndPath("stonevaults", "startpool_pillager_dungeon_long");

    public PillagerDungeonStructure(StructureSettings settings) {
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

        // old shouldStartAt()
        if (terrainHeight > seaLevel + 32) {
            return Optional.empty();
        }

        boolean highDungeon =
                terrainHeight >= seaLevel + 12;

        Identifier selectedPool =
                highDungeon
                        ? START_POOL_LONG
                        : START_POOL;

        Holder<StructureTemplatePool> startPool =
                context.registryAccess()
                        .lookupOrThrow(Registries.TEMPLATE_POOL)
                        .getOrThrow(
                                ResourceKey.create(
                                        Registries.TEMPLATE_POOL,
                                        selectedPool
                                )
                        );

        BlockPos startPos =
                new BlockPos(x, terrainHeight, z);

        return JigsawPlacement.addPieces(
                context,
                startPool,
                Optional.empty(),
                CommonClass.CONFIG.PILLAGER.SIZE,
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
        return StonevaultStructures.PILLAGER_DUNGEON;
    }

    @Override
    public Map<MobCategory, StructureSpawnOverride> spawnOverrides() {

        WeightedList.Builder<MobSpawnSettings.SpawnerData> builder =
                WeightedList.builder();

        builder.add( new MobSpawnSettings.SpawnerData(
                EntityType.VINDICATOR,
                        1,
                        2
                ),
                10
        );

        builder.add( new MobSpawnSettings.SpawnerData(
                        EntityType.PILLAGER,
                        1,
                        3
                ),
                30
        );

        return Map.of(
                MobCategory.MONSTER,
                new StructureSpawnOverride(
                        StructureSpawnOverride.BoundingBoxType.PIECE,
                        builder.build()
                )
        );
    }
}