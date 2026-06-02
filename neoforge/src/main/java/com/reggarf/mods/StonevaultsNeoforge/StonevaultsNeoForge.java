package com.reggarf.mods.StonevaultsNeoforge;


import com.reggarf.mods.Stonevaults.CommonClass;
import com.reggarf.mods.Stonevaults.Constants;
import com.reggarf.mods.Stonevaults.config.StonevaultsConfig;
import com.reggarf.mods.StonevaultsNeoforge.register.NeoForgeProcessors;
import com.reggarf.mods.StonevaultsNeoforge.register.NeoForgeStructures;
import me.shedaniel.autoconfig.AutoConfig;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(Constants.MOD_ID)
public class StonevaultsNeoForge {

    public StonevaultsNeoForge(IEventBus bus) {
        NeoForgeStructures.register(bus);
        NeoForgeProcessors.register(bus);

        bus.addListener((FMLCommonSetupEvent event) -> {
            event.enqueueWork(() -> {
                NeoForgeStructures.finish();
                NeoForgeProcessors.finish();
            });
        });
        // Use NeoForge to bootstrap the Common mod.
        Constants.LOG.info("Hello NeoForge world!");
        CommonClass.init();

    }
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (container, parent) -> {
            return AutoConfig.getConfigScreen(StonevaultsConfig.class, parent).get();
        });
    }
}