package com.reggarf.mods.StonevaultsForge.register;

import com.reggarf.mods.Stonevaults.Constants;
import com.reggarf.mods.Stonevaults.register.StonevaultStructures;
import com.reggarf.mods.Stonevaults.structures.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ForgeStructures {

    public static final DeferredRegister<StructureType<?>> STRUCTURES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, Constants.MOD_ID);

    public static final RegistryObject<StructureType<MageTowerStructure>> MAGETOWER =
            STRUCTURES.register("magetower",
                    () -> () -> MageTowerStructure.CODEC);

    public static final RegistryObject<StructureType<IglooStructure>> IGLOO =
            STRUCTURES.register("igloo",
                    () -> () -> IglooStructure.CODEC);

    public static final RegistryObject<StructureType<DungeonStructure>> DUNGEON =
            STRUCTURES.register("dungeon",
                    () -> () -> DungeonStructure.CODEC);

    public static final RegistryObject<StructureType<PillagerDungeonStructure>> PILLAGER_DUNGEON =
            STRUCTURES.register("pillager_dungeon",
                    () -> () -> PillagerDungeonStructure.CODEC);

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