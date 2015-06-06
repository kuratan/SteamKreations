package de.kuratan.steamkreations.container;

import de.kuratan.steamkreations.tileentity.TileEntitySteamGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSteamGenerator extends Container {

    protected TileEntitySteamGenerator tileEntitySteamGenerator;

    public ContainerSteamGenerator(final InventoryPlayer inventoryPlayer, final TileEntitySteamGenerator tileEntitySteamGenerator) {
        this.tileEntitySteamGenerator = tileEntitySteamGenerator;
        this.addSlotToContainer(new Slot(tileEntitySteamGenerator, 0, 12 + 4 * 18, 8 + 1 * 18));
        this.addSlotToContainer(new Slot(tileEntitySteamGenerator, 1, 12 + 4 * 18, 8 + 3 * 18));

        for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 12 + inventoryColumnIndex * 18, 174 - (4 - inventoryRowIndex) * 18));
            }
        }

        for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 12 + actionBarSlotIndex * 18, 160));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return tileEntitySteamGenerator.isUseableByPlayer(entityPlayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotId) {
        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotId);

        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemStack = slotStack.copy();

            if (slotId < tileEntitySteamGenerator.getSizeInventory()) {
                if (!this.mergeItemStack(slotStack, tileEntitySteamGenerator.getSizeInventory(), 36+ tileEntitySteamGenerator.getSizeInventory(), true)) {
                    return null;
                }
            } else if (!this.mergeItemStack(slotStack, 0, tileEntitySteamGenerator.getSizeInventory(), false)) {
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
