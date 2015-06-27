package de.kuratan.steamkreations.world.village;

import de.kuratan.steamkreations.block.SKBlocks;
import de.kuratan.steamkreations.lib.Log;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import java.util.List;
import java.util.Random;

public class ComponentRestaurant extends StructureVillagePieces.House1 {
    private int averageGroundLevel = -1;

    public ComponentRestaurant() {

    }

    public ComponentRestaurant(StructureVillagePieces.Start villagePiece, final int par2, final Random random, final StructureBoundingBox structureBoundingBox, final int coordBaseMode) {
        super(villagePiece, par2, random, structureBoundingBox, coordBaseMode);
    }

    public static ComponentRestaurant buildComponent(StructureVillagePieces.Start start, List list, Random random, int x, int y, int z, int coordBaseMode, int i5) {
        final StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 9, 10, 6, coordBaseMode);
        return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(list, structureboundingbox) == null ? new ComponentRestaurant(start, i5, random, structureboundingbox, coordBaseMode) : null;
    }

    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
        if (this.averageGroundLevel < 0)
        {
            this.averageGroundLevel = this.getAverageGroundLevel(world, structureBoundingBox);

            if (this.averageGroundLevel < 0)
            {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 9, 0);
        }
        Log.warn("Restaurant @" + structureBoundingBox.getCenterX() + " " + structureBoundingBox.getCenterZ());
        // Base
        this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 8, 0, 6, Blocks.cobblestone, Blocks.cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBox, 1, 0, 1, 7, 0, 5, Blocks.planks, Blocks.planks, false);

        // Walls
        this.fillWithBlocks(world, structureBoundingBox, 1, 1, 0, 7, 3, 0, Blocks.planks, Blocks.planks, false);
        this.fillWithBlocks(world, structureBoundingBox, 0, 1, 1, 0, 3, 5, Blocks.planks, Blocks.planks, false);
        this.fillWithBlocks(world, structureBoundingBox, 1, 1, 6, 7, 3, 6, Blocks.planks, Blocks.planks, false);
        this.fillWithBlocks(world, structureBoundingBox, 8, 1, 1, 8, 3, 5, Blocks.planks, Blocks.planks, false);
        this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 0, 3, 0, Blocks.log, Blocks.log, false);
        this.fillWithBlocks(world, structureBoundingBox, 8, 0, 0, 8, 3, 0, Blocks.log, Blocks.log, false);
        this.fillWithBlocks(world, structureBoundingBox, 8, 0, 6, 8, 3, 6, Blocks.log, Blocks.log, false);
        this.fillWithBlocks(world, structureBoundingBox, 0, 0, 6, 0, 3, 6, Blocks.log, Blocks.log, false);
        this.fillWithBlocks(world, structureBoundingBox, 4, 0, 6, 4, 3, 6, Blocks.log, Blocks.log, false);

        // Windows
        this.fillWithBlocks(world, structureBoundingBox, 0, 2, 2, 0, 2, 4, Blocks.glass_pane, Blocks.glass_pane, false);
        this.fillWithBlocks(world, structureBoundingBox, 8, 2, 2, 8, 2, 4, Blocks.glass_pane, Blocks.glass_pane, false);
        this.placeBlockAtCurrentPosition(world, Blocks.glass_pane, 0, 2, 2, 0, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.glass_pane, 0, 6, 2, 0, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.glass_pane, 0, 6, 2, 6, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.glass_pane, 0, 2, 2, 6, structureBoundingBox);

        // Interior
        this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 6, 1, 2, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.wooden_pressure_plate, 0, 6, 2, 2, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 6, 1, 4, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.wooden_pressure_plate, 0, 6, 2, 4, structureBoundingBox);

        this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 3), 6, 1, 5, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 2), 6, 1, 1, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 0), 7, 1, 2, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 0), 7, 1, 3, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 0), 7, 1, 4, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.planks, 0, 7, 1, 1, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, 7, 2, 1, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.jukebox, 0, 7, 1, 5, structureBoundingBox);

        this.fillWithBlocks(world, structureBoundingBox, 3, 1, 1, 3, 1, 4, Blocks.stone, Blocks.stone, false);
        this.placeBlockAtCurrentPosition(world, SKBlocks.steamGenerator, 0, 1, 1, 1, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, SKBlocks.steamer, 0, 1, 2, 1, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.furnace, this.getMetadataWithOffset(Blocks.furnace, 2), 1, 1, 2, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.chest, this.getMetadataWithOffset(Blocks.chest, 2), 1, 1, 3, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.crafting_table, 0, 1, 1, 4, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, SKBlocks.steamGenerator, 0, 1, 1, 5, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, SKBlocks.heater, 0, 1, 2, 5, structureBoundingBox);

        // Chimney
        this.fillWithBlocks(world, structureBoundingBox, 0, 0, 2, 0, 9, 2, Blocks.cobblestone, Blocks.cobblestone, false);

        // Torches
        this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 1, 3, 3, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 7, 3, 3, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 4, 3, 1, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 4, 3, 5, structureBoundingBox);

        // Door
        this.fillWithBlocks(world, structureBoundingBox, 4, 1, 0, 4, 2, 0, Blocks.air, Blocks.air, false);
        this.placeDoorAtCurrentPosition(world, structureBoundingBox, random, 4, 1, 0, this.getMetadataWithOffset(Blocks.wooden_door, 1));
        if(this.getBlockAtCurrentPosition(world, 4, 0, -1, structureBoundingBox).getMaterial() == Material.air && this.getBlockAtCurrentPosition(world, 4, -1, -1, structureBoundingBox).getMaterial() != Material.air) {
            this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 3), 4, 0, -1, structureBoundingBox);
        }
        return true;
    }
}
