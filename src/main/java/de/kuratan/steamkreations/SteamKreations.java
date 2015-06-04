package de.kuratan.steamkreations;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import de.kuratan.steamkreations.block.SKBlocks;
import net.minecraft.init.Blocks;


@Mod(modid = SteamKreations.MODID, version = SteamKreations.VERSION, name = "SteamKreations")
public class SteamKreations {
    public static final String MODID = "@MOD_ID@";
    public static final String VERSION = "@VERSION@";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        SKBlocks.init();
        // some example code
        System.out.println("DIRT BLOCK >> "+ Blocks.dirt.getUnlocalizedName());
        System.out.println("STEAMER BLOCK >> "+ SKBlocks.steamer.getUnlocalizedName());
    }
}
