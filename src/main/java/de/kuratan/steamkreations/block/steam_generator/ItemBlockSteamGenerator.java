package de.kuratan.steamkreations.block.steam_generator;

import de.kuratan.steamkreations.item.ItemBlockWithSubtypes;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockSteamGenerator extends ItemBlockWithSubtypes {
    public ItemBlockSteamGenerator(Block block) {
        super(block);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName(itemStack) + "." + TileEntitySteamGenerator.TYPES.values()[itemStack.getItemDamage()];
    }
}
