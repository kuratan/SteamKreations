package de.kuratan.steamkreations.gui;

import de.kuratan.steamkreations.SteamKreations;
import de.kuratan.steamkreations.container.ContainerSteamer;
import de.kuratan.steamkreations.block.steamer.TileEntitySteamer;
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
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(SteamKreations.MOD_ID,
                                                                     this.tileEntitySteamer.getType() ==
                                                                     TileEntitySteamer.TYPES.NORMAL ? "textures/gui/steamer.png" : "textures/gui/steamer_big.png"));
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
