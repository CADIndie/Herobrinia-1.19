package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.block.Blocks;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;

/* Not working */
public class Smash extends Attack {
    public Smash() {
        super("Smash", "Smashes a player");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        FallingBlockEntity fallingBlockEntity = new FallingBlockEntity(target.world, target.getPos().x, target.getPos().y + 10, target.getPos().z, Blocks.BEDROCK.getDefaultState());
        fallingBlockEntity.timeFalling = 100000;
        target.world.spawnEntity(fallingBlockEntity);
    }
}
