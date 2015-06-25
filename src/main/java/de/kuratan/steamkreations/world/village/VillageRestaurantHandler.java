package de.kuratan.steamkreations.world.village;

import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
import de.kuratan.steamkreations.lib.Log;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import java.util.List;
import java.util.Random;

public class VillageRestaurantHandler implements IVillageCreationHandler {
    @Override
    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i) {
        return new StructureVillagePieces.PieceWeight(ComponentRestaurant.class, 30, i + random.nextInt(4));
    }

    @Override
    public Class<?> getComponentClass() {
        return ComponentRestaurant.class;
    }

    @Override
    public Object buildComponent(StructureVillagePieces.PieceWeight pieceWeight, StructureVillagePieces.Start start, List list, Random random, int x, int y, int z, int coordBaseMode, int i) {
        return ComponentRestaurant.buildComponent(start, list, random, x, y, z, coordBaseMode, i);
    }
}
