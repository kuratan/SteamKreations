package de.kuratan.steamkreations.utils;

import net.minecraft.item.ItemStack;

public class ComparableItemStack extends ComparableItem {
    protected int stackSize;

    public ComparableItemStack(ItemStack itemStack) {
        super(itemStack);
        stackSize = itemStack.stackSize;
    }

    public int getStackSize() {
        return stackSize;
    }

    public boolean isEqualStack(ComparableItemStack o) {
        return super.isEqual(o) && stackSize == o.getStackSize();
    }

    public ItemStack toItemStack() {
        return new ItemStack(this.getItem(), this.getStackSize(), this.getDamage());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ComparableItemStack && this.isEqual((ComparableItemStack) obj);
    }
}
