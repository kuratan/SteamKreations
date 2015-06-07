package de.kuratan.steamkreations.item;

import cpw.mods.fml.common.registry.GameRegistry;
import de.kuratan.steamkreations.utils.IInitializer;
import de.kuratan.steamkreations.utils.ModReference;
import net.minecraft.item.Item;

import java.util.ArrayList;

public class SKItems {
    public static ArrayList<IInitializer> items = new ArrayList<IInitializer>();
    public static Item steamedCarrot;

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

    public static void init() {
        steamedCarrot = registerItem(addItem(new ItemSteamedCarrot()));
    }
}
