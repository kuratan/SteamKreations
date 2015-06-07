package de.kuratan.steamkreations.utils;

import de.kuratan.steamkreations.SteamKreations;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ModReference {
    public static final String TEXTURE_PATH = "textures/";
    public static final String BLOCKS_PATH = TEXTURE_PATH + "blocks/";
    public static final String GUI_PATH = TEXTURE_PATH + "gui/";
    public static final String ITEMS_PATH = TEXTURE_PATH + "items/";

    public static ResourceLocation getGuiResourceLocation(String gui) {
        return new ResourceLocation(SteamKreations.MOD_ID, GUI_PATH + gui + ".png");
    }

    public static String getUnlocalizedInternalNameWithMod(Block block) {
        return block.getUnlocalizedName().substring(("tile.").length());
    }

    public static String getUnlocalizedInternalNameWithMod(Item item) {
        return item.getUnlocalizedName().substring(("item.").length());
    }

    public static String getUnlocalizedInternalName(Block block) {
        return getUnlocalizedInternalNameWithMod(block).substring((SteamKreations.MOD_ID + ":").length());
    }

    public static String getUnlocalizedInternalName(Item item) {
        return getUnlocalizedInternalNameWithMod(item).substring((SteamKreations.MOD_ID + ":").length());
    }

    public static String modPrefix(String base) {
        return SteamKreations.MOD_ID + ":" + base;
    }
}
