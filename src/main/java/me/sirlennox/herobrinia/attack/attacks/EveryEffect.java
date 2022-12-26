package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;

public class EveryEffect extends Attack {
    public EveryEffect() {
        super("EveryEffect", "Get every effect in the game");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        Registries.STATUS_EFFECT.getIndexedEntries().forEach(e -> target.addStatusEffect(new StatusEffectInstance(e.value(), 20 * 20, 6)));
    }
}
