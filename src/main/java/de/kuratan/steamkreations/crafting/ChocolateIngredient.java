package de.kuratan.steamkreations.crafting;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;

public class ChocolateIngredient {
    protected Item item;
    protected int healAmount = 0;
    protected float saturationModifier = 0;

    protected ChocolateIngredient() {}

    public ChocolateIngredient(Item item) {
        setItem(item);
    }

    public Item getItem() {
        return item;
    }

    public int getHealAmount() {
        return healAmount;
    }

    public float getSaturationModifier() {
        return saturationModifier;
    }

    private ChocolateIngredient setItem(Item item) {
        this.item = item;
        return this;
    }

    public ChocolateIngredient setHealAmount(int healAmount) {
        this.healAmount = healAmount;
        return this;
    }

    public ChocolateIngredient setSaturationModifier(float saturationModifier) {
        this.saturationModifier = saturationModifier;
        return this;
    }

    public void writeToNBT(NBTTagCompound tag) {
        tag.setInteger("item", Item.getIdFromItem(getItem()));
        tag.setInteger("healAmount", getHealAmount());
        tag.setFloat("saturationModifier", getSaturationModifier());
    }

    public void readFromNBT(NBTTagCompound tag) {
        setItem(Item.getItemById(tag.getInteger("item")));
        setHealAmount(tag.getInteger("healAmount"));
        setSaturationModifier(tag.getFloat("saturationModifier"));
    }
}
