package com.reggarf.mods.Stonevaults.structures.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.reggarf.mods.Stonevaults.register.StonevaultsProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;



public class VineWallProcessor extends StructureProcessor {

    public static final MapCodec<VineWallProcessor> CODEC =
            Codec.FLOAT.fieldOf("probability")
                    .xmap(VineWallProcessor::new, processor -> processor.probability);

    private final float probability;

    public VineWallProcessor(float probability) {
        this.probability = probability;
    }


    @Override
    public StructureTemplate.StructureBlockInfo processBlock(
            LevelReader level,
            BlockPos offset,
            BlockPos pos,
            StructureTemplate.StructureBlockInfo localInfo,
            StructureTemplate.StructureBlockInfo worldInfo,
            StructurePlaceSettings settings
    ) {

        if (worldInfo.state().isAir()) {

            RandomSource random = settings.getRandom(worldInfo.pos());
            random.setSeed(worldInfo.pos().asLong() * worldInfo.pos().getY());

            if (random.nextFloat() < probability) {

                BlockState worldState = level.getBlockState(worldInfo.pos());

                if (worldState.isAir()) {

                    BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

                    for (Direction direction : Direction.Plane.HORIZONTAL) {

                        mutablePos.set(worldInfo.pos()).move(direction);

                        BlockState adjacentState =
                                level.getBlockState(mutablePos);

                        if (!adjacentState.isAir()
                                && Block.isFaceFull(
                                adjacentState.getCollisionShape(level, mutablePos),
                                direction.getOpposite()
                        )) {

                            BlockState vineState =
                                    Blocks.VINE.defaultBlockState()
                                            .setValue(
                                                    VineBlock.getPropertyForFace(direction),
                                                    true
                                            );

                            return new StructureTemplate.StructureBlockInfo(
                                    worldInfo.pos(),
                                    vineState,
                                    worldInfo.nbt()
                            );
                        }
                    }
                }
            }
        }

        return worldInfo;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return StonevaultsProcessors.VINEWALL_PROCESSOR;
    }
}