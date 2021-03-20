package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;

public class Witherskull extends Attack {

    public Witherskull() {
        super("Witherskull", "Spawn a witherskull");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        WitherSkullEntity fireball = new WitherSkullEntity(target.world, herobrine, 0, -1, 0);
        fireball.updatePosition(target.getPos().x, target.getPos().y + 20, target.getPos().z);
        target.world.spawnEntity(fireball);
    }
}
