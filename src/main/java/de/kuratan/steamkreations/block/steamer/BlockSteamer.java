package de.kuratan.steamkreations.block.steamer;

import cpw.mods.fml.common.registry.GameRegistry;
import de.kuratan.steamkreations.SteamKreations;
import de.kuratan.steamkreations.gui.SKGuis;
import de.kuratan.steamkreations.utils.IInitializer;
import de.kuratan.steamkreations.utils.ModReference;
import net.minecraft.block.BlockContainer;
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

public class BlockSteamer extends BlockContainer implements ITileEntityProvider, IInitializer {

    protected IIcon textureTop;
    protected IIcon textureBottom;
    protected IIcon textureSide;

    public BlockSteamer() {
        super(Material.iron);
        this.setBlockName(ModReference.modPrefix("steamer"));
        this.setBlockTextureName(ModReference.modPrefix("steamer"));
        this.setHardness(1.0f);
        this.setStepSound(soundTypeMetal);
    }

    @Override
    public boolean initialize() {
        GameRegistry.registerTileEntity(TileEntitySteamer.class,
                                        ModReference.getUnlocalizedInternalNameWithMod(this));
        return true;
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
        textureTop = iconRegister.registerIcon(textureName + "_top");
        textureBottom = iconRegister.registerIcon(textureName + "_bottom");
        textureSide = iconRegister.registerIcon(textureName + "_side");
    }

    @Override
    public IIcon getIcon(int iside, int metadata) {
        ForgeDirection side = ForgeDirection.getOrientation(iside);
        switch (side) {
            case DOWN:
                return textureBottom;
            case UP:
                return textureTop;
            default:
                return textureSide;
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
