package de.kuratan.steamkreations.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemBlock;

public class SKBlocks {
    public static SKBlock steamer;

    public static SKBlock registerBlock(final SKBlock block, final Class<? extends ItemBlock> itemblock) {
        return (SKBlock)GameRegistry.registerBlock(block, (Class)itemblock, block.getUnlocalizedInternalName());
    }

    public static void init() {
        steamer = registerBlock(new BlockSteamer(), ItemBlock.class);
    }
}
