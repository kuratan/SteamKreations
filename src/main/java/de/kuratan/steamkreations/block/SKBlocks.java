package de.kuratan.steamkreations.block;

import cpw.mods.fml.common.registry.GameRegistry;
import de.kuratan.steamkreations.block.steam_generator.BlockSteamGenerator;
import de.kuratan.steamkreations.block.steam_generator.ItemBlockSteamGenerator;
import de.kuratan.steamkreations.block.steamer.BlockSteamer;
import de.kuratan.steamkreations.block.steamer.ItemBlockSteamer;
import de.kuratan.steamkreations.utils.IInitializer;
import de.kuratan.steamkreations.utils.ModReference;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

import java.util.ArrayList;

public class SKBlocks {
    public static ArrayList<IInitializer> blocks = new ArrayList<IInitializer>();
    public static Block steamer;
    public static Block steamGenerator;

    public static Block addBlock(Block block) {
        if (block instanceof IInitializer) {
            blocks.add((IInitializer) block);
        }
        return block;
    }

    public static Block registerBlock(final Block block, final Class<? extends ItemBlock> itemBlock) {
        return GameRegistry.registerBlock(block, itemBlock, ModReference.getUnlocalizedInternalName(block));
    }

    public static void init() {
        steamer = registerBlock(addBlock(new BlockSteamer()), ItemBlockSteamer.class);
        steamGenerator = registerBlock(addBlock(new BlockSteamGenerator()), ItemBlockSteamGenerator.class);
        for (IInitializer block : blocks) {
            block.initialize();
        }
    }
}
