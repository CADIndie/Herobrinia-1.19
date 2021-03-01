package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvents;

public class Lava extends Attack {
    public Lava() {
        super("Lava", "Lava someone");
    }

    @Override
    public void attack(LivingEntity target, Entity herobrine) {
        target.playSound(SoundEvents.BLOCK_SLIME_BLOCK_BREAK, 10, 1);
        Utils.setBlockAtPos(target.world, target.getPos().x, target.getPos().y, target.getPos().z, Blocks.LAVA);
        Utils.setBlockAtPos(target.world, target.getPos().x + 1, target.getPos().y, target.getPos().z, Blocks.LAVA);
        Utils.setBlockAtPos(target.world, target.getPos().x + 1, target.getPos().y, target.getPos().z + 1, Blocks.LAVA);
        Utils.setBlockAtPos(target.world, target.getPos().x, target.getPos().y, target.getPos().z + 1, Blocks.LAVA);
    }
}
