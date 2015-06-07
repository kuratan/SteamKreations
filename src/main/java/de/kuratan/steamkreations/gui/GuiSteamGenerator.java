package de.kuratan.steamkreations.gui;

import de.kuratan.steamkreations.container.ContainerSteamGenerator;
import de.kuratan.steamkreations.block.steam_generator.TileEntitySteamGenerator;
import de.kuratan.steamkreations.utils.ModReference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GuiSteamGenerator extends GuiContainer {
    TileEntitySteamGenerator tileEntitySteamGenerator;

    public GuiSteamGenerator(final InventoryPlayer inventoryPlayer, final TileEntitySteamGenerator tileEntitySteamGenerator) {
        super(new ContainerSteamGenerator(inventoryPlayer, tileEntitySteamGenerator));
        this.tileEntitySteamGenerator = tileEntitySteamGenerator;
        this.xSize = 184;
        this.ySize = 184;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(ModReference.getGuiResourceLocation("steam_generator"));
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
