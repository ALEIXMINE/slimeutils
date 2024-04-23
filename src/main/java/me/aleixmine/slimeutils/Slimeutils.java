package me.aleixmine.slimeutils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;
import me.aleixmine.slimeutils.items.CustomArmor;
import me.aleixmine.slimeutils.utils.ArmorType;

public class Slimeutils extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        // Read something from your config.yml
        Config cfg = new Config(this);

        // if (cfg.getBoolean("options.auto-update")) {
        // // You could start an Auto-Updater for example
        // new GitHubBuildsUpdater(this, getFile(),
        // "aleixmine/Slimeutils/master").start();
        // }
        registerItems();
    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * You will need to return a reference to your Plugin here.
         * If you are using your main class for this, simply return "this".
         */
        return this;
    }

    public ItemStack[] ArmorRecipe(ArmorType armor, ItemStack item) {
        if (armor == ArmorType.HELMET) {
            return new ItemStack[] { item, item, item, item, null, item, null, null, null };
        } else if (armor == ArmorType.CHESTPLATE) {
            return new ItemStack[] { item, null, item, item, item, item, item, item, item };
        } else if (armor == ArmorType.LEGGINGS) {
            return new ItemStack[] { item, item, item, item, null, item, item, null, item };
        } else if (armor == ArmorType.BOOTS) {
            return new ItemStack[] { item, null, item, item, null, item, null, null, null };
        } else {
            return new ItemStack[] { null, null, null, null, item, null, null, null, null };
        }
    }

    private void registerItems() {
        ItemGroup SLIMEUTILS = new ItemGroup(
                new NamespacedKey(this, "slimeutils"),
                new CustomItemStack(Material.SADDLE, "&5Utils", "", "&a> Click to open"));

        CustomArmor EMERALD_HELMET = new CustomArmor(SLIMEUTILS,
                new SlimefunItemStack("EMERALD_HELMET",
                        Material.LEATHER_HELMET, Color.GREEN, "&aEmerald Helmet"),
                RecipeType.ENHANCED_CRAFTING_TABLE, ArmorRecipe(ArmorType.HELMET, new ItemStack(Material.EMERALD)));
        CustomArmor EMERALD_CHESTPLATE = new CustomArmor(SLIMEUTILS,
                new SlimefunItemStack("EMERALD_CHESPLATE",
                        Material.LEATHER_CHESTPLATE, Color.GREEN, "&aEmerald Chestplate"),
                RecipeType.ENHANCED_CRAFTING_TABLE, ArmorRecipe(ArmorType.CHESTPLATE, new ItemStack(Material.EMERALD)));
        CustomArmor EMERALD_LEGGINGS = new CustomArmor(SLIMEUTILS,
                new SlimefunItemStack("EMERALD_LEGGINGS",
                        Material.LEATHER_LEGGINGS, Color.GREEN, "&aEmerald Leggings"),
                RecipeType.ENHANCED_CRAFTING_TABLE, ArmorRecipe(ArmorType.LEGGINGS, new ItemStack(Material.EMERALD)));
        CustomArmor EMERALD_BOOTS = new CustomArmor(SLIMEUTILS,
                new SlimefunItemStack("EMERALD_BOOTS",
                        Material.LEATHER_BOOTS, Color.GREEN, "&aEmerald Boots"),
                RecipeType.ENHANCED_CRAFTING_TABLE, ArmorRecipe(ArmorType.BOOTS, new ItemStack(Material.EMERALD)));

        EMERALD_HELMET.register(this, 4, 3, ArmorType.HELMET);
        EMERALD_CHESTPLATE.register(this, 9, 3, ArmorType.CHESTPLATE);
        EMERALD_LEGGINGS.register(this, 7, 3, ArmorType.LEGGINGS);
        EMERALD_BOOTS.register(this, 4, 3, ArmorType.BOOTS);

        Research EMERALD_ARMOR = new Research(new NamespacedKey(this, "emerald_armor"), 1001, "&aEmerald Armor", 20);
        EMERALD_ARMOR.addItems(EMERALD_HELMET,EMERALD_CHESTPLATE,EMERALD_LEGGINGS,EMERALD_BOOTS);
    }
}
