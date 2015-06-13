package de.kuratan.steamkreations.item;

import de.kuratan.steamkreations.SteamKreations;
import de.kuratan.steamkreations.lib.Reference;
import de.kuratan.steamkreations.crafting.ChocolateIngredient;
import de.kuratan.steamkreations.crafting.ChocolateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class ItemChocolate extends ItemFood {
    public ItemChocolate() {
        super(0, 0F, false);
        setUnlocalizedName(Reference.modPrefix("chocolate"));
        setTextureName(Reference.modPrefix("chocolate"));
        setCreativeTab(SteamKreations.tab);
        setMaxStackSize(16);
        setAlwaysEdible();
    }

    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (itemStack.stackTagCompound == null) {
            itemStack.stackTagCompound = new NBTTagCompound();
        }
    }

    @Override
    public int func_150905_g(ItemStack itemStack) {
        return ChocolateManager.getHealAmountFromItemStack(itemStack);
    }

    @Override
    public float func_150906_h(ItemStack itemStack) {
        return ChocolateManager.getSaturationModifierItemStack(itemStack);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool) {
        if (itemStack.stackTagCompound == null) {
            return;
        }
        list.add(EnumChatFormatting.DARK_GRAY + "Chocolate info:");
        for (ChocolateIngredient ingredient : ChocolateManager.getIngredientsFromItemStack(itemStack)) {
            list.add(EnumChatFormatting.AQUA +
                     StatCollector.translateToLocal(ingredient.getItem().getUnlocalizedName() + ".name"));
        }
        list.add(EnumChatFormatting.GRAY + String.format("HealAmount: %d", func_150905_g(itemStack)));
        list.add(EnumChatFormatting.GRAY + String.format("SaturationModifier: %.1f", func_150906_h(itemStack)));
    }
}
