package com.reggarf.mods.Stonevaults.config;

import com.reggarf.mods.Stonevaults.Constants;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = Constants.MOD_ID)
public class StonevaultsConfig implements ConfigData {

    public static final class Structure {
        public int SPACING = 64;
        public int SEPARATION = 32;
        public int SALT = 987654321;

        public int SIZE;

        public Structure(int size) {
            this.SIZE = size;
        }
    }

    @Comment("""
            Structure Configs.

            SIZE controls how many jigsaw expansions are attempted.
            Larger values generally create larger structures.
            For example, increasing Mage Tower size can create more floors.
            """)
    public Structure MAGETOWER = new Structure(7);

    public Structure IGLOO = new Structure(7);

    public Structure DUNGEON = new Structure(8);

    public Structure PILLAGER = new Structure(8);

}