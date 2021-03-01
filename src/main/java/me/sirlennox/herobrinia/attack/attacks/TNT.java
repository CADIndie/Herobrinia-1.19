package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;

public class TNT extends Attack {

    public TNT() {
        super("TNT", "Let the TNT rain begin!");
    }

    @Override
    public void attack(LivingEntity target, Entity herobrine) {
        TntEntity e = new TntEntity(EntityType.TNT, target.world);
        e.setPos(target.getPos().x, target.getPos().y, target.getPos().z);
        e.setFuse(60);
        target.world.spawnEntity(e);
    }
}
