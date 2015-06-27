package de.kuratan.steamkreations.block.steamgenerator;

import de.kuratan.steamkreations.SteamKreations;
import de.kuratan.steamkreations.item.ItemBlockWithSubtypes;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockSteamGenerator extends ItemBlockWithSubtypes {
    public ItemBlockSteamGenerator(Block block) {
        super(block);
        this.setCreativeTab(SteamKreations.tab);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName(itemStack) + "." + TileEntitySteamGenerator.TYPES.values()[itemStack.getItemDamage()];
    }
}
