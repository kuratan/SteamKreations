package de.kuratan.steamkreations.item;

import de.kuratan.steamkreations.SteamKreations;
import de.kuratan.steamkreations.utils.ModReference;
import de.kuratan.steamkreations.utils.managers.ChocolateIngredient;
import de.kuratan.steamkreations.utils.managers.ChocolateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
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
        setUnlocalizedName(ModReference.modPrefix("chocolate"));
        setTextureName(ModReference.modPrefix("chocolate"));
        setCreativeTab(SteamKreations.tab);
        setMaxStackSize(16);
        setAlwaysEdible();
    }

    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        itemStack.stackTagCompound = new NBTTagCompound();
        if ((int) (Math.random() * 10) % 2 == 0) {
            ChocolateManager
                    .addIgredientToItemStack(itemStack, ChocolateManager.getIngredient(new ItemStack(Items.apple)));
        } else {
            ChocolateManager.addIgredientToItemStack(itemStack, ChocolateManager
                    .getIngredient(new ItemStack(Items.golden_apple, 1, 1)));
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
        list.add(EnumChatFormatting.GRAY + "HealAmount: " + func_150905_g(itemStack));
        list.add(EnumChatFormatting.GRAY + "SaturationModifier: " + func_150906_h(itemStack));
    }
}
