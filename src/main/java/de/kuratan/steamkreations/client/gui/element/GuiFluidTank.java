package de.kuratan.steamkreations.client.gui.element;

import de.kuratan.steamkreations.item.SKItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;

public class GuiFluidTank implements IGuiElement{
    protected int x;
    protected int y;
    protected FluidTankInfo[] fluidTankInfos;
    protected Gui gui;

    public GuiFluidTank(Gui gui, FluidTankInfo[] fluidTankInfos, int x, int y) {
        this.gui = gui;
        this.fluidTankInfos = fluidTankInfos;
        this.x = x;
        this.y = y;
    }

    @Override
    public void drawGuiContainerForegroundLayer(int i1, int i2) {

    }

    @Override
    public void drawGuiContainerBackgroundLayer(float f1, int i1, int i2) {
        FluidStack fluidStack = fluidTankInfos[0].fluid;
        if (fluidStack == null || fluidStack.amount <= 0) {
            return;
        }
        int capacity = fluidTankInfos[0].capacity;
        int height = fluidTankInfos[0].fluid.amount * 58 / capacity;
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        /*
        IIcon fluidIcon = fluidTankInfos[0].fluid.getFluid().getStillIcon();
        */
        IIcon fluidIcon = FluidRegistry.getFluid("water").getStillIcon();
        for (int row = 0; row <= height / 16; ++row) {
            gui.drawTexturedModelRectFromIcon(this.x, this.y + 57 - height + row * 16, fluidIcon, 16, 16);
        }
    }
}
