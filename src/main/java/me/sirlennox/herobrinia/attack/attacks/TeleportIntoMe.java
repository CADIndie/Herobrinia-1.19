package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.entity.LivingEntity;

public class TeleportIntoMe extends Attack {
    public TeleportIntoMe() {
        super("TeleportIntoMe", "Teleports you into herobrine");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        target.teleport(herobrine.getPos().x, herobrine.getPos().y, herobrine.getPos().z, true);
    }
}
