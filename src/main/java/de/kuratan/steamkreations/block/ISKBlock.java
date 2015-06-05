package de.kuratan.steamkreations.block;

import net.minecraft.block.Block;

public interface ISKBlock {
    String getUnlocalizedInternalName();

    Block setBlockAndTextureName(String name);
}
