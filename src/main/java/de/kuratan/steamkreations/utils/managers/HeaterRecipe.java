package de.kuratan.steamkreations.utils.managers;

import net.minecraft.item.ItemStack;

import java.util.List;

public class HeaterRecipe {
    protected ItemStack input;
    protected List<ItemStack> additions;
    protected ItemStack output;
    protected int duration;

    public HeaterRecipe(ItemStack input, List<ItemStack> additions, ItemStack output, int duration) {
        this.input = input;
        this.additions = additions;
        this.output = output;
        this.duration = duration;

        if (input != null && input.stackSize < 1) {
            this.input.stackSize = 1;
        }

        if (additions != null) {
            for (ItemStack addition: additions) {
                if (addition.stackSize < 1) {
                    addition.stackSize = 1;
                }
            }
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

    @Override
    public String toString() {
        return "[" + input + "," + additions + "->" + output + "@" + duration + "]";
    }
}
