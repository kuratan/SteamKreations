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
        return this.item.delegate.get().equals(o.getItem().delegate.get());
    }

    public boolean isEqual(ComparableItem o) {
        return this.damage == o.getDamage() && isEqualItem(o);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ComparableItem && this.isEqual((ComparableItem) obj);
    }

    @Override
    public int hashCode() {
        // Damage is 16bit -> shift id by 16 to combine
        return (0xFFFF | damage) | Item.getIdFromItem(item) << 16;
    }

    @Override
    public String toString() {
        return this.item.getUnlocalizedName() + ":" + this.damage;
    }
}
