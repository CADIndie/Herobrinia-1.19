package me.sirlennox.herobrinia.attacks;

import me.sirlennox.herobrinia.Attack;
import me.sirlennox.herobrinia.entities.EntityHerobrine;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

public class SpamHit extends Attack {
    public SpamHit() {
        super("SpamHit", "Spam hit someone");
    }

    @Override
    public void attack(Entity target, Entity herobrine) {
        for(int i = 0; i < 15; i++) {
            target.damage(DamageSource.MAGIC, 5);
        }
    }
}
