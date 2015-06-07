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

    private static final Map<ComparableItemStack, ComparableItemStack> entries = new HashMap<ComparableItemStack, ComparableItemStack>();
    private static final Map<ComparableItemStack, Integer> durations = new HashMap<ComparableItemStack, Integer>();

    public static void init() {
        System.out.println("Init steamer recipes");
        entries.clear();
        for (Object me : FurnaceRecipes.smelting().getSmeltingList().entrySet()) {
            Map.Entry<ItemStack, ItemStack> entry = (Map.Entry<ItemStack, ItemStack>)me;
            if (entry.getKey().getItem() instanceof ItemFishFood) {
                addRecipe(entry.getKey(), entry.getValue(), 200);
            }
        }
        addRecipe(Items.poisonous_potato, Items.potato, 200);
        addRecipe(Items.potato, Items.baked_potato, 200);
    }

    public static void addRecipe(ItemStack input, ItemStack output, int duration) {
        System.out.println("Adding: " + input + " -> " + output + " | " + duration);
        entries.put(new ComparableItemStack(input), new ComparableItemStack(output));
        durations.put(new ComparableItemStack(input), duration);
    }

    public static void addRecipe(Item input, Item output, int duration) {
        addRecipe(new ItemStack(input, 1), new ItemStack(output, 1), duration);
    }

    public static ItemStack getResult(ItemStack input) {
        return entries.get(input).toItemStack();
    }

    public static int getDuration(ItemStack input) {
        return durations.get(input);
    }

    public static boolean isValidInputItemStack(ItemStack itemStack) {
        return entries.containsKey(itemStack);
    }

    public static boolean isValidOutputItemStack(ItemStack itemStack) {
        return entries.containsValue(itemStack);
    }

    public static boolean isValidItemStack(ItemStack itemStack) {
        return isValidInputItemStack(itemStack) || isValidOutputItemStack(itemStack);
    }
}
