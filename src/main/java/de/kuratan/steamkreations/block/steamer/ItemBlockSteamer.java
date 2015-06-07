package de.kuratan.steamkreations.block.steamer;

import de.kuratan.steamkreations.item.ItemBlockWithSubtypes;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockSteamer extends ItemBlockWithSubtypes {
    public ItemBlockSteamer(Block block) {
        super(block);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName(itemStack) + "." + TileEntitySteamer.TYPES.values()[itemStack.getItemDamage()];
    }
}
