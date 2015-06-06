package de.kuratan.steamkreations.block;

import de.kuratan.steamkreations.SteamKreations;
import de.kuratan.steamkreations.utils.ModReference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class SKBlock extends Block implements ISKBlock {

    protected SKBlock(Material material) {
        super(material);
        this.setCreativeTab(SteamKreations.tab);
    }

    public Block setBlockAndTextureName(String name) {
        return this.setBlockName(name).setBlockTextureName(name);
    }

    public String getUnlocalizedInternalNameWithMod() {
        return ModReference.getUnlocalizedInternalNameWithMod(this);
    }

    public String getUnlocalizedInternalName() {
        return ModReference.getUnlocalizedInternalName(this);
    }

    @Override
    public Block setBlockName(String name) {
        return super.setBlockName(ModReference.modPrefix(name));
    }

    @Override
    public Block setBlockTextureName(String name) {
        return super.setBlockTextureName(ModReference.modPrefix(name));
    }
}
