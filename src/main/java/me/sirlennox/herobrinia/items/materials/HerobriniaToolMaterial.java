package me.sirlennox.herobrinia.items.materials;

import me.sirlennox.herobrinia.Main;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class HerobriniaToolMaterial implements ToolMaterial {
    @Override
    public int getDurability() {
        return 6000;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 36.0F;
    }

    @Override
    public float getAttackDamage() {
        return 8.0F;
    }

    @Override
    public int getMiningLevel() {
        return 4;
    }

    @Override
    public int getEnchantability() {
        return 15;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Main.HEROBRINE_INGOT, Main.HEROBRINE_BLOCK);
    }

}
