package de.kuratan.steamkreations.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class SKFluids {

    public static Fluid steam;

    public static void initialization() {
        steam = FluidRegistry.getFluid("steam");
        if (steam == null) {
            steam = new Fluid("steam");
            steam.setDensity(200);
            steam.setTemperature(373);
            steam.setViscosity(500);
            steam.setGaseous(true);
            FluidRegistry.registerFluid(steam);
        }
    }
}
