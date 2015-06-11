package de.kuratan.steamkreations;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import de.kuratan.steamkreations.block.SKBlocks;
import de.kuratan.steamkreations.gui.GuiHandler;
import de.kuratan.steamkreations.item.SKItems;
import de.kuratan.steamkreations.tileentity.SKTileEntities;
import de.kuratan.steamkreations.utils.managers.ChocolateManager;
import de.kuratan.steamkreations.utils.managers.SteamerManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


@Mod(modid = SteamKreations.MOD_ID, version = SteamKreations.MOD_VERSION, name = "SteamKreations")
public class SteamKreations {
    public static final String MOD_ID = "steamkreations";
    public static final String MOD_VERSION = "@MOD_VERSION@";

    public static CreativeTabs tab = new CreativeTabs(MOD_ID) {
        @Override
        public Item getTabIconItem() {
            return SKItems.steamedCarrot;
        }
    };

    @Mod.Instance("steamkreations")
    public static SteamKreations instance;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        SKBlocks.initialization();
        SKTileEntities.initialization();
        SKItems.initialization();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        SteamerManager.initialization();
        ChocolateManager.initialize();
        SKBlocks.postInitialization();
        SKItems.postInitialization();
    }
}
