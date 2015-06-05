package de.kuratan.steamkreations.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.TileFluidHandler;

public class TileEntitySteamer extends TileFluidHandler implements IInventory, IFluidHandler {


    public enum TYPES {
        NORMAL, REINFORCED, CREATIVE
    }

    protected TYPES type;
    protected ItemStack[] inventory;

    public void setType(TYPES type) {
        this.type = type;
        switch (type) {
            case REINFORCED:
                this.tank = new FluidTank(10000);
                this.inventory = new ItemStack[4];
                break;
            case CREATIVE:
                this.tank = new FluidTank(-1);
                this.inventory = new ItemStack[4];
                break;
            default:
                this.tank = new FluidTank(5000);
                this.inventory = new ItemStack[1];
        }
        System.out.println("Type set to: " + type);
    }

    public TYPES getType() {
        System.out.println("Retrieving type: " + type);
        return type;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        tag.setInteger("type", this.getType().ordinal());
        super.writeToNBT(tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        this.setType(TYPES.values()[tag.getInteger("type")]);
        super.readFromNBT(tag);
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return false;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        if (from.equals(ForgeDirection.DOWN)) {
            return super.canFill(from, fluid);
        }
        return false;
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
    public ItemStack decrStackSize(int i, int i1) {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        if (slot < getSizeInventory()) {
            this.inventory[slot] = itemStack;
        }
    }

    @Override
    public String getInventoryName() {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return false;
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
}
