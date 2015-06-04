package de.kuratan.steamkreations.tileentity;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.TileFluidHandler;

public class TileEntitySteamer extends TileFluidHandler implements IFluidHandler {

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
}
