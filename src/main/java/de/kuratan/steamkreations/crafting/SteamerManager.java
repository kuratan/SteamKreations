package de.kuratan.steamkreations.crafting;

import de.kuratan.steamkreations.item.SKItems;
import de.kuratan.steamkreations.utils.ComparableItemStack;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class SteamerManager {

    private static final Map<ComparableItemStack, SteamerRecipe> recipes =
            new HashMap<ComparableItemStack, SteamerRecipe>();

    public static void initialization() {
        recipes.clear();
        initializeVanilla();
        initializeMod();
    }

    public static void initializeVanilla() {
        addRecipe(new ItemStack(Items.fish, 1, ItemFishFood.FishType.COD.func_150976_a()),
                  new ItemStack(Items.cooked_fished, 1, ItemFishFood.FishType.COD.func_150976_a()), 200);
        addRecipe(new ItemStack(Items.fish, 1, ItemFishFood.FishType.SALMON.func_150976_a()),
                  new ItemStack(Items.cooked_fished, 1, ItemFishFood.FishType.SALMON.func_150976_a()), 200);
        addRecipe(Items.poisonous_potato, Items.potato, 240);
        addRecipe(Items.potato, Items.baked_potato, 200);
    }

    public static void initializeMod() {
        addRecipe(Items.carrot, SKItems.steamedCarrot, 160);
    }

    public static void addRecipe(ItemStack input, ItemStack output, int duration) {
        recipes.put(new ComparableItemStack(input), new SteamerRecipe(input, output, duration));
    }

    public static void addRecipe(Item input, Item output, int duration) {
        addRecipe(new ItemStack(input, 1), new ItemStack(output, 1), duration);
    }

    public static SteamerRecipe getRecipe(ItemStack input) {
        return recipes.get(new ComparableItemStack(input));
    }

    public static boolean isValidInputItemStack(ItemStack itemStack) {
        ComparableItemStack comparableItemStack = new ComparableItemStack(itemStack);
        return recipes.containsKey(comparableItemStack);
    }
}

