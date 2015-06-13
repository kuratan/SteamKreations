package de.kuratan.steamkreations.utils.managers;

import de.kuratan.steamkreations.item.SKItems;
import de.kuratan.steamkreations.utils.ComparableItemStack;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.*;

public class HeaterManager {
    private static final Map<HeaterRecipeKey, HeaterRecipe> recipes = new HashMap<HeaterRecipeKey, HeaterRecipe>();

    public static void initialization() {
        recipes.clear();
        initializeVanilla();
        initializeMod();
        System.out.println("Knowing " + recipes.size() + " recipes for heating");
    }

    public static void initializeVanilla() {

    }

    public static void initializeMod() {
        List<ItemStack> additions = new ArrayList<ItemStack>();
        additions.add(new ItemStack(Items.sugar));
        additions.add(new ItemStack(Items.dye, 1, 3));
        additions.add(new ItemStack(Items.milk_bucket));
        addRecipe(null, additions, new ItemStack(SKItems.chocolate), 20);
    }

    public static void addRecipe(ItemStack input, List<ItemStack> additions, ItemStack output, int duration) {
        recipes.put(new HeaterRecipeKey(input, additions), new HeaterRecipe(input, additions, output, duration));
    }

    public static HeaterRecipe getRecipe(ItemStack[] inventory) {
        List<ItemStack> additions = new LinkedList<ItemStack>();
        for (int i = 1; i < inventory.length; i++) {
            if (inventory[i] != null) {
                additions.add(inventory[i]);
            }
        }
        return getRecipe(inventory[0], additions);
    }

    public static HeaterRecipe getRecipe(ItemStack input, List<ItemStack> additions) {
        return recipes.get(new HeaterRecipeKey(input, additions));
    }
}

class HeaterRecipeKey {
    protected ItemStack key;
    protected List<ComparableItemStack> additions;

    public HeaterRecipeKey(ItemStack key, List<ItemStack> keys) {
        this.key = key;
        this.additions = new ArrayList<ComparableItemStack>();
        for (ItemStack itemStack : keys) {
            this.additions.add(new ComparableItemStack(itemStack));
        }
        this.additions.sort(new Comparator<ComparableItemStack>() {
            @Override
            public int compare(ComparableItemStack o1, ComparableItemStack o2) {
                if (o1.getItem() == o2.getItem()) {
                    if (o1.isEqual(o2)) {
                        return 0;
                    } else {
                        return o1.toItemStack().getItemDamage() - o2.toItemStack().getItemDamage();
                    }
                } else {
                    return Item.getIdFromItem(o1.getItem()) - Item.getIdFromItem(o2.getItem());
                }
            }
        });
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("{" + (key != null ? key.toString() : "null" + "|"));
        for (ComparableItemStack addition : additions) {
            sb.append(addition + ",");
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HeaterRecipeKey) {
            HeaterRecipeKey hrk = (HeaterRecipeKey) obj;
            if (this.key == hrk.key && this.additions.size() == hrk.additions.size()) {
                for (int i = 0; i < additions.size(); i++) {
                    if (!additions.get(i).isEqual(hrk.additions.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        if (this.key != null) {
            // Damage is 16bit -> shift id by 16 to combine
            hash += (0xFFFF | key.getItemDamage()) | Item.getIdFromItem(key.getItem()) << 16;
        }
        return hash;
    }
}
