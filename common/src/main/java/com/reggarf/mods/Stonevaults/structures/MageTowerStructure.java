package com.reggarf.mods.Stonevaults.structures;

import com.mojang.serialization.MapCodec;
import com.reggarf.mods.Stonevaults.CommonClass;
import com.reggarf.mods.Stonevaults.register.StonevaultStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasLookup;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.Optional;

public class MageTowerStructure extends Structure {

    public static final MapCodec<MageTowerStructure> CODEC =
            simpleCodec(MageTowerStructure::new);

    public static final ResourceLocation START_POOL =
            ResourceLocation.fromNamespaceAndPath(
                    "stonevaults",
                    "startpool_magetower"
            );

    public MageTowerStructure(StructureSettings settings) {
        super(settings);
    }

    @Override
    public Optional<GenerationStub> findGenerationPoint(
            GenerationContext context
    ) {

        int x = context.chunkPos().getMiddleBlockX();
        int z = context.chunkPos().getMiddleBlockZ();

        int y = context.chunkGenerator().getFirstFreeHeight(
                x,
                z,
                Heightmap.Types.WORLD_SURFACE_WG,
                context.heightAccessor(),
                context.randomState()
        );

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
                y + 1,
                z
        );

        return JigsawPlacement.addPieces(
                context,
                startPool,
                Optional.empty(),
                CommonClass.CONFIG.MAGETOWER.SIZE,
                startPos,
                false,
                Optional.empty(),
                new JigsawStructure.MaxDistance(128),
                PoolAliasLookup.EMPTY,
                new DimensionPadding(0, 0),
                LiquidSettings.IGNORE_WATERLOGGING
        );
    }

    @Override
    public StructureType<?> type() {
        return StonevaultStructures.MAGETOWER;
    }
}