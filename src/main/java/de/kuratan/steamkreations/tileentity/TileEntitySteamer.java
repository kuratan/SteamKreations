package de.kuratan.steamkreations.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.TileFluidHandler;

public class TileEntitySteamer extends TileFluidHandler implements IFluidHandler {

    public enum TYPES {
        NORMAL, REINFORCED, CREATIVE
    }

    protected TYPES type;

    public TileEntitySteamer() {
        this.tank = new FluidTank(5000);
    }

    public void setType(TYPES type) {
        this.type = type;
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
        if (!from.equals(ForgeDirection.DOWN) && !from.equals(ForgeDirection.UP)) {
            return super.canFill(from, fluid);
        }
        return false;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        if (from.equals(ForgeDirection.DOWN)) {
            return super.canFill(from, fluid);
        }
        return false;
    }
}
