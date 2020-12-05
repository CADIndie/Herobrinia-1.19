package me.sirlennox.herobrinia.attacks;

import me.sirlennox.herobrinia.Attack;
import me.sirlennox.herobrinia.entities.EntityHerobrine;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

public class Hit extends Attack {
    public Hit() {
        super("Hit", "Hit someone");
    }

    @Override
    public void attack(Entity target, Entity herobrine) {
        target.damage(DamageSource.MAGIC, 10);
    }
}
