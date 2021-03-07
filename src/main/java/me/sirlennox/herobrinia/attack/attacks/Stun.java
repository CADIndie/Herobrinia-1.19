package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.SoundEvents;

public class Stun extends Attack {
    public Stun() {
        super("Stun", "Stun somebody");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        target.playSound(SoundEvents.BLOCK_ANVIL_FALL, 10, 1);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 5, 255));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 20 * 5, 2));

    }
}
