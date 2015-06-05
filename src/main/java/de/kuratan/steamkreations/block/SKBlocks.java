package de.kuratan.steamkreations.block;

import cpw.mods.fml.common.registry.GameRegistry;
import de.kuratan.steamkreations.item.ItemSteamer;
import net.minecraft.item.ItemBlock;

public class SKBlocks {
    public static SKBlockContainer steamer;

    public static SKBlock registerBlock(final SKBlock block, final Class<? extends ItemBlock> itemblock) {
        return (SKBlock)GameRegistry.registerBlock(block, (Class)itemblock, block.getUnlocalizedInternalName());
    }

    public static SKBlockContainer registerBlock(final SKBlockContainer block, final Class<? extends ItemBlock> itemblock) {
        return (SKBlockContainer)GameRegistry.registerBlock(block, (Class)itemblock, block.getUnlocalizedInternalName());
    }

    public static void init() {
        steamer = registerBlock(new BlockSteamer(), ItemSteamer.class);
    }
}
