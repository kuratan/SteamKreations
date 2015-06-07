package de.kuratan.steamkreations.item;

import de.kuratan.steamkreations.SteamKreations;
import de.kuratan.steamkreations.utils.ModReference;
import net.minecraft.item.ItemFood;

public class ItemSteamedCarrot extends ItemFood {
    public ItemSteamedCarrot() {
        this(3, 2.0f, false);
    }

    public ItemSteamedCarrot(int healAmount, float saturationModifier, boolean isWolfsFavoriteMeat) {
        super(healAmount, saturationModifier, isWolfsFavoriteMeat);
        this.setUnlocalizedName(ModReference.modPrefix("steamed_carrot"));
        this.setTextureName(ModReference.modPrefix("steamed_carrot"));
        this.setMaxDamage(0);
        this.setCreativeTab(SteamKreations.tab);
    }
}
