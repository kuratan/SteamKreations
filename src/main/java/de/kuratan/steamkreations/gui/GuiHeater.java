package de.kuratan.steamkreations.gui;

import de.kuratan.steamkreations.block.heater.TileEntityHeater;
import de.kuratan.steamkreations.container.ContainerHeater;
import de.kuratan.steamkreations.lib.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiHeater extends GuiContainer {
    TileEntityHeater tileEntityHeater;

    public GuiHeater(final InventoryPlayer inventoryPlayer, final TileEntityHeater tileEntityHeater) {
        super(new ContainerHeater(inventoryPlayer, tileEntityHeater));
        this.tileEntityHeater = tileEntityHeater;
        this.xSize = 184;
        this.ySize = 184;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/heater.png"));
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
