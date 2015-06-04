package de.kuratan.steamkreations.block;

import de.kuratan.steamkreations.SteamKreations;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class SKBlock extends Block {

    protected SKBlock(Material material) {
        super(material);
    }

    public Block setBlockAndTextureName(String name) {
        return this.setBlockName(name).setBlockTextureName(name);
    }

    public String getUnlocalizedInternalName() {
        return this.getUnlocalizedName().substring(("tile." + SteamKreations.MODID + ":").length());
    }

    @Override
    public Block setBlockName(String name) {
        return super.setBlockName(SteamKreations.MODID+":"+name);
    }

    @Override
    public Block setBlockTextureName(String name) {
        return super.setBlockTextureName(SteamKreations.MODID+":"+name);
    }
}
