package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.SoundEvents;

public class Freeze extends Attack {

    public Freeze() {
        super("Freeze", "Freezes the enemy");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        target.playSound(SoundEvents.BLOCK_GLASS_BREAK, 10, 1);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 5, 255));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20 * 5, 255));
    }
}