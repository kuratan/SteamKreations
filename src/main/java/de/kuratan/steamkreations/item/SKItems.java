package de.kuratan.steamkreations.item;

import cpw.mods.fml.common.registry.GameRegistry;
import de.kuratan.steamkreations.SteamKreations;
import de.kuratan.steamkreations.utils.IInitializer;
import de.kuratan.steamkreations.utils.ModReference;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;

public class SKItems {
    public static ArrayList<IInitializer> items = new ArrayList<IInitializer>();
    public static Item steamedCarrot;
    public static Item smallMeal;
    public static Item chocolate;

    public static Item addItem(Item item) {
        if (item instanceof IInitializer) {
            items.add((IInitializer) item);
        }
        return item;
    }

    public static Item registerItem(Item item) {
        GameRegistry.registerItem(item, ModReference.getUnlocalizedInternalName(item));
        return item;
    }

    public static void initialization() {
        steamedCarrot = registerItem(
                addItem(new ItemFood(6, 1.0F, false).setUnlocalizedName(ModReference.modPrefix("steamed_carrot"))
                                                    .setTextureName(ModReference.modPrefix("steamed_carrot"))
                                                    .setCreativeTab(SteamKreations.tab)));
        smallMeal = registerItem(
                addItem(new ItemFood(10, 3.5F, false).setUnlocalizedName(ModReference.modPrefix("small_meal"))
                                                     .setTextureName(ModReference.modPrefix("small_meal"))
                                                     .setCreativeTab(SteamKreations.tab).setHasSubtypes(true)));
        chocolate = registerItem(addItem(new ItemChocolate()));
        OreDictionary.registerOre("foodSteamedVegetable", steamedCarrot);
    }

    public static void postInitialization() {
        GameRegistry.addRecipe(
                new ShapedOreRecipe(new ItemStack(smallMeal, 1, 0), "MP", "PV", 'M', Items.cooked_beef, 'P',
                                    Items.baked_potato, 'V', "foodSteamedVegetable"));
        GameRegistry.addRecipe(
                new ShapedOreRecipe(new ItemStack(smallMeal, 1, 1), "MP", "PV", 'M', Items.cooked_chicken, 'P',
                                    Items.baked_potato, 'V', "foodSteamedVegetable"));
        GameRegistry.addRecipe(
                new ShapedOreRecipe(new ItemStack(smallMeal, 1, 2), "MP", "PV", 'M', Items.cooked_fished, 'P',
                                    Items.baked_potato, 'V', "foodSteamedVegetable"));
        GameRegistry.addRecipe(
                new ShapedOreRecipe(new ItemStack(smallMeal, 1, 3), "MP", "PV", 'M', Items.cooked_porkchop, 'P',
                                    Items.baked_potato, 'V', "foodSteamedVegetable"));
    }
}
