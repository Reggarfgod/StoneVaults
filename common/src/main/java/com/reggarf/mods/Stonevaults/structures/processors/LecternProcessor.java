package com.reggarf.mods.Stonevaults.structures.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.MapCodec;
import com.reggarf.mods.Stonevaults.register.StonevaultsProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;


public class LecternProcessor extends StructureProcessor {

    public static final MapCodec<LecternProcessor> CODEC =
            MapCodec.unit(LecternProcessor::new);


    @Override
    public StructureTemplate.StructureBlockInfo processBlock(
            LevelReader levelReader,
            BlockPos blockPos,
            BlockPos blockPos2,
            StructureTemplate.StructureBlockInfo structureBlockInfo,
            StructureTemplate.StructureBlockInfo structureBlockInfo2,
            StructurePlaceSettings structurePlaceSettings
    ) {

        if (structureBlockInfo2.state().is(Blocks.LECTERN)) {

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

            CompoundTag nbt = structureBlockInfo2.nbt() == null
                    ? new CompoundTag()
                    : structureBlockInfo2.nbt().copy();

            nbt.put("Book", book);

            return new StructureTemplate.StructureBlockInfo(
                    structureBlockInfo2.pos(),
                    structureBlockInfo2.state().setValue(LecternBlock.HAS_BOOK, true),
                    nbt
            );
        }

        return structureBlockInfo2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return StonevaultsProcessors.LECTERN_PROCESSOR;
    }
}