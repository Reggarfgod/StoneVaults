package com.reggarf.mods.StonevaultsForge;

import com.reggarf.mods.Stonevaults.CommonClass;
import com.reggarf.mods.Stonevaults.Constants;
import com.reggarf.mods.Stonevaults.config.StonevaultsConfig;
import com.reggarf.mods.StonevaultsForge.register.ForgeProcessors;
import com.reggarf.mods.StonevaultsForge.register.ForgeStructures;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class StonevaultsForge {
    
    public StonevaultsForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
        ForgeStructures.register(bus);
        ForgeProcessors.register(bus);

        bus.addListener((FMLCommonSetupEvent event) -> {
            event.enqueueWork(() -> {
                ForgeStructures.finish();
                ForgeProcessors.finish();
            });
        });
        // Use Forge to bootstrap the Common mod.
        Constants.LOG.info("Hello Forge world!");
        CommonClass.init();
        
    }
}