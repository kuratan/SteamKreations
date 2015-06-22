package de.kuratan.steamkreations.world.village;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import java.util.List;
import java.util.Random;

public class ComponentRestaurant extends StructureVillagePieces.House2 {
    public ComponentRestaurant(StructureVillagePieces.Start villagePiece, final int par2, final Random random, final StructureBoundingBox structureBoundingBox, final int coordBaseMode) {
        this.coordBaseMode = coordBaseMode;
    }

    public static ComponentRestaurant buildComponent(StructureVillagePieces.Start start, List list, Random random, int x, int y, int z, int coordBaseMode, int i5) {
        final StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 7, 6, 7, coordBaseMode);
        return StructureComponent.findIntersecting(list, structureboundingbox) == null ? new ComponentRestaurant(start, i5, random, structureboundingbox, coordBaseMode) : null;
    }

    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
        return true;
    }
}
