package me.sirlennox.herobrinia.attack;

import net.minecraft.entity.LivingEntity;

public abstract class Attack {

    public String name;
    public String desc;

    public Attack(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public abstract void attack(LivingEntity target, LivingEntity herobrine);
}
