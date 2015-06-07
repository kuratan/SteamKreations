package de.kuratan.steamkreations.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ComparableItem {
    protected Item item;
    protected int damage;

    public ComparableItem(Item item, int damage) {
        this.item = item;
        this.damage = damage;
    }

    public ComparableItem(ItemStack itemStack) {
        this(itemStack.getItem(), itemStack.getItemDamage());
    }

    public Item getItem() {
        return item;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isEqualItem(ComparableItem o) {
        if (this.item.equals(o.getItem())) {
            return true;
        }
        if (this.item.delegate.get().equals(o.getItem().delegate.get())) {
            return true;
        }
        return false;
    }

    public boolean isEqual(ComparableItem o) {
        if (this.damage == o.getDamage()) {
            return isEqualItem(o);
        }
        return false;
    }
}
