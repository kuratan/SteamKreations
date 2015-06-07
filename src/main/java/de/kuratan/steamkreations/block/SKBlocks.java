package de.kuratan.steamkreations.block;

import cpw.mods.fml.common.registry.GameRegistry;
import de.kuratan.steamkreations.block.steam_generator.BlockSteamGenerator;
import de.kuratan.steamkreations.block.steam_generator.ItemBlockSteamGenerator;
import de.kuratan.steamkreations.block.steamer.BlockSteamer;
import de.kuratan.steamkreations.block.steamer.ItemBlockSteamer;
import net.minecraft.item.ItemBlock;

public class SKBlocks {
    public static SKBlockContainer steamer;
    public static SKBlockContainer steamGenerator;

    public static SKBlock registerBlock(final SKBlock block, final Class<? extends ItemBlock> itemblock) {
        return (SKBlock) GameRegistry.registerBlock(block, (Class) itemblock, block.getUnlocalizedInternalName());
    }

    public static SKBlockContainer registerBlock(final SKBlockContainer block, final Class<? extends ItemBlock> itemblock) {
        return (SKBlockContainer) GameRegistry
                .registerBlock(block, (Class) itemblock, block.getUnlocalizedInternalName());
    }

    public static void init() {
        steamer = registerBlock(new BlockSteamer(), ItemBlockSteamer.class);
        steamGenerator = registerBlock(new BlockSteamGenerator(), ItemBlockSteamGenerator.class);
    }
}
