package de.kuratan.steamkreations.block;

import de.kuratan.steamkreations.SteamKreations;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

public abstract class SKBlockContainer extends BlockContainer implements ISKBlock {

    protected SKBlockContainer(Material material) {
        super(material);
        this.setCreativeTab(SteamKreations.tab);
    }

    public Block setBlockAndTextureName(String name) {
        return this.setBlockName(name).setBlockTextureName(name);
    }

    public String getUnlocalizedInternalName() {
        return this.getUnlocalizedName().substring(("tile." + SteamKreations.MOD_ID + ":").length());
    }

    @Override
    public Block setBlockName(String name) {
        return super.setBlockName(SteamKreations.MOD_ID +":"+name);
    }

    @Override
    public Block setBlockTextureName(String name) {
        return super.setBlockTextureName(SteamKreations.MOD_ID +":"+name);
    }
}
