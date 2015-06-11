package de.kuratan.steamkreations.block.heater;

import de.kuratan.steamkreations.SteamKreations;
import de.kuratan.steamkreations.block.steamer.TileEntitySteamer;
import de.kuratan.steamkreations.item.ItemBlockWithSubtypes;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockHeater extends ItemBlockWithSubtypes {
    public ItemBlockHeater(Block block) {
        super(block);
        this.setCreativeTab(SteamKreations.tab);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName(itemStack) + "." + TileEntityHeater.TYPES.values()[itemStack.getItemDamage()];
    }
}
