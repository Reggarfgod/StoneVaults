package com.reggarf.mods.Stonevaults.api;


import com.reggarf.mods.Stonevaults.CommonClass;
import com.reggarf.mods.Stonevaults.Constants;
import com.reggarf.mods.better_lib.message.api.JoinMessagePlugin;
import com.reggarf.mods.better_lib.message.api.JoinMessagePlugins;
import com.reggarf.mods.better_lib.message.api.JoinMessageSet;




import java.util.List;


public class JoinPlugin implements JoinMessagePlugin {
    @Override
    public String getModId() {
        return Constants.MOD_ID;
    }

    @Override
    public boolean enabled() {
        return CommonClass.CONFIG.GENERAL.enableInGameMessage;
    }

    @Override
    public List<JoinMessageSet> getMessageSets() {
        return List.of(
                new JoinMessageSet()
                        .addText("    Uncover forgotten dungeons, towers, and treasures with        Stonevault.", 0xFFFFFF)
                        .addLink("Discord", "https://discord.gg/kb6BntpcYq", 0x7289DA, "Support & Updates")
                        .addLink("GitHub", "https://github.com/Reggarfgod/Stonevaults/issues", 0xAAAAAA, "Bug Reports")
                        .addLink("ZAP-Hosting", "https://zap-hosting.com/reggarf", 0x00FFFF, "20% off with code Reggarf-1047")
        );
    }

    public static void register() {
        JoinMessagePlugins.register(new JoinPlugin());
    }
}