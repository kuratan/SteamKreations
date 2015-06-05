package de.kuratan.steamkreations.container;

import de.kuratan.steamkreations.tileentity.TileEntitySteamer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSteamer extends Container {

    protected TileEntitySteamer tileEntitySteamer;

    public ContainerSteamer(final InventoryPlayer inventoryPlayer, final TileEntitySteamer tileEntitySteamer) {
        this.tileEntitySteamer = tileEntitySteamer;
        this.addSlotToContainer(new Slot(tileEntitySteamer, 0, 30, 40));
        if (tileEntitySteamer.getType() != TileEntitySteamer.TYPES.NORMAL) {
            this.addSlotToContainer(new Slot(tileEntitySteamer, 1, 30, 80));
            this.addSlotToContainer(new Slot(tileEntitySteamer, 2, 70, 40));
            this.addSlotToContainer(new Slot(tileEntitySteamer, 3, 70, 80));
        }

        for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 94 + inventoryRowIndex * 18));
            }
        }

        for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 152));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return tileEntitySteamer.isUseableByPlayer(entityPlayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotId) {
        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotId);

        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemStack = slotStack.copy();

            if (slotId < tileEntitySteamer.getSizeInventory()) {
                if (!this.mergeItemStack(slotStack, tileEntitySteamer.getSizeInventory(), 36+tileEntitySteamer.getSizeInventory(), true)) {
                    return null;
                }
            } else if (!this.mergeItemStack(slotStack, 0, tileEntitySteamer.getSizeInventory(), false)) {
                return null;
            }

            if (slotStack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (slotStack.stackSize == itemStack.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(entityPlayer, slotStack);
        }
        return itemStack;
    }
}
