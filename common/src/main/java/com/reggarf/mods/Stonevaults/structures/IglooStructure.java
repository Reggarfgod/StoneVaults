package com.reggarf.mods.Stonevaults.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.reggarf.mods.Stonevaults.CommonClass;
import com.reggarf.mods.Stonevaults.Constants;
import com.reggarf.mods.Stonevaults.register.StonevaultStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasLookup;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.Optional;

public class IglooStructure extends Structure {

    public static final MapCodec<IglooStructure> CODEC =
            simpleCodec(IglooStructure::new);

    public static final ResourceLocation START_POOL =
           ResourceLocation.fromNamespaceAndPath(
                    "stonevaults",
                    "startpool_igloo"
            );

    public IglooStructure(StructureSettings settings) {
        super(settings);
    }

    @Override
    public Optional<GenerationStub> findGenerationPoint(
            GenerationContext context) {

        if (!extraSpawningChecks(context))
            return Optional.empty();

        ChunkPos chunkPos = context.chunkPos();

        int x = chunkPos.getMiddleBlockX();
        int z = chunkPos.getMiddleBlockZ();

        int y = context.chunkGenerator().getFirstFreeHeight(
                x,
                z,
                Heightmap.Types.WORLD_SURFACE_WG,
                context.heightAccessor(),
                context.randomState()
        );

        Holder<StructureTemplatePool> startPool =
                context.registryAccess()
                        .registryOrThrow(Registries.TEMPLATE_POOL)
                        .getHolderOrThrow(
                                ResourceKey.create(
                                        Registries.TEMPLATE_POOL,
                                        START_POOL
                                )
                        );

        return JigsawPlacement.addPieces(
                context,
                startPool,
                Optional.empty(),
                CommonClass.CONFIG.IGLOO.SIZE,
                new BlockPos(x, y + 1, z),
                false,
                Optional.empty(),
                128,
                PoolAliasLookup.EMPTY,
                JigsawStructure.DEFAULT_DIMENSION_PADDING,
                LiquidSettings.IGNORE_WATERLOGGING
        );
    }

    private static boolean extraSpawningChecks(
            GenerationContext context) {

        ChunkPos chunkPos = context.chunkPos();

        int centerX = chunkPos.getMiddleBlockX();
        int centerZ = chunkPos.getMiddleBlockZ();

        int minHeight = Integer.MAX_VALUE;
        int maxHeight = Integer.MIN_VALUE;

        for (int xOffset = -8; xOffset <= 8; xOffset += 4) {
            for (int zOffset = -8; zOffset <= 8; zOffset += 4) {

                int height =
                        context.chunkGenerator().getFirstFreeHeight(
                                centerX + xOffset,
                                centerZ + zOffset,
                                Heightmap.Types.WORLD_SURFACE_WG,
                                context.heightAccessor(),
                                context.randomState()
                        );

                minHeight = Math.min(minHeight, height);
                maxHeight = Math.max(maxHeight, height);

                if (height < 60)
                    return false;

                if (height > 140)
                    return false;
            }
        }

        if ((maxHeight - minHeight) > 6)
            return false;

        return true;
    }

    @Override
    public StructureType<?> type() {
        return StonevaultStructures.IGLOO;
    }
}