package me.sirlennox.herobrinia.attacks;

import me.sirlennox.herobrinia.Attack;
import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.entities.EntityHerobrine;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;

public class Boost extends Attack {
    public Boost() {
        super("Boost", "Boost someone in a direction");
    }

    @Override
    public void attack(Entity target, Entity herobrine) {
        target.playSound(SoundEvents.ENTITY_FIREWORK_ROCKET_LAUNCH, 10, 1);
        target.setVelocity((Main.rndm.nextBoolean() ? Main.rndm.nextInt(10) : -Main.rndm.nextInt(10)), (Main.rndm.nextBoolean() ? Main.rndm.nextInt(10) : -Main.rndm.nextInt(10)), (Main.rndm.nextBoolean() ? Main.rndm.nextInt(10) : -Main.rndm.nextInt(10)));
    }
}
