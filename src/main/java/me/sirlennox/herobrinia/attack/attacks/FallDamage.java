package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;

public class FallDamage extends Attack {
    public FallDamage() {
        super("FallDamage", "Get falldamage while falling");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        if(!target.isOnGround() && target.fallDistance > 3) {
            Utils.setBlockAtPos(target.world, target.getPos().add(0, -1, 0), Blocks.GLASS);
        }else {
            Utils.randomAttack(target, herobrine);
        }
    }
}
