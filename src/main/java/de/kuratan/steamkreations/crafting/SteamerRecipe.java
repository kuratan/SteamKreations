package de.kuratan.steamkreations.crafting;

import net.minecraft.item.ItemStack;

public class SteamerRecipe {
    protected ItemStack input;
    protected ItemStack output;
    protected int duration;

    public SteamerRecipe(ItemStack input, ItemStack output, int duration) {
        this.input = input;
        this.output = output;
        this.duration = duration;

        if (input.stackSize < 1) {
            this.input.stackSize = 1;
        }

        if (output.stackSize < 1) {
            this.output.stackSize = 1;
        }
    }

    public int getDuration() {
        return duration;
    }

    public ItemStack getOutput() {
        return output;
    }
}
