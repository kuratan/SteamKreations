package de.kuratan.steamkreations.block.steamer;

import de.kuratan.steamkreations.crafting.SteamerManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.TileFluidHandler;

/**
 * TileEntity representing a basic steamer.
 */
public class TileEntitySteamer extends TileFluidHandler implements ISidedInventory, IFluidHandler {

    public enum TYPES {
        NORMAL(1, 4000, 20), REINFORCED(4, 8000, 40), CREATIVE(4, -1, 40);

        private int steamCapacity;
        private int steamPerTick;
        private int slots;

        TYPES(int slots, int steamCapacity, int steamPerTick) {
            this.slots = slots;
            this.steamCapacity = steamCapacity;
            this.steamPerTick = steamPerTick;
        }

        /**
         * @return Number of slots.
         */
        public int getSlots() {
            return slots;
        }

        /**
         * @return Internal tank capacity in milibuckets.
         */
        public int getSteamCapacity() {
            return steamCapacity;
        }

        /**
         * @return Used steam in milibuckets per tick.
         */
        public int getSteamPerTick() {
            return steamPerTick;
        }
    }

    protected TYPES type;
    protected ItemStack[] inventory;
    protected int[] itemCookTime;

    public TileEntitySteamer() {
        super();
    }

    /**
     * Updates internal paramaters using the values from the provided type.
     *
     * @param type
     */
    public void setType(TYPES type) {
        this.type = type;
        this.tank = new FluidTank(type.getSteamCapacity());
        this.inventory = new ItemStack[type.getSlots()];
        this.itemCookTime = new int[type.getSlots()];
    }

    public TYPES getType() {
        return type;
    }

    /**
     * Serializes important data into NBT. Slots of the internal inventory are only saved if not empty.
     *
     * @param tag
     */
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("type", this.getType().ordinal());
        tag.setIntArray("cookTime", this.itemCookTime);
        NBTTagList tagList = new NBTTagList();
        for (int i = 0; i < getSizeInventory(); i++) {
            if (inventory[i] != null) {
                NBTTagCompound slot = new NBTTagCompound();
                slot.setByte("slot", (byte) i);
                inventory[i].writeToNBT(slot);
                tagList.appendTag(slot);
            }
        }
        tag.setTag(this.getInventoryName(), tagList);
    }

    /**
     * Restores serialized data from NBT.
     *
     * @param tag
     */
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.setType(TYPES.values()[tag.getInteger("type")]);
        this.itemCookTime = tag.getIntArray("cookTime");
        NBTTagList tagList = tag.getTagList(this.getInventoryName(), 10);
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound slot = tagList.getCompoundTagAt(i);
            byte index = slot.getByte("slot");
            if (index >= 0 && index < getSizeInventory()) {
                inventory[index] = ItemStack.loadItemStackFromNBT(slot);
            }
        }
    }

    /**
     * Do not allow output of fluid.
     *
     * @param from
     * @param fluid
     * @return false
     */
    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return false;
    }

    /**
     * Allow steam input from the bottom.
     *
     * @param from
     * @param fluid
     * @return
     */
    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return from.equals(ForgeDirection.DOWN) && super.canFill(from, fluid);
    }

    /**
     * Bottom is fluid only, all other sides can access all slots.
     *
     * @param iside
     * @return
     */
    @Override
    public int[] getAccessibleSlotsFromSide(int iside) {
        ForgeDirection side = ForgeDirection.getOrientation(iside);
        switch (side) {
            case DOWN:
                return new int[0];
            default:
                if (this.inventory.length == 2) {
                    return new int[]{0, 1};
                }
                return new int[]{0, 1, 2, 3};
        }
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, int iside) {
        ForgeDirection side = ForgeDirection.getOrientation(iside);
        return side != ForgeDirection.DOWN && isItemValidForSlot(slot, itemStack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, int iside) {
        ForgeDirection side = ForgeDirection.getOrientation(iside);
        return side != ForgeDirection.DOWN;
    }


    @Override
    public int getSizeInventory() {
        return this.inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot < getSizeInventory()) {
            return this.inventory[slot];
        }
        return null;
    }

    /**
     * Decrements slot using setInventorySlotContents.
     * @param slot
     * @param decrement
     * @return
     */
    @Override
    public ItemStack decrStackSize(int slot, int decrement) {
        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null) {
            if (itemStack.stackSize <= decrement) {
                setInventorySlotContents(slot, null);
            } else {
                itemStack = itemStack.splitStack(decrement);
                if (itemStack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return itemStack;
    }

    /**
     * @param slot
     * @return
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null) {
            setInventorySlotContents(slot, null);
        }
        return itemStack;
    }

    /**
     * Sets contents of slot and recalculates data.
     * @param slot
     * @param itemStack
     */
    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        if (slot < getSizeInventory()) {
            this.inventory[slot] = itemStack;
            if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
                itemStack.stackSize = getInventoryStackLimit();
            }
            calculateSlotData(slot);
        }
    }

    @Override
    public String getInventoryName() {
        return "steamer";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && entityPlayer.getDistanceSq(
                (double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return false;
    }

    /**
     * Consume steam and cook items if there cooking time has finished.
     */
    @Override
    public void updateEntity() {
        if (tank.getFluidAmount() >= type.getSteamPerTick() || this.type == TYPES.CREATIVE) {
            tank.drain(type.getSteamPerTick(), true);

            for (int i = 0; i < this.getSizeInventory(); i++) {
                if (this.itemCookTime[i] > 0) {
                    this.itemCookTime[i]--;

                    if (this.itemCookTime[i] == 0) {
                        // DEBUG - If slot was modified illegally log offense.
                        if (!this.cookSlot(i)) {
                            System.err.println("Could not cook item!");
                        }
                    }
                }
            }
            this.markDirty();
        }
    }

    /**
     * Performes the transformation according to the valid recipe.
     * @param slot
     * @return
     */
    protected boolean cookSlot(int slot) {
        ItemStack itemStack = getStackInSlot(slot);
        ItemStack newStack = null;
        if (SteamerManager.isValidInputItemStack(itemStack)) {
            newStack = SteamerManager.getRecipe(itemStack).getOutput();
            newStack.stackSize = itemStack.stackSize;
        }
        if (newStack != null) {
            this.setInventorySlotContents(slot, newStack);
            return true;
        }
        return false;
    }

    /**
     * Recalculates data for the given slot. This resets the time the item is cooked as if it is a new one or marks the
     * slot as invalid.
     *
     * @param slot
     */
    public void calculateSlotData(int slot) {
        ItemStack itemStack = getStackInSlot(slot);
        int duration = -1;
        if (itemStack != null && itemStack.stackSize > 0 && SteamerManager.isValidInputItemStack(itemStack)) {
            duration = SteamerManager.getRecipe(this.inventory[slot]).getDuration();
        }
        this.itemCookTime[slot] = duration;
        this.markDirty();
    }
}
