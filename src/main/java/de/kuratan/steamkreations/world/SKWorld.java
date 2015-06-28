package de.kuratan.steamkreations.world;

import cpw.mods.fml.common.registry.VillagerRegistry;
import de.kuratan.steamkreations.config.Config;
import de.kuratan.steamkreations.lib.Reference;
import de.kuratan.steamkreations.world.village.ComponentRestaurant;
import de.kuratan.steamkreations.world.village.VillageRestaurantHandler;
import net.minecraft.world.gen.structure.MapGenStructureIO;

public class SKWorld {

    public static void initialization() {
        if (Config.World.restaurant && Config.Blocks.heater && Config.Blocks.steamGenerator && Config.Blocks.steamer) {
            VillagerRegistry.instance().registerVillageCreationHandler(new VillageRestaurantHandler());
            MapGenStructureIO.func_143031_a(ComponentRestaurant.class, Reference.modPrefix("RestaurantStructure"));
        }
    }
}
