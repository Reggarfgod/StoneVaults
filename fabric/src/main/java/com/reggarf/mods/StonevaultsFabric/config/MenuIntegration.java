package com.reggarf.mods.StonevaultsFabric.config;


import com.reggarf.mods.Stonevaults.config.StonevaultsConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfigClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;


@Environment(EnvType.CLIENT)
public class MenuIntegration implements ModMenuApi {
   @Override
   public ConfigScreenFactory<?> getModConfigScreenFactory() {
       return parent -> AutoConfigClient.getConfigScreen(StonevaultsConfig.class, parent).get();
    }

}