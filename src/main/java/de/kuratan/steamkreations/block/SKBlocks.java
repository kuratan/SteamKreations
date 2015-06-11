package de.kuratan.steamkreations.block;

import cpw.mods.fml.common.registry.GameRegistry;
import de.kuratan.steamkreations.block.heater.BlockHeater;
import de.kuratan.steamkreations.block.heater.ItemBlockHeater;
import de.kuratan.steamkreations.block.steam_generator.BlockSteamGenerator;
import de.kuratan.steamkreations.block.steam_generator.ItemBlockSteamGenerator;
import de.kuratan.steamkreations.block.steamer.BlockSteamer;
import de.kuratan.steamkreations.block.steamer.ItemBlockSteamer;
import de.kuratan.steamkreations.utils.IInitializer;
import de.kuratan.steamkreations.utils.ModReference;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;

public class SKBlocks {
    public static ArrayList<IInitializer> blocks = new ArrayList<IInitializer>();
    public static Block heater;
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

    public static void initialization() {
        heater = registerBlock(addBlock(new BlockHeater()), ItemBlockHeater.class);
        steamer = registerBlock(addBlock(new BlockSteamer()), ItemBlockSteamer.class);
        steamGenerator = registerBlock(addBlock(new BlockSteamGenerator()), ItemBlockSteamGenerator.class);
        for (IInitializer block : blocks) {
            block.initialization();
        }
    }

    public static void postInitialization() {
        GameRegistry.addRecipe(
                new ShapedOreRecipe(heater, "i i", " b ", " f ", 'i', "ingotIron", 'f', Blocks.furnace, 'b',
                                    Items.bucket));
        GameRegistry.addRecipe(
                new ShapedOreRecipe(steamer, " i ", "ici", " b ", 'i', "ingotIron", 'c', Blocks.chest, 'b',
                                    Items.bucket));
        GameRegistry.addRecipe(
                new ShapedOreRecipe(steamGenerator, " b ", "ifi", " i ", 'i', "ingotIron", 'f', Blocks.furnace, 'b',
                                    Items.bucket));
    }
}
