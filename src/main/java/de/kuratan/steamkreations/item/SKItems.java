package de.kuratan.steamkreations.item;

import cpw.mods.fml.common.registry.GameRegistry;
import de.kuratan.steamkreations.SteamKreations;
import de.kuratan.steamkreations.config.Config;
import de.kuratan.steamkreations.lib.Reference;
import de.kuratan.steamkreations.utils.IInitializer;
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
        GameRegistry.registerItem(item, Reference.getUnlocalizedInternalName(item));
        return item;
    }

    public static void initialization() {
        if (Config.Items.steamedCarrot) {
            steamedCarrot = registerItem(
                    addItem(new ItemFood(6, 1.0F, false).setUnlocalizedName(Reference.modPrefix("steamed_carrot"))
                                                        .setTextureName(Reference.modPrefix("steamed_carrot"))
                                                        .setCreativeTab(SteamKreations.tab)));
            OreDictionary.registerOre("foodSteamedVegetable", steamedCarrot);
        }
        if (Config.Items.smallMeal) {
            smallMeal = registerItem(
                    addItem(new ItemFood(24, 3.5F, false).setUnlocalizedName(Reference.modPrefix("small_meal"))
                                                         .setTextureName(Reference.modPrefix("small_meal"))
                                                         .setCreativeTab(SteamKreations.tab).setHasSubtypes(true)));
        }
        if (Config.Items.chocolate) {
            chocolate = registerItem(addItem(new ItemChocolate()));
        }
    }

    public static void postInitialization() {
        if (smallMeal != null) {
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
                    new ShapedOreRecipe(new ItemStack(smallMeal, 1, 2), "MP", "PV", 'M', new ItemStack(Items.cooked_fished, 1, 1), 'P',
                                        Items.baked_potato, 'V', "foodSteamedVegetable"));
            GameRegistry.addRecipe(
                    new ShapedOreRecipe(new ItemStack(smallMeal, 1, 3), "MP", "PV", 'M', Items.cooked_porkchop, 'P',
                                        Items.baked_potato, 'V', "foodSteamedVegetable"));
        }
    }
}
