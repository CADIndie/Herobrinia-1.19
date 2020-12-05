package me.sirlennox.herobrinia.attacks;

import me.sirlennox.herobrinia.Attack;
import me.sirlennox.herobrinia.entities.EntityHerobrine;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class OneHit extends Attack {
    public OneHit() {
        super("OneHit", "OneHit a player");
    }

    @Override
    public void attack(Entity target, Entity herobrine) {
        target.kill();
    }
}
