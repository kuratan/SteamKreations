package de.kuratan.steamkreations.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import de.kuratan.steamkreations.container.ContainerSteamGenerator;
import de.kuratan.steamkreations.container.ContainerSteamer;
import de.kuratan.steamkreations.tileentity.TileEntitySteamGenerator;
import de.kuratan.steamkreations.tileentity.TileEntitySteamer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        SKGuis gui = SKGuis.values()[id];
        switch (gui) {
            case STEAMER:
                TileEntitySteamer tileEntitySteamer = (TileEntitySteamer)world.getTileEntity(x, y, z);
                return new ContainerSteamer(entityPlayer.inventory, tileEntitySteamer);
            case STEAM_GENERATOR:
                TileEntitySteamGenerator tileEntitySteamGenerator = (TileEntitySteamGenerator)world.getTileEntity(x, y, z);
                return new ContainerSteamGenerator(entityPlayer.inventory, tileEntitySteamGenerator);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        SKGuis gui = SKGuis.values()[id];
        switch (gui) {
            case STEAMER:
                TileEntitySteamer tileEntitySteamer = (TileEntitySteamer)world.getTileEntity(x, y, z);
                return new GuiSteamer(entityPlayer.inventory, tileEntitySteamer);
            case STEAM_GENERATOR:
                TileEntitySteamGenerator tileEntitySteamGenerator = (TileEntitySteamGenerator)world.getTileEntity(x, y, z);
                return new GuiSteamGenerator(entityPlayer.inventory, tileEntitySteamGenerator);
        }
        return null;
    }
}
