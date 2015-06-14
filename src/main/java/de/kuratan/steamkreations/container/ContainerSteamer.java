package de.kuratan.steamkreations.container;

import de.kuratan.steamkreations.block.steamer.TileEntitySteamer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSteamer extends Container {

    public enum SLOTS {
        SMALL(0, 12 + 4 * 18, 8 + 2 * 18),
        BIG1(0, 3 + 4 * 18, 17 + 18),
        BIG2(1, 3 + 5 * 18, 17 + 18),
        BIG3(2, 3 + 4 * 18, 17 + 2 * 18),
        BIG4(3, 3 + 5 * 18, 17 + 2 * 18);

        private int id;
        private int x;
        private int y;

        SLOTS(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        public int getId() {
            return id;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    protected TileEntitySteamer tileEntitySteamer;

    public ContainerSteamer(final InventoryPlayer inventoryPlayer, final TileEntitySteamer tileEntitySteamer) {
        this.tileEntitySteamer = tileEntitySteamer;
        if (tileEntitySteamer.getType() != TileEntitySteamer.TYPES.NORMAL) {
            this.addSlotToContainer(new SlotSteamer(tileEntitySteamer, SLOTS.BIG1));
            this.addSlotToContainer(new SlotSteamer(tileEntitySteamer, SLOTS.BIG2));
            this.addSlotToContainer(new SlotSteamer(tileEntitySteamer, SLOTS.BIG3));
            this.addSlotToContainer(new SlotSteamer(tileEntitySteamer, SLOTS.BIG4));
        } else {
            this.addSlotToContainer(new SlotSteamer(tileEntitySteamer, SLOTS.SMALL));
        }

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
                if (!this.mergeItemStack(slotStack, tileEntitySteamer.getSizeInventory(),
                                         36 + tileEntitySteamer.getSizeInventory(), true)) {
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

    class SlotSteamer extends Slot {

        public SlotSteamer(IInventory p_i1824_1_, SLOTS slot) {
            super(p_i1824_1_, slot.getId(), slot.getX(), slot.getY());
        }

        @Override
        public void onSlotChanged() {
            super.onSlotChanged();
            tileEntitySteamer.calculateSlotData(this.slotNumber);
        }
    }
}
