package com.reggarf.mods.Stonevaults;

import com.reggarf.mods.Stonevaults.api.JoinPlugin;
import com.reggarf.mods.Stonevaults.config.StonevaultsConfig;
import com.reggarf.mods.Stonevaults.platform.Services;
import com.reggarf.mods.Stonevaults.register.StonevaultStructures;
import com.reggarf.mods.Stonevaults.register.StonevaultsProcessors;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class CommonClass {
    public static StonevaultsConfig CONFIG;
    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.
    public static void init() {
        JoinPlugin.register();
        AutoConfig.register(StonevaultsConfig.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
        CONFIG = AutoConfig.getConfigHolder(StonevaultsConfig.class).getConfig();

        if (Services.PLATFORM.isModLoaded("Stonevaults")) {

            Constants.LOG.info("Hello to Stonevaults");
        }
    }
}