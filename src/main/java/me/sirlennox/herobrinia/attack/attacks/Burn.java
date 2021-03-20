package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.entity.LivingEntity;

public class Burn extends Attack {
    public Burn() {
        super("Burn", "Burn your enemies");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        if(!target.isFireImmune()) target.setOnFireFor(60 * 10);
    }
}
