package de.kuratan.steamkreations.block.heater;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.kuratan.steamkreations.SteamKreations;
import de.kuratan.steamkreations.block.steamer.TileEntitySteamer;
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

public class BlockHeater extends BlockContainer implements ITileEntityProvider, IInitializer {

    protected IIcon textureTop;
    protected IIcon textureBottom;
    protected IIcon textureSide;

    public BlockHeater() {
        super(Material.iron);
        this.setBlockName(ModReference.modPrefix("heater"));
        this.setBlockTextureName(ModReference.modPrefix("heater"));
        this.setHardness(1.0f);
        this.setStepSound(soundTypeMetal);
        this.setCreativeTab(SteamKreations.tab);
    }

    @Override
    public boolean preInitialization() {
        return true;
    }

    @Override
    public boolean initialization() {
        GameRegistry.registerTileEntity(TileEntityHeater.class, ModReference.getUnlocalizedInternalNameWithMod(this));
        return true;
    }

    @Override
    public boolean postInitialization() {
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void getSubBlocks(Item blockItem, CreativeTabs tab, List subBlocks) {
        for (int i = 0; i < TileEntityHeater.TYPES.values().length; i++) {
            subBlocks.add(new ItemStack(blockItem, 1, i));
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        TileEntityHeater heater = new TileEntityHeater();
        heater.setType(TileEntityHeater.TYPES.values()[metadata]);
        return heater;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        textureTop = iconRegister.registerIcon(textureName + "_top");
        textureBottom = iconRegister.registerIcon(textureName + "_bottom");
        textureSide = iconRegister.registerIcon(textureName + "_side");
    }

    @SideOnly(Side.CLIENT)
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
            TileEntityHeater tileEntityHeater = (TileEntityHeater) world.getTileEntity(x, y, z);
            if (tileEntityHeater != null) {
                entityPlayer.openGui(SteamKreations.instance, SKGuis.HEATER.ordinal(), world, x, y, z);
            }
        }
        return true;
    }
}
