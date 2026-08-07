package com.reggarf.mods.Stonevaults.structures.processors;

import com.mojang.serialization.JsonOps;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;


public class LecternProcessor implements StructureProcessor {

    public static final MapCodec<LecternProcessor> CODEC =
            MapCodec.unit(LecternProcessor::new);

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(
            LevelReader level,
            BlockPos targetPosition,
            BlockPos referencePos,
            BlockPos templateRelativePos,
            StructureTemplate.StructureBlockInfo processedBlockInfo,
            StructurePlaceSettings settings
    ) {
        if (processedBlockInfo.state().is(Blocks.LECTERN)) {
            CompoundTag book = new CompoundTag();
            book.putString("id", "minecraft:written_book");
            book.putByte("Count", (byte) 1);

            CompoundTag bookTag = new CompoundTag();
            bookTag.putBoolean("resolved", true);
            bookTag.putString("author", "Reggarf");
            bookTag.putString("title", "???");
            bookTag.putString("filtered_title", "???");

            ListTag pages = new ListTag();
            ComponentSerialization.CODEC.encodeStart(JsonOps.INSTANCE, Component.literal("???")
            ).getOrThrow();
            bookTag.put("pages", pages);

            book.put("tag", bookTag);

            CompoundTag nbt = processedBlockInfo.nbt() == null
                    ? new CompoundTag()
                    : processedBlockInfo.nbt().copy();

            nbt.put("Book", book);

            return new StructureTemplate.StructureBlockInfo(
                    processedBlockInfo.pos(),
                    processedBlockInfo.state().setValue(LecternBlock.HAS_BOOK, true),
                    nbt
            );
        }

        return processedBlockInfo;
    }

    @Override
    public MapCodec<LecternProcessor> codec() {
        return CODEC;
    }
}
