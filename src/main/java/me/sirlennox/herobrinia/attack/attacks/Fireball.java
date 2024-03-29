package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.Vec3d;

public class Fireball extends Attack {

    public Fireball() {
        super("Fireball", "Spawn a fireball");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        FireballEntity fireball = new FireballEntity(target.world, herobrine,0, -1, 0, 4);
        fireball.updatePosition(target.getPos().x, target.getPos().y + 20, target.getPos().z);
        target.world.spawnEntity(fireball);
    }
}
