package com.reggarf.mods.StonevaultsNeoforge.register;

import com.reggarf.mods.Stonevaults.Constants;
import com.reggarf.mods.Stonevaults.register.StonevaultStructures;
import com.reggarf.mods.Stonevaults.structures.DungeonStructure;
import com.reggarf.mods.Stonevaults.structures.IglooStructure;
import com.reggarf.mods.Stonevaults.structures.MageTowerStructure;
import com.reggarf.mods.Stonevaults.structures.PillagerDungeonStructure;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NeoForgeStructures {

    public static final DeferredRegister<StructureType<?>> STRUCTURES =
            DeferredRegister.create(
                    Registries.STRUCTURE_TYPE,
                    Constants.MOD_ID
            );

    public static final DeferredHolder<
            StructureType<?>,
            StructureType<MageTowerStructure>
            > MAGETOWER =
            STRUCTURES.register(
                    "magetower",
                    () -> () -> MageTowerStructure.CODEC
            );

    public static final DeferredHolder<
            StructureType<?>,
            StructureType<IglooStructure>
            > IGLOO =
            STRUCTURES.register(
                    "igloo",
                    () -> () -> IglooStructure.CODEC
            );

    public static final DeferredHolder<
            StructureType<?>,
            StructureType<DungeonStructure>
            > DUNGEON =
            STRUCTURES.register(
                    "dungeon",
                    () -> () -> DungeonStructure.CODEC
            );

    public static final DeferredHolder<
            StructureType<?>,
            StructureType<PillagerDungeonStructure>
            > PILLAGER_DUNGEON =
            STRUCTURES.register(
                    "pillager_dungeon",
                    () -> () -> PillagerDungeonStructure.CODEC
            );

    public static void register(IEventBus bus) {
        STRUCTURES.register(bus);
    }

    public static void finish() {
        StonevaultStructures.MAGETOWER = MAGETOWER.get();
        StonevaultStructures.IGLOO = IGLOO.get();
        StonevaultStructures.DUNGEON = DUNGEON.get();
        StonevaultStructures.PILLAGER_DUNGEON = PILLAGER_DUNGEON.get();
    }
}