package me.sirlennox.herobrinia;

import me.sirlennox.herobrinia.entities.EntityHerobrine;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public abstract class Attack {

    public String name;
    public String desc;

    public Attack(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public abstract void attack(Entity target, Entity herobrine);
}
