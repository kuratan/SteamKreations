package de.kuratan.steamkreations;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import de.kuratan.steamkreations.block.SKBlocks;
import net.minecraft.init.Blocks;


@Mod(modid = SteamKreations.MOD_ID, version = SteamKreations.MOD_VERSION, name = "SteamKreations")
public class SteamKreations {
    public static final String MOD_ID = "steamkreations";
    public static final String MOD_VERSION = "@MOD_VERSION@";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        System.out.println("MOD_ID (MOD_VERSION): " + MOD_ID + " (" + MOD_VERSION + ")");
        SKBlocks.init();
        // some example code
        System.out.println("DIRT BLOCK >> "+ Blocks.dirt.getUnlocalizedName());
        System.out.println("STEAMER BLOCK >> "+ SKBlocks.steamer.getUnlocalizedName());
    }
}
