package de.kuratan.steamkreations.item;

import net.minecraft.block.Block;

public class ItemSteamer extends SKItemBlock {
    public ItemSteamer(Block p_i45328_1_) {
        super(p_i45328_1_);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int metadata) {
        return metadata;
    }
}
