package de.kuratan.steamkreations.item;

import net.minecraft.block.Block;

public class ItemSteamGenerator extends SKItemBlock {
    public ItemSteamGenerator(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int metadata) {
        return metadata;
    }
}
