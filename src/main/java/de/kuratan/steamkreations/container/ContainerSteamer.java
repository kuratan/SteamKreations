package de.kuratan.steamkreations.container;

import de.kuratan.steamkreations.tileentity.TileEntitySteamer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerSteamer extends Container {

    public ContainerSteamer(final InventoryPlayer inventoryPlayer, final TileEntitySteamer tileEntitySteamer) {
        this.addSlotToContainer(new SlotSteamerTransform(tileEntitySteamer, 0, 30, 40));
        this.addSlotToContainer(new SlotSteamerTransform(tileEntitySteamer, 1, 30, 80));
        if (tileEntitySteamer.getType() != TileEntitySteamer.TYPES.NORMAL) {
            this.addSlotToContainer(new SlotSteamerTransform(tileEntitySteamer, 2, 70, 40));
            this.addSlotToContainer(new SlotSteamerTransform(tileEntitySteamer, 3, 70, 80));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return false;
    }
}
