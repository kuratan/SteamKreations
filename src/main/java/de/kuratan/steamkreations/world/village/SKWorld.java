package de.kuratan.steamkreations.world.village;

import cpw.mods.fml.common.registry.VillagerRegistry;
import de.kuratan.steamkreations.config.Config;
import de.kuratan.steamkreations.lib.Reference;
import net.minecraft.world.gen.structure.MapGenStructureIO;

public class SKWorld {

    public static void initialization() {
        if (Config.World.restaurant && Config.Blocks.heater && Config.Blocks.steamGenerator && Config.Blocks.steamer) {
            VillagerRegistry.instance().registerVillageCreationHandler(new VillageRestaurantHandler());
            MapGenStructureIO.func_143031_a(ComponentRestaurant.class, Reference.modPrefix("RestaurantStructure"));
        }
    }
}
