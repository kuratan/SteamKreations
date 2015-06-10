package de.kuratan.steamkreations.utils.managers;

import cpw.mods.fml.common.registry.GameRegistry;
import de.kuratan.steamkreations.item.SKItems;
import de.kuratan.steamkreations.utils.ComparableItemStack;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChocolateManager {
    public static final String NBT_TAGLIST_KEY = "chocolateIngredients";
    private static final Map<ComparableItemStack, ChocolateIngredient> ingredients =
            new HashMap<ComparableItemStack, ChocolateIngredient>();

    public static void initialize() {
        ingredients.clear();
        initializeVanilla();
        initializeMod();
        System.out.println("Knowing " + ingredients.size() + " additions to chocolate");
    }

    public static void initializeVanilla() {
        addIngredient(new ItemStack(Items.apple), 0, 0.5F);
        addIngredient(new ItemStack(Items.golden_apple, 1, 0), 2, 4.0F);
        addIngredient(new ItemStack(Items.golden_apple, 1, 1), 4, 8.0F);
    }

    public static void initializeMod() {
        GameRegistry.addSmelting(Items.apple, new ItemStack(SKItems.chocolate), 0);
    }

    public static void addIngredient(ItemStack itemStack, ChocolateIngredient chocolateIngredient) {
        ingredients.put(new ComparableItemStack(itemStack), chocolateIngredient);
    }

    public static void addIngredient(ItemStack itemStack, int healAmount, float saturationModifier) {
        addIngredient(itemStack,
                      new ChocolateIngredient(itemStack.getItem()).setHealAmount(healAmount)
                                                                  .setSaturationModifier(saturationModifier));
    }

    public static void addIgredientToItemStack(ItemStack itemStack, ChocolateIngredient ingredient) {
        List<ChocolateIngredient> list = getIngredientsFromItemStack(itemStack);
        list.add(ingredient);
        setIngredientsToItemStack(itemStack, list);
    }

    public static ChocolateIngredient getIngredient(ItemStack itemStack) {
        return ingredients.get(new ComparableItemStack(itemStack));
    }

    public static int getHealAmountFromItemStack(ItemStack itemStack) {
        int healAmount = 0;
        for (ChocolateIngredient ingredient : getIngredientsFromItemStack(itemStack)) {
            healAmount += ingredient.getHealAmount();
        }
        return healAmount;
    }

    public static float getSaturationModifierItemStack(ItemStack itemStack) {
        float saturationModifier = 0.0F;
        for (ChocolateIngredient ingredient : getIngredientsFromItemStack(itemStack)) {
            saturationModifier += ingredient.getSaturationModifier();
        }
        return saturationModifier;
    }

    public static List<ChocolateIngredient> getIngredientsFromItemStack(ItemStack itemStack) {
        ArrayList<ChocolateIngredient> ingredients = new ArrayList<ChocolateIngredient>();
        NBTTagCompound root = itemStack.stackTagCompound;
        if (root == null) {
            root = new NBTTagCompound();
        }
        if (!root.hasKey(NBT_TAGLIST_KEY)) {
            root.setTag(NBT_TAGLIST_KEY, new NBTTagList());
        }
        NBTTagList list = root.getTagList(NBT_TAGLIST_KEY, 10);
        for (int i = 0; i < list.tagCount(); i++) {
            ChocolateIngredient ingredient = new ChocolateIngredient();
            ingredient.readFromNBT(list.getCompoundTagAt(i));
            ingredients.add(ingredient);
        }
        return ingredients;
    }

    public static void setIngredientsToItemStack(ItemStack itemStack, List<ChocolateIngredient> ingredients) {
        NBTTagCompound root = itemStack.stackTagCompound;
        if (root == null) {
            root = new NBTTagCompound();
        }
        NBTTagList list = root.getTagList(NBT_TAGLIST_KEY, 10);
        for (ChocolateIngredient ingredient : ingredients) {
            NBTTagCompound tag = new NBTTagCompound();
            ingredient.writeToNBT(tag);
            list.appendTag(tag);
        }
        root.setTag(NBT_TAGLIST_KEY, list);
        itemStack.stackTagCompound = root;
    }
}
