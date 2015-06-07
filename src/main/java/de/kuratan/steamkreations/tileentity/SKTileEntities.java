package de.kuratan.steamkreations.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;
import de.kuratan.steamkreations.SteamKreations;
import de.kuratan.steamkreations.block.SKBlock;
import de.kuratan.steamkreations.block.SKBlockContainer;
import de.kuratan.steamkreations.block.SKBlocks;
import net.minecraft.tileentity.TileEntity;

public class SKTileEntities {

    public static void registerBlock(final Class<? extends TileEntity> tileEntity, SKBlock block) {
        GameRegistry.registerTileEntity(tileEntity, SteamKreations.MOD_ID + ":" + block.getUnlocalizedInternalName());
    }

    public static void registerBlock(final Class<? extends TileEntity> tileEntity, SKBlockContainer block) {
        GameRegistry.registerTileEntity(tileEntity, SteamKreations.MOD_ID + ":" + block.getUnlocalizedInternalName());
    }

    public static void init() {
        registerBlock(TileEntitySteamer.class, SKBlocks.steamer);
        registerBlock(TileEntitySteamGenerator.class, SKBlocks.steamGenerator);
    }
}
