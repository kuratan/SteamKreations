package de.kuratan.steamkreations.block;

import de.kuratan.steamkreations.tileentity.TileEntitySteamer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSteamer extends SKBlock {
    protected BlockSteamer() {
        super(Material.iron);
        this.setBlockAndTextureName("steamer");
        this.setHardness(1.0f);
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntitySteamer();
    }
}
