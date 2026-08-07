package com.reggarf.mods.Stonevaults.structures.processors;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.material.FluidState;


public class NoWaterProcessor implements StructureProcessor {

    public static final MapCodec<NoWaterProcessor> CODEC =
            MapCodec.unit(NoWaterProcessor::new);

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(
            LevelReader level,
            BlockPos targetPosition,
            BlockPos referencePos,
            BlockPos templateRelativePos,
            StructureTemplate.StructureBlockInfo processedBlockInfo,
            StructurePlaceSettings settings
    ) {
        if (processedBlockInfo.state().hasProperty(BlockStateProperties.WATERLOGGED)) {
            FluidState fluidState = level.getFluidState(processedBlockInfo.pos());

            if (!fluidState.isEmpty()) {
                boolean waterlogged = processedBlockInfo.state()
                        .getValue(BlockStateProperties.WATERLOGGED);

                return new StructureTemplate.StructureBlockInfo(
                        processedBlockInfo.pos(),
                        processedBlockInfo.state().setValue(
                                BlockStateProperties.WATERLOGGED,
                                waterlogged
                        ),
                        processedBlockInfo.nbt()
                );
            }
        }

        return processedBlockInfo;
    }

    @Override
    public MapCodec<NoWaterProcessor> codec() {
        return CODEC;
    }
}
