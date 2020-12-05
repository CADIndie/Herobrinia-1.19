package me.sirlennox.herobrinia.attacks;

import me.sirlennox.herobrinia.Attack;
import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.entities.EntityHerobrine;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class RandomTP extends Attack {
    public RandomTP() {
        super("RandomTP", "Teleport you randomly on the map");
    }

    @Override
    public void attack(Entity target, Entity herobrine) {
        target.teleport((Main.rndm.nextBoolean() ? Main.rndm.nextInt(80000) : -Main.rndm.nextInt(80000)), (Main.rndm.nextBoolean() ? Main.rndm.nextInt(100) : -Main.rndm.nextInt(100)), (Main.rndm.nextBoolean() ? Main.rndm.nextInt(80000) : -Main.rndm.nextInt(80000)));
    }
}
