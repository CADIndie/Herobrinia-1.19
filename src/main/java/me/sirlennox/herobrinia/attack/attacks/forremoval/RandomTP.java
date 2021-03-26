package me.sirlennox.herobrinia.attack.attacks.forremoval;

import me.sirlennox.herobrinia.attack.Attack;
import me.sirlennox.herobrinia.Main;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvents;

/* Too OP */
public class RandomTP extends Attack {
    public RandomTP() {
        super("RandomTP", "Teleport you randomly on the map");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        target.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 10, 1);
        target.teleport((Main.rndm.nextBoolean() ? Main.rndm.nextInt(80000) : -Main.rndm.nextInt(80000)), (Main.rndm.nextBoolean() ? Main.rndm.nextInt(100) : -Main.rndm.nextInt(100)), (Main.rndm.nextBoolean() ? Main.rndm.nextInt(80000) : -Main.rndm.nextInt(80000)));
    }
}
