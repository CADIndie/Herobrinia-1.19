package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.registry.Registry;

public class EveryEffect extends Attack {
    public EveryEffect() {
        super("EveryEffect", "Get every effect in the game");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        Registry.STATUS_EFFECT.getEntries().forEach(e -> target.addStatusEffect(new StatusEffectInstance(e.getValue(), 20 * 20, 6)));
    }
}
