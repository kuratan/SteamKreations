package de.kuratan.steamkreations.client.gui;

import de.kuratan.steamkreations.container.ContainerSteamGenerator;
import de.kuratan.steamkreations.block.steam_generator.TileEntitySteamGenerator;
import de.kuratan.steamkreations.lib.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
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
    protected void drawGuiContainerForegroundLayer(int p_drawGuiContainerForegroundLayer_1_, int p_drawGuiContainerForegroundLayer_2_) {
        this.fontRendererObj.drawString(
                this.tileEntitySteamGenerator.hasCustomInventoryName() ? this.tileEntitySteamGenerator
                        .getInventoryName() : I18n
                        .format(this.tileEntitySteamGenerator.getInventoryName(), new Object[0]), 8, 6, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Reference.getGuiResourceLocation("steam_generator"));
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        if (this.tileEntitySteamGenerator.isBurning()) {
            int progress = this.tileEntitySteamGenerator.getBurnTimeRemainingScaled(13);
            this.drawTexturedModalRect(x + 12 + 4 * 18, y + 10 + 2 * 18 + 12 - progress, xSize, 12 - progress, 14,
                                       progress + 1);
        }
    }
}
