package de.kuratan.steamkreations.lib;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class Reference {
    public static final String MOD_ID = "@MOD_ID@";
    public static final String MOD_NAME = "@MOD_NAME@";
    public static final String MOD_VERSION = "@MOD_VERSION@";

    public static final String TEXTURE_PATH = "textures/";
    public static final String ITEMS_PATH = TEXTURE_PATH + "items/";
    public static final String GUI_PATH = TEXTURE_PATH + "gui/";
    public static final String BLOCKS_PATH = TEXTURE_PATH + "blocks/";

    public static final int TICKS_PER_SECOND = 20;
    public static final int TICKS_PER_MINUTE = 60 * TICKS_PER_SECOND;
    public static final int TICKS_PER_HOUR = 60 * TICKS_PER_MINUTE;

    public static ResourceLocation getGuiResourceLocation(String gui) {
        return new ResourceLocation(MOD_ID, GUI_PATH + gui + ".png");
    }

    public static String getUnlocalizedInternalNameWithMod(Block block) {
        return block.getUnlocalizedName().substring(("tile.").length());
    }

    public static String getUnlocalizedInternalNameWithMod(Item item) {
        return item.getUnlocalizedName().substring(("item.").length());
    }

    public static String getUnlocalizedInternalName(Block block) {
        return getUnlocalizedInternalNameWithMod(block).substring((MOD_ID + ":").length());
    }

    public static String getUnlocalizedInternalName(Item item) {
        return getUnlocalizedInternalNameWithMod(item).substring((MOD_ID + ":").length());
    }

    public static String modPrefix(String base) {
        return MOD_ID + ":" + base;
    }
}
