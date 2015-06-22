package de.kuratan.steamkreations.world.village;

import cpw.mods.fml.common.registry.VillagerRegistry;

public class SKWorld {

    public static void initialization() {
        VillagerRegistry.instance().registerVillageCreationHandler(new VillageRestaurantHandler());
    }
}
