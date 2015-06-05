package de.kuratan.steamkreations.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import de.kuratan.steamkreations.container.ContainerSteamer;
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
        }
        return null;
    }
}
