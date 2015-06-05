package de.kuratan.steamkreations.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.kuratan.steamkreations.SteamKreations;
import de.kuratan.steamkreations.tileentity.TileEntitySteamer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSteamer extends SKBlock {

    protected IIcon basicTexture;
    protected IIcon portTexture;

    protected BlockSteamer() {
        super(Material.iron);
        this.setBlockAndTextureName("steamer");
        this.setHardness(1.0f);
        this.setStepSound(soundTypeMetal);
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        TileEntitySteamer steamer = new TileEntitySteamer();
        steamer.setType(TileEntitySteamer.TYPES.NORMAL);
        return steamer;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        basicTexture = iconRegister.registerIcon(SteamKreations.MOD_ID + ":steamer");
        portTexture = iconRegister.registerIcon(SteamKreations.MOD_ID + ":steamer_port");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int iside, int metadata) {
        ForgeDirection side = ForgeDirection.getOrientation(iside);
        switch (side) {
            case DOWN:
                return portTexture;
            default:
                return basicTexture;
        }
    }
}
