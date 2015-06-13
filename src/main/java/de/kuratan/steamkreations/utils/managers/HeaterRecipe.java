package de.kuratan.steamkreations.utils.managers;

import de.kuratan.steamkreations.container.ContainerHeater;
import de.kuratan.steamkreations.item.ItemChocolate;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
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

    public void handleInventory(IInventory inventory) {
        ItemStack inputSlotStack = inventory.getStackInSlot(ContainerHeater.SLOTS.INPUT.getId());
        if (inputSlotStack != null &&
            inputSlotStack.getItem() instanceof ItemChocolate) {
            ChocolateManager
                    .addIgredientToItemStack(inputSlotStack, ChocolateManager.getIngredient(this.getOutput()));
            inventory.setInventorySlotContents(ContainerHeater.SLOTS.OUTPUT.getId(), inputSlotStack);
        } else {
            inventory.setInventorySlotContents(ContainerHeater.SLOTS.OUTPUT.getId(), this.getOutput());
        }
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            if (i == ContainerHeater.SLOTS.OUTPUT.getId()) {
                continue;
            }
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (itemStack == null) {
                continue;
            }
            Item containerItem = itemStack.getItem().getContainerItem();
            if (containerItem != null) {
                inventory.setInventorySlotContents(i, new ItemStack(containerItem, itemStack.stackSize));
            } else {
                inventory.setInventorySlotContents(i, null);
            }
        }
    }

    @Override
    public String toString() {
        return "[" + input + "," + additions + "->" + output + "@" + duration + "]";
    }
}
