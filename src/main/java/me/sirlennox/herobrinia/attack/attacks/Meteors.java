package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireballEntity;

public class Meteors extends Attack {

    public Meteors() {
        super("Meteors", "Spawn Meteors");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        FireballEntity fireball = new FireballEntity(target.world, target.getPos().x, target.getPos().y + 10, target.getPos().z, 0, 1, 0);
        fireball.explosionPower = 3;
        target.world.spawnEntity(fireball);
    }
}
