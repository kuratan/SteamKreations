package de.kuratan.steamkreations.world.village;

import de.kuratan.steamkreations.block.SKBlocks;
import de.kuratan.steamkreations.item.SKItems;
import de.kuratan.steamkreations.lib.Log;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import java.util.List;
import java.util.Random;

public class ComponentRestaurant extends StructureVillagePieces.House1 {
    private int averageGroundLevel = -1;
    private final WeightedRandomChestContent[] contents = new WeightedRandomChestContent[]{
            new WeightedRandomChestContent(Items.cooked_beef, 0, 1, 3, 2),
            new WeightedRandomChestContent(Items.cooked_fished, 0, 1, 2, 2),
            new WeightedRandomChestContent(Items.cooked_fished, 1, 1, 2, 2),
            new WeightedRandomChestContent(Items.milk_bucket, 0, 1, 1, 2),
            new WeightedRandomChestContent(SKItems.smallMeal, 0, 1, 1, 1),
            new WeightedRandomChestContent(SKItems.steamedCarrot, 0, 2, 4, 3),
            new WeightedRandomChestContent(Items.baked_potato, 0, 4, 6, 2),
            new WeightedRandomChestContent(Items.bread, 0, 1, 3, 3)
    };

    public ComponentRestaurant() {

    }

    public ComponentRestaurant(StructureVillagePieces.Start villagePiece, final int par2, final Random random, final StructureBoundingBox structureBoundingBox, final int coordBaseMode) {
        super(villagePiece, par2, random, structureBoundingBox, coordBaseMode);
    }

    public static ComponentRestaurant buildComponent(StructureVillagePieces.Start start, List list, Random random, int x, int y, int z, int coordBaseMode, int i5) {
        final StructureBoundingBox structureboundingbox =
                StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 9, 10, 6, coordBaseMode);
        if (canVillageGoDeeper(structureboundingbox) &&
            StructureComponent.findIntersecting(list, structureboundingbox) == null) {
            return new ComponentRestaurant(start, i5, random, structureboundingbox, coordBaseMode);
        }
        return null;
    }

    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
        if (this.averageGroundLevel < 0) {
            this.averageGroundLevel = this.getAverageGroundLevel(world, structureBoundingBox);

            if (this.averageGroundLevel < 0) {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 9, 0);
        }

        // Base
        this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 8, 0, 6, Blocks.cobblestone, Blocks.cobblestone,
                            false);
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

        // Roof
        this.fillWithBlocks(world, structureBoundingBox, 0, 5, 1, 8, 5, 1, Blocks.planks, Blocks.planks, false);
        this.fillWithBlocks(world, structureBoundingBox, 0, 6, 2, 8, 6, 2, Blocks.planks, Blocks.planks, false);
        this.fillWithBlocks(world, structureBoundingBox, 0, 6, 4, 8, 6, 4, Blocks.planks, Blocks.planks, false);
        this.fillWithBlocks(world, structureBoundingBox, 0, 5, 5, 8, 5, 5, Blocks.planks, Blocks.planks, false);
        for (int i = 1; i <= 5; i++) {
            this.placeBlockAtCurrentPosition(world, Blocks.log, 12, 0, 4, i, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, Blocks.log, 12, 8, 4, i, structureBoundingBox);
        }
        this.fillWithBlocks(world, structureBoundingBox, 0, 5, 2, 0, 5, 4, Blocks.planks, Blocks.planks, false);
        this.fillWithBlocks(world, structureBoundingBox, 8, 5, 2, 8, 5, 4, Blocks.planks, Blocks.planks, false);
        for (int i = 5; i <= 7; i++) {
            this.placeBlockAtCurrentPosition(world, Blocks.log, 12, 0, i, 3, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, Blocks.log, 12, 8, i, 3, structureBoundingBox);
        }
        for (int i = 0; i <= 8; i++) {
            this.placeBlockAtCurrentPosition(world, Blocks.log, 12, i, 4, 0, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, Blocks.log, 12, i, 7, 3, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, Blocks.log, 12, i, 4, 6, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 3),
                                             i, 4, -1, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 3),
                                             i, 5, 0, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 3),
                                             i, 6, 1, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 3),
                                             i, 7, 2, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, Blocks.wooden_slab, 0, i, 8, 3, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 2),
                                             i, 7, 4, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 2),
                                             i, 6, 5, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 2),
                                             i, 5, 6, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 2),
                                             i, 4, 7, structureBoundingBox);
        }

        // Interior
        this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 6, 1, 2, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.wooden_pressure_plate, 0, 6, 2, 2, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 6, 1, 4, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.wooden_pressure_plate, 0, 6, 2, 4, structureBoundingBox);

        this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 3), 6,
                                         1, 5, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 2), 6,
                                         1, 1, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 0), 7,
                                         1, 2, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 0), 7,
                                         1, 3, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 0), 7,
                                         1, 4, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.planks, 0, 7, 1, 1, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, 7, 2, 1, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.jukebox, 0, 7, 1, 5, structureBoundingBox);

        this.placeBlockAtCurrentPosition(world, SKBlocks.steamGenerator, 0, 1, 1, 1, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, SKBlocks.steamer, 0, 1, 2, 1, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.furnace, this.getMetadataWithOffset(Blocks.furnace, 2), 1, 1, 2, structureBoundingBox);
        this.generateFoodChest(world, structureBoundingBox, random, 1, 1, 3, 5);
        this.generateFoodChest(world, structureBoundingBox, random, 1, 1, 4, 4);
        this.placeBlockAtCurrentPosition(world, SKBlocks.steamGenerator, 0, 1, 1, 5, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, SKBlocks.heater, 0, 1, 2, 5, structureBoundingBox);

        this.placeBlockAtCurrentPosition(world, Blocks.crafting_table, 2, 3, 1, 4, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.log, 12, 3, 1, 3, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.log, 12, 3, 1, 2, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.cauldron, 0, 3, 1, 1, structureBoundingBox);

        // Chimney
        this.fillWithBlocks(world, structureBoundingBox, 0, 0, 2, 0, 8, 2, Blocks.cobblestone, Blocks.cobblestone,
                            false);
        this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 0, 0, 9, 2, structureBoundingBox);

        // Torches
        this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 1, 3, 3, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 7, 3, 3, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 4, 3, 1, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 4, 3, 5, structureBoundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 4, 3, -1, structureBoundingBox);

        // Door
        this.fillWithBlocks(world, structureBoundingBox, 4, 1, 0, 4, 2, 0, Blocks.air, Blocks.air, false);
        this.placeDoorAtCurrentPosition(world, structureBoundingBox, random, 4, 1, 0,
                                        this.getMetadataWithOffset(Blocks.wooden_door, 1));
        if (this.getBlockAtCurrentPosition(world, 4, 0, -1, structureBoundingBox).getMaterial() == Material.air &&
            this.getBlockAtCurrentPosition(world, 4, -1, -1, structureBoundingBox).getMaterial() != Material.air) {
            this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs,
                                             this.getMetadataWithOffset(Blocks.stone_stairs, 3), 4, 0, -1,
                                             structureBoundingBox);
        }
        return true;
    }

    private boolean generateFoodChest(World world, StructureBoundingBox structureBoundingBox, Random random, int x, int y, int z, int weight) {
        int posX = this.getXWithOffset(x, z);
        int posY = this.getYWithOffset(y);
        int posZ = this.getZWithOffset(x, z);

        if (structureBoundingBox.isVecInside(posX, posY, posZ) && world.getBlock(posX, posY, posZ) != Blocks.chest) {
            this.placeBlockAtCurrentPosition(world, Blocks.chest, 0, x, y, z, structureBoundingBox);
            TileEntityChest inventory = (TileEntityChest) world.getTileEntity(posX, posY, posZ);

            if (inventory != null) {
                WeightedRandomChestContent.generateChestContents(random, contents, inventory, weight);
                return true;
            }
        }
        return false;
    }
}
