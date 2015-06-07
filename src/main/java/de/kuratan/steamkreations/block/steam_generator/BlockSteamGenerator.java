package de.kuratan.steamkreations.block.steam_generator;

import cpw.mods.fml.common.registry.GameRegistry;
import de.kuratan.steamkreations.SteamKreations;
import de.kuratan.steamkreations.block.ISKBlock;
import de.kuratan.steamkreations.block.SKBlockContainer;
import de.kuratan.steamkreations.gui.SKGuis;
import de.kuratan.steamkreations.utils.IHasInit;
import de.kuratan.steamkreations.utils.ModReference;
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

public class BlockSteamGenerator extends SKBlockContainer implements ITileEntityProvider, ISKBlock, IHasInit {

    protected IIcon textureTop;
    protected IIcon textureBottom;
    protected IIcon textureSide;

    public BlockSteamGenerator() {
        super(Material.iron);
        this.setBlockAndTextureName("steam_generator");
        this.setHardness(1.0f);
        this.setStepSound(soundTypeMetal);
    }

    @Override
    public boolean initialize() {
        GameRegistry.registerTileEntity(TileEntitySteamGenerator.class, ModReference.getUnlocalizedInternalNameWithMod(this));
        return true;
    }

    @Override
    public void getSubBlocks(Item blockItem, CreativeTabs p_149666_2_, List subBlocks) {
        for (int i = 0; i < TileEntitySteamGenerator.TYPES.values().length; i++) {
            subBlocks.add(new ItemStack(blockItem, 1, i));
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        TileEntitySteamGenerator steamGenerator = new TileEntitySteamGenerator();
        steamGenerator.setType(TileEntitySteamGenerator.TYPES.values()[metadata]);
        return steamGenerator;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        textureTop = iconRegister.registerIcon(getUnlocalizedInternalNameWithMod() + "_top");
        textureBottom = iconRegister.registerIcon(getUnlocalizedInternalNameWithMod() + "_bottom");
        textureSide = iconRegister.registerIcon(getUnlocalizedInternalNameWithMod() + "_side");
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
            TileEntitySteamGenerator tileEntitySteamGenerator = (TileEntitySteamGenerator) world.getTileEntity(x, y, z);
            if (tileEntitySteamGenerator != null) {
                entityPlayer.openGui(SteamKreations.instance, SKGuis.STEAM_GENERATOR.ordinal(), world, x, y, z);
            }
        }
        return true;
    }
}
