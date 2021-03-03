package me.sirlennox.herobrinia.items;

import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.utils.Utils;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class HerobrineApple extends Item {
    public HerobrineApple() {
        super(new Settings().group(Main.HEROBRINIA_GROUP).food((new FoodComponent.Builder()).hunger(4).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20 * (60 * 60), 3), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 20 * (60 * 60), 3), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 20 * (60 * 60), 3), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 20 * (60 * 45), 3), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20 * (60 * 45), 3), 1.0F).alwaysEdible().build()));
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isFireproof() {
        return false;
    }

    @Override
    public boolean isFood() {
        return true;
    }

    @Override
    public Text getName() {
        return Text.of("§cHerobrine Apple");
    }

    @Override
    public Text getName(ItemStack is) {
        return Text.of("§cHerobrine Apple");
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        player.playSound(SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.MUSIC, 10, 1);
        super.onCraft(stack, world, player);
    }
}
