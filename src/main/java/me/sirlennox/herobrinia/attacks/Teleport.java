package me.sirlennox.herobrinia.attacks;

import me.sirlennox.herobrinia.Attack;
import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.entities.EntityHerobrine;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class Teleport extends Attack {
    public Teleport() {
        super("Teleport", "Teleport someone to a random position in their near");
    }

    @Override
    public void attack(Entity target, Entity herobrine) {
        target.teleport(target.getPos().x + (Main.rndm.nextBoolean() ? Main.rndm.nextInt(20) : Main.rndm.nextInt(20)), target.getPos().y + (Main.rndm.nextBoolean() ? Main.rndm.nextInt(10) : Main.rndm.nextInt(10)), target.getPos().z + (Main.rndm.nextBoolean() ? Main.rndm.nextInt(20) : Main.rndm.nextInt(20)));
        target.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 10, 1);
    }
}
