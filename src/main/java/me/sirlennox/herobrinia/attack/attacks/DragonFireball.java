package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.entity.projectile.FireballEntity;

public class DragonFireball extends Attack {

    public DragonFireball() {
        super("DragonFireball", "Spawn a DragonFireball");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        DragonFireballEntity fireball = new DragonFireballEntity(target.world, herobrine, 0, -1, 0);
        fireball.updatePosition(target.getPos().x, target.getPos().y + 20, target.getPos().z);
        target.world.spawnEntity(fireball);
    }
}
