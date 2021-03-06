package de.kuratan.steamkreations.client.gui;

import de.kuratan.steamkreations.block.heater.TileEntityHeater;
import de.kuratan.steamkreations.client.gui.element.GuiFluidTank;
import de.kuratan.steamkreations.container.ContainerHeater;
import de.kuratan.steamkreations.item.SKItems;
import de.kuratan.steamkreations.lib.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
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
    protected void drawGuiContainerForegroundLayer(int p_drawGuiContainerForegroundLayer_1_, int p_drawGuiContainerForegroundLayer_2_) {
        this.fontRendererObj.drawString(
                this.tileEntityHeater.hasCustomInventoryName() ? this.tileEntityHeater.getInventoryName() : I18n
                        .format(this.tileEntityHeater.getInventoryName(), new Object[0]), 8, 6, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/heater.png"));
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        if (tileEntityHeater.active()) {
            drawTexturedModalRect(x + 87, y + 43, xSize, 0, 1 + tileEntityHeater.getCookProgressScaled(24), 16);
        }
        GuiFluidTank guiFluidTank = new GuiFluidTank(this, tileEntityHeater.getTankInfo(ForgeDirection.UNKNOWN), x + 156, y + 27);
        guiFluidTank.drawGuiContainerBackgroundLayer(f, i , j);
    }
}
