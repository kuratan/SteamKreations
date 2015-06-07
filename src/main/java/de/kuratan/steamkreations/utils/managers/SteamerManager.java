package de.kuratan.steamkreations.utils.managers;

import de.kuratan.steamkreations.utils.ComparableItemStack;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import java.util.HashMap;
import java.util.Map;

public class SteamerManager {

    private static final Map<ComparableItemStack, SteamerRecipe> recipes =
            new HashMap<ComparableItemStack, SteamerRecipe>();

    public static void init() {
        System.out.println("Init steamer recipes");
        recipes.clear();
        for (Object me : FurnaceRecipes.smelting().getSmeltingList().entrySet()) {
            Map.Entry<ItemStack, ItemStack> entry = (Map.Entry<ItemStack, ItemStack>) me;
            if (entry.getKey().getItem() instanceof ItemFishFood) {
                addRecipe(entry.getKey(), entry.getValue(), 200);
            }
        }
        addRecipe(Items.poisonous_potato, Items.potato, 200);
        addRecipe(Items.potato, Items.baked_potato, 200);
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

