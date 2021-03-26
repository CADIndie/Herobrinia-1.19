package me.sirlennox.herobrinia.attack.attacks.forremoval;

import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.entity.LivingEntity;

public class OneHit extends Attack {
    public OneHit() {
        super("OneHit", "OneHit a player");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        target.kill();
    }
}
