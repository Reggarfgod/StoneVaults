package com.reggarf.mods.Stonevaults.config;

import com.reggarf.mods.Stonevaults.Constants;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = Constants.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/deepslate_tiles.png")
public class StonevaultsConfig extends PartitioningSerializer.GlobalData {

    @ConfigEntry.Category("magetower")
    @ConfigEntry.Gui.TransitiveObject()
    public MageTower MAGETOWER = new MageTower();

    @ConfigEntry.Category("igloo")
    @ConfigEntry.Gui.TransitiveObject()
    public Igloo IGLOO = new Igloo();

    @ConfigEntry.Category("dungeon")
    @ConfigEntry.Gui.TransitiveObject()
    public Dungeon DUNGEON = new Dungeon();

    @ConfigEntry.Category("pillager")
    @ConfigEntry.Gui.TransitiveObject()
    public Pillager PILLAGER = new Pillager();

    @Config(name = "magetower")
    public static class MageTower implements ConfigData {

        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        @Comment("Controls how many jigsaw expansions are attempted.")
        public int SIZE = 7;
    }

    @Config(name = "igloo")
    public static class Igloo implements ConfigData {

        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        @Comment("Controls how many jigsaw expansions are attempted.")
        public int SIZE = 7;
    }

    @Config(name = "dungeon")
    public static class Dungeon implements ConfigData {

        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        @Comment("Controls how many jigsaw expansions are attempted.")
        public int SIZE = 8;
    }

    @Config(name = "pillager")
    public static class Pillager implements ConfigData {

        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        @Comment("Controls how many jigsaw expansions are attempted.")
        public int SIZE = 8;
    }
}