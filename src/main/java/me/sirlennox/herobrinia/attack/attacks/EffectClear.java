package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.entity.LivingEntity;

public class EffectClear extends Attack {

    public EffectClear() {
        super("EffectClear", "Clears the effects of the target");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        target.clearStatusEffects();
    }
}
