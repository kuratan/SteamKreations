package de.kuratan.steamkreations.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler {
    public static Configuration configuration;

    public static void init(File configFile)
    {
        if (configuration == null)
        {
            configuration = new Configuration(configFile, true);
            loadConfiguration();
        }
    }

    protected static void loadConfiguration() {
        Config.Blocks.heater = configuration.getBoolean("blocks.heater.enabled", Configuration.CATEGORY_GENERAL, true, "Enable Heater");
        Config.Blocks.steamGenerator = configuration.getBoolean("blocks.steamGenerator.enabled", Configuration.CATEGORY_GENERAL, true, "Enable Steam Generator");
        Config.Blocks.steamer = configuration.getBoolean("blocks.steamer.enabled", Configuration.CATEGORY_GENERAL, true, "Enable Steamer");

        Config.Items.chocolate = configuration.getBoolean("items.chocolate.enabled", Configuration.CATEGORY_GENERAL, true, "Enable Chocolate");
        Config.Items.smallMeal = configuration.getBoolean("items.smallMeal.enabled", Configuration.CATEGORY_GENERAL, true, "Enable small Meal");
        Config.Items.steamedCarrot = configuration.getBoolean("items.steamedCarrot.enabled", Configuration.CATEGORY_GENERAL, true, "Enable steamed Carrot");

        Config.World.restaurant = configuration.getBoolean("world.restaurant.enabled", Configuration.CATEGORY_GENERAL, true, "Enable Restaurant");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
