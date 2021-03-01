package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvents;

public class Web extends Attack {
    public Web() {
        super("Web", "Spawn cobweb around someone");
    }

    @Override
    public void attack(LivingEntity target, Entity herobrine) {
        target.playSound(SoundEvents.BLOCK_WOOL_BREAK, 10, 1);
        Utils.setBlocks(target.world, target.getPos().x - 5, target.getPos().x + 5, target.getPos().y, target.getPos().y + 1, target.getPos().z - 5, target.getPos().z + 5, Blocks.COBWEB);
    }
}
