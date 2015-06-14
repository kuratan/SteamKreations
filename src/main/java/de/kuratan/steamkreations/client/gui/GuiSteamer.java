package de.kuratan.steamkreations.client.gui;

import de.kuratan.steamkreations.container.ContainerSteamer;
import de.kuratan.steamkreations.block.steamer.TileEntitySteamer;
import de.kuratan.steamkreations.lib.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiSteamer extends GuiContainer {
    TileEntitySteamer tileEntitySteamer;

    public GuiSteamer(final InventoryPlayer inventoryPlayer, final TileEntitySteamer tileEntitySteamer) {
        super(new ContainerSteamer(inventoryPlayer, tileEntitySteamer));
        this.tileEntitySteamer = tileEntitySteamer;
        this.xSize = 184;
        this.ySize = 184;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        boolean small = this.tileEntitySteamer.getType() == TileEntitySteamer.TYPES.NORMAL;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID,
                                                                     small ? "textures/gui/steamer.png" : "textures/gui/steamer_big.png"));
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        if (tileEntitySteamer.active()) {
            int progress = 0;
            ContainerSteamer.SLOTS slot;
            if (small) {
                slot = ContainerSteamer.SLOTS.SMALL;
                progress = this.tileEntitySteamer.getSlotDoneProgressScaled(slot.getId(), 13);
                this.drawTexturedModalRect(x + slot.getX() + 2, y + slot.getY() + 2 + 12 - progress, xSize,
                                           12 - progress, 14, progress + 1);
            } else {
                slot = ContainerSteamer.SLOTS.BIG1;
                if (tileEntitySteamer.isSlotActive(slot.getId())) {
                    progress = this.tileEntitySteamer.getSlotDoneProgressScaled(slot.getId(), 13);
                    this.drawTexturedModalRect(x + slot.getX() + 2 - 18, y + slot.getY() + 2 + 12 - progress, xSize,
                                               12 - progress, 14, progress + 1);
                }
                slot = ContainerSteamer.SLOTS.BIG2;
                if (tileEntitySteamer.isSlotActive(slot.getId())) {
                    progress = this.tileEntitySteamer.getSlotDoneProgressScaled(slot.getId(), 13);
                    this.drawTexturedModalRect(x + slot.getX() + 2 + 18, y + slot.getY() + 2 + 12 - progress, xSize,
                                               12 - progress, 14, progress + 1);
                } slot = ContainerSteamer.SLOTS.BIG3;
                if (tileEntitySteamer.isSlotActive(slot.getId())) {
                    progress = this.tileEntitySteamer.getSlotDoneProgressScaled(slot.getId(), 13);
                    this.drawTexturedModalRect(x + slot.getX() + 2 - 18, y + slot.getY() + 2 + 12 - progress, xSize,
                                               12 - progress, 14, progress + 1);
                }
                slot = ContainerSteamer.SLOTS.BIG4;
                if (tileEntitySteamer.isSlotActive(slot.getId())) {
                    progress = this.tileEntitySteamer.getSlotDoneProgressScaled(slot.getId(), 13);
                    this.drawTexturedModalRect(x + slot.getX() + 2 + 18, y + slot.getY() + 2 + 12 - progress, xSize,
                                               12 - progress, 14, progress + 1);
                }
            }
        }
    }
}
