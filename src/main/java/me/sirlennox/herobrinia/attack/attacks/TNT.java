package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;

public class TNT extends Attack {

    public TNT() {
        super("TNT", "Let the TNT rain begin!");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        for(int i = 0; i < 10; i++) {
            TntEntity e = new TntEntity(target.world, target.getPos().x, target.getPos().y, target.getPos().z, herobrine);
            target.world.spawnEntity(e);
        }
    }
}
