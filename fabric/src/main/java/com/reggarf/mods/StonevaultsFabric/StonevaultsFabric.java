package com.reggarf.mods.StonevaultsFabric;

import com.reggarf.mods.Stonevaults.CommonClass;
import com.reggarf.mods.Stonevaults.Constants;
import com.reggarf.mods.Stonevaults.register.StonevaultsProcessors;
import com.reggarf.mods.StonevaultsFabric.register.FabricProcessors;
import com.reggarf.mods.StonevaultsFabric.register.FabricStructures;
import net.fabricmc.api.ModInitializer;

public class StonevaultsFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        Constants.LOG.info("Hello Fabric world!");
        FabricStructures.register();
        FabricProcessors.register();
        CommonClass.init();
    }
}
