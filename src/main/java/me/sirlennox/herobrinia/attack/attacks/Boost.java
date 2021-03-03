package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import me.sirlennox.herobrinia.Main;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvents;

public class Boost extends Attack {
    public Boost() {
        super("Boost", "Boost someone in a direction");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        target.playSound(SoundEvents.ENTITY_FIREWORK_ROCKET_LAUNCH, 10, 1);
        target.setVelocity((Main.rndm.nextBoolean() ? Main.rndm.nextInt(10) : -Main.rndm.nextInt(10)), (Main.rndm.nextBoolean() ? Main.rndm.nextInt(10) : -Main.rndm.nextInt(10)), (Main.rndm.nextBoolean() ? Main.rndm.nextInt(10) : -Main.rndm.nextInt(10)));
    }
}
