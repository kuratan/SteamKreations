package de.kuratan.steamkreations.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class SKBlocks {
    public static Block steamer;

    public static Block registerBlock(final SKBlock block, final Class<? extends ItemBlock> itemblock) {
        return GameRegistry.registerBlock(block, (Class)itemblock, block.getUnlocalizedInternalName());
    }

    public static void init() {
        steamer = registerBlock(new BlockSteamer(), ItemBlock.class);
    }
}
