package de.kuratan.steamkreations.container;

import de.kuratan.steamkreations.block.heater.TileEntityHeater;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerHeater extends Container {

    public enum SLOTS {
        INPUT(3 + 2 * 18, 8 + 2 * 18),
        ADDITION1(3 + 4 * 18, 17 + 18),
        ADDITION2(3 + 5 * 18, 17 + 18),
        ADDITION3(3 + 4 * 18, 17 + 2 * 18),
        ADDITION4(3 + 5 * 18, 17 + 2 * 18),
        OUTPUT(3 + 7 * 18, 8 + 2 * 18);

        private int x;
        private int y;

        SLOTS(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getId() {
            return ordinal();
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    protected TileEntityHeater tileEntityHeater;

    public ContainerHeater(final InventoryPlayer inventoryPlayer, final TileEntityHeater tileEntityHeater) {
        this.tileEntityHeater = tileEntityHeater;
        this.addSlotToContainer(new SlotHeater(tileEntityHeater, SLOTS.INPUT));
        this.addSlotToContainer(new SlotHeater(tileEntityHeater, SLOTS.ADDITION1));
        this.addSlotToContainer(new SlotHeater(tileEntityHeater, SLOTS.ADDITION2));
        this.addSlotToContainer(new SlotHeater(tileEntityHeater, SLOTS.ADDITION3));
        this.addSlotToContainer(new SlotHeater(tileEntityHeater, SLOTS.ADDITION4));
        this.addSlotToContainer(new SlotHeater(tileEntityHeater, SLOTS.OUTPUT));

        for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9,
                                                 12 + inventoryColumnIndex * 18, 174 - (4 - inventoryRowIndex) * 18));
            }
        }

        for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex) {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 12 + actionBarSlotIndex * 18, 160));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return tileEntityHeater.isUseableByPlayer(entityPlayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotId) {
        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotId);

        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemStack = slotStack.copy();

            if (slotId < tileEntityHeater.getSizeInventory()) {
                if (!this.mergeItemStack(slotStack, tileEntityHeater.getSizeInventory(),
                                         36 + tileEntityHeater.getSizeInventory(), true)) {
                    return null;
                }
            } else if (!this.mergeItemStack(slotStack, 0, tileEntityHeater.getSizeInventory(), false)) {
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

    class SlotHeater extends Slot {

        public SlotHeater(IInventory p_i1824_1_, SLOTS slots) {
            super(p_i1824_1_, slots.getId(), slots.getX(), slots.getY());
        }

        @Override
        public void onSlotChanged() {
            super.onSlotChanged();
            tileEntityHeater.calculateSlotData(this.slotNumber);
        }
    }
}
