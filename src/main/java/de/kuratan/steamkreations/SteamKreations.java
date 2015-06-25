package de.kuratan.steamkreations;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import de.kuratan.steamkreations.block.SKBlocks;
import de.kuratan.steamkreations.client.gui.GuiHandler;
import de.kuratan.steamkreations.item.SKItems;
import de.kuratan.steamkreations.lib.Log;
import de.kuratan.steamkreations.lib.Reference;
import de.kuratan.steamkreations.tileentity.SKTileEntities;
import de.kuratan.steamkreations.crafting.ChocolateManager;
import de.kuratan.steamkreations.crafting.HeaterManager;
import de.kuratan.steamkreations.crafting.SteamerManager;
import de.kuratan.steamkreations.world.village.SKWorld;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


@Mod(modid = Reference.MOD_ID, version = Reference.MOD_VERSION, name = Reference.MOD_NAME)
public class SteamKreations {

    public static CreativeTabs tab = new CreativeTabs(Reference.MOD_ID) {
        @Override
        public Item getTabIconItem() {
            return SKItems.steamedCarrot;
        }
    };

    @Mod.Instance(Reference.MOD_ID)
    public static SteamKreations instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        //event.getSuggestedConfigurationFile()
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        SKBlocks.initialization();
        SKTileEntities.initialization();
        SKItems.initialization();
        SKWorld.initialization();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        SteamerManager.initialization();
        ChocolateManager.initialization();
        HeaterManager.initialization();
        SKBlocks.postInitialization();
        SKItems.postInitialization();
        Log.info("postInit done :)");
    }
}
