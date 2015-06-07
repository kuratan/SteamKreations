package de.kuratan.steamkreations.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockWithSubtypes extends ItemBlock implements ISKItem {
    public ItemBlockWithSubtypes(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int metadata) {
        return metadata;
    }
}
