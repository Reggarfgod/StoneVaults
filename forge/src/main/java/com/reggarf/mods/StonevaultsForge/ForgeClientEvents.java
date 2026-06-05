//package com.reggarf.mods.StonevaultsForge;
//
//import com.reggarf.mods.Stonevaults.Constants;
//import com.reggarf.mods.Stonevaults.config.StonevaultsConfig;
//import me.shedaniel.autoconfig.AutoConfig;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.client.ConfigScreenHandler;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.ModLoadingContext;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//
//@Mod.EventBusSubscriber(
//        modid = Constants.MOD_ID,
//        bus = Mod.EventBusSubscriber.Bus.MOD,
//        value = Dist.CLIENT
//)
//public class ForgeClientEvents {
//
//    @SubscribeEvent
//    public static void onClientSetup(FMLClientSetupEvent event) {
//        ModLoadingContext.get().registerExtensionPoint(
//                ConfigScreenHandler.ConfigScreenFactory.class,
//                () -> new ConfigScreenHandler.ConfigScreenFactory(
//                        (client, parent) ->
//                                AutoConfig.getConfigScreen(
//                                        StonevaultsConfig.class,
//                                        parent
//                                ).get()
//                )
//        );
//    }
//}