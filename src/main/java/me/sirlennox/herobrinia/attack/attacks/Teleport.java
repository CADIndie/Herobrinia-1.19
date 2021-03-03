package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import me.sirlennox.herobrinia.Main;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvents;

public class Teleport extends Attack {
    public Teleport() {
        super("Teleport", "Teleport someone to a random position in their near");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        target.teleport(target.getPos().x + (Main.rndm.nextBoolean() ? Main.rndm.nextInt(20) : Main.rndm.nextInt(20)), target.getPos().y + (Main.rndm.nextBoolean() ? Main.rndm.nextInt(10) : Main.rndm.nextInt(10)), target.getPos().z + (Main.rndm.nextBoolean() ? Main.rndm.nextInt(20) : Main.rndm.nextInt(20)));
        target.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 10, 1);
    }
}
