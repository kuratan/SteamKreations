package de.kuratan.steamkreations;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import de.kuratan.steamkreations.block.SKBlocks;
import de.kuratan.steamkreations.gui.GuiHandler;
import de.kuratan.steamkreations.item.SKItems;
import de.kuratan.steamkreations.lib.Reference;
import de.kuratan.steamkreations.tileentity.SKTileEntities;
import de.kuratan.steamkreations.utils.managers.ChocolateManager;
import de.kuratan.steamkreations.utils.managers.HeaterManager;
import de.kuratan.steamkreations.utils.managers.SteamerManager;
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
        ChocolateManager.initialization();
        HeaterManager.initialization();
        SKBlocks.postInitialization();
        SKItems.postInitialization();
    }
}
