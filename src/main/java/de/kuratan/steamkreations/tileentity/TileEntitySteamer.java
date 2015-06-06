package de.kuratan.steamkreations.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.TileFluidHandler;

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

        public int getSlots() {
            return slots;
        }

        public int getSteamCapacity() {
            return steamCapacity;
        }

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

    public void setType(TYPES type) {
        this.type = type;
        this.tank = new FluidTank(type.getSteamCapacity());
        this.inventory = new ItemStack[type.getSlots()];
        this.itemCookTime = new int[type.getSlots()];
    }

    public TYPES getType() {
        return type;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("type", this.getType().ordinal());
        tag.setIntArray("cookTime", this.itemCookTime);
        NBTTagList tagList = new NBTTagList();
        for (int i = 0; i < getSizeInventory(); i++) {
            if (inventory[i] != null) {
                NBTTagCompound slot = new NBTTagCompound();
                slot.setByte("slot", (byte)i);
                inventory[i].writeToNBT(slot);
                tagList.appendTag(slot);
            }
        }
        tag.setTag(this.getInventoryName(), tagList);
    }

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

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return false;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return from.equals(ForgeDirection.DOWN) && super.canFill(from, fluid);
    }

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

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null) {
            setInventorySlotContents(slot, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        if (slot < getSizeInventory()) {
            this.inventory[slot] = itemStack;
            if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
                itemStack.stackSize = getInventoryStackLimit();
            }
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
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && entityPlayer.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
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

    @Override
    public void updateEntity() {
        if (tank.getFluidAmount() >= type.getSteamPerTick() || this.type == TYPES.CREATIVE) {
            tank.drain(type.getSteamPerTick(), true);

            for (int i = 0; i < this.getSizeInventory(); i++) {
                if (canCookSlot(i)) {
                    this.itemCookTime[i]++;

                    if (this.itemCookTime[i] == 200) {
                        if (!this.cookSlot(i)) {
                            System.out.println("Could not cook item!");
                        }
                    }
                }
            }
            this.markDirty();
        }
    }

    protected boolean canCookSlot(int slot) {
        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null) {
            if (itemStack.getItem() == Items.potato) {
                return true;
            }
            if (itemStack.getItem() == Items.poisonous_potato) {
                return true;
            }
        }
        return false;
    }

    protected boolean cookSlot(int slot) {
        ItemStack itemStack = getStackInSlot(slot);
        ItemStack newStack = null;
        if (itemStack != null) {
            if (itemStack.getItem() == Items.potato) {
                newStack = new ItemStack(Items.baked_potato);
                newStack.stackSize = itemStack.stackSize;
            }
            if (itemStack.getItem() == Items.poisonous_potato) {
                newStack = new ItemStack(Items.potato);
                newStack.stackSize = itemStack.stackSize;
            }
        }
        if (newStack != null) {
            this.setInventorySlotContents(slot, newStack);
            return true;
        }
        return false;
    }

    public void resetSlot(int slotNumber) {
        this.itemCookTime[slotNumber] = 0;
        this.markDirty();
    }
}
