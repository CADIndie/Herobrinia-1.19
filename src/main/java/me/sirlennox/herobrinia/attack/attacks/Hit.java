package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class Hit extends Attack {
    public Hit() {
        super("Hit", "Hit someone");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        target.damage(DamageSource.MAGIC, 10);
    }
}
