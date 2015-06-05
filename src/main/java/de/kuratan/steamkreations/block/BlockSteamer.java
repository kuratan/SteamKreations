package de.kuratan.steamkreations.block;

import de.kuratan.steamkreations.SteamKreations;
import de.kuratan.steamkreations.gui.SKGuis;
import de.kuratan.steamkreations.tileentity.TileEntitySteamer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

public class BlockSteamer extends SKBlockContainer implements ITileEntityProvider {

    protected IIcon basicTexture;
    protected IIcon portTexture;

    protected BlockSteamer() {
        super(Material.iron);
        this.setBlockAndTextureName("steamer");
        this.setHardness(1.0f);
        this.setStepSound(soundTypeMetal);
    }

    @Override
    public void getSubBlocks(Item blockItem, CreativeTabs p_149666_2_, List subBlocks) {
        for (int i = 0; i < TileEntitySteamer.TYPES.values().length; i++) {
            subBlocks.add(new ItemStack(blockItem, 1, i));
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {

        TileEntitySteamer steamer = new TileEntitySteamer();
        steamer.setType(TileEntitySteamer.TYPES.values()[metadata]);
        return steamer;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        basicTexture = iconRegister.registerIcon(SteamKreations.MOD_ID + ":steamer");
        portTexture = iconRegister.registerIcon(SteamKreations.MOD_ID + ":steamer_port");
    }

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

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        final ArrayList<ItemStack> itemStacks = new ArrayList<ItemStack>();
        final ItemStack itemStack = new ItemStack(this, 1, metadata);
        itemStacks.add(itemStack);
        return itemStacks;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int direction, float hitX, float hitY, float hitZ) {
        System.out.println("Activated!");
        if (entityPlayer.isSneaking()) {
            return false;
        }
        if (!world.isRemote) {
            TileEntitySteamer tileEntitySteamer = (TileEntitySteamer) world.getTileEntity(x, y, z);
            if (tileEntitySteamer != null) {
                entityPlayer.openGui(SteamKreations.instance, SKGuis.STEAMER.ordinal(), world, x, y, z);
            }
        }
        return true;
    }
}
