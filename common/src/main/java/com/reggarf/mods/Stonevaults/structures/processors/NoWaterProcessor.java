package com.reggarf.mods.Stonevaults.structures.processors;

import com.mojang.serialization.Codec;
import com.reggarf.mods.Stonevaults.register.StonevaultsProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.Nullable;

public class NoWaterProcessor extends StructureProcessor {

    public static final Codec<NoWaterProcessor> CODEC =
            Codec.unit(NoWaterProcessor::new);

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(
            LevelReader level,
            BlockPos offset,
            BlockPos pos,
            StructureTemplate.StructureBlockInfo localInfo,
            StructureTemplate.StructureBlockInfo worldInfo,
            StructurePlaceSettings settings
    ) {

        if (worldInfo.state().hasProperty(BlockStateProperties.WATERLOGGED)) {

            FluidState fluidState = level.getFluidState(worldInfo.pos());

            if (!fluidState.isEmpty()) {

                boolean waterlogged =
                        localInfo.state().hasProperty(BlockStateProperties.WATERLOGGED)
                                && localInfo.state().getValue(BlockStateProperties.WATERLOGGED);

                return new StructureTemplate.StructureBlockInfo(
                        worldInfo.pos(),
                        worldInfo.state().setValue(
                                BlockStateProperties.WATERLOGGED,
                                waterlogged
                        ),
                        worldInfo.nbt()
                );
            }
        }

        return worldInfo;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return StonevaultsProcessors.NOWATER_PROCESSOR;
    }
}