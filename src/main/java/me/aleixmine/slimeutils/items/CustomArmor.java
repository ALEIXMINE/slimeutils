package me.aleixmine.slimeutils.items;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.aleixmine.slimeutils.Slimeutils;
import me.aleixmine.slimeutils.utils.ArmorType;

public class CustomArmor extends SlimefunItem {
    
    private double amountArmor;
    private double amountArmorToughness;
    private ArmorType armor;

    public CustomArmor(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public EquipmentSlot Transform (ArmorType armor) {
        if (ArmorType.HELMET==armor) {
            return EquipmentSlot.HEAD;
        } else if (ArmorType.CHESTPLATE==armor) {
            return EquipmentSlot.CHEST;
        } else if (ArmorType.LEGGINGS==armor) {
            return EquipmentSlot.LEGS;
        } else if (ArmorType.BOOTS==armor) {
            return EquipmentSlot.FEET;
        } else {
            return EquipmentSlot.HAND;
        }
     }

    @Override
    public void preRegister() {
        ItemStack item = this.getItem();
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
        ItemMeta meta = item.getItemMeta();
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", this.amountArmor,
                AttributeModifier.Operation.ADD_NUMBER, Transform(this.armor)));
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),
                "generic.armor_toughness", this.amountArmorToughness, AttributeModifier.Operation.ADD_NUMBER, Transform(this.armor)));
        item.setItemMeta(meta);
    }

    public void register(Slimeutils plugin, double armor_amount, double armor_amount_toughness, ArmorType armor) {
        this.amountArmor = armor_amount;
        this.amountArmorToughness = armor_amount_toughness;
        this.armor = armor;
        super.register(plugin);
    }
}
