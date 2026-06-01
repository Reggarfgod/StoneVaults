package com.reggarf.mods.StonevaultsFabric;

import com.reggarf.mods.Stonevaults.CommonClass;
import com.reggarf.mods.Stonevaults.Constants;
import net.fabricmc.api.ModInitializer;

public class StonevaultsFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        Constants.LOG.info("Hello Fabric world!");
        CommonClass.init();
    }
}
