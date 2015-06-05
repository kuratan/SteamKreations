package de.kuratan.steamkreations.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotSteamerTransform extends Slot {
    public SlotSteamerTransform(IInventory inventory, int slotIndex, int xDisplayPosition, int yDisplayPosition) {
        super(inventory, slotIndex, xDisplayPosition, yDisplayPosition);
    }
}
