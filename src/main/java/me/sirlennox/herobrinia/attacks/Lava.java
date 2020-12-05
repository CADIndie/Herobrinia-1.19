package me.sirlennox.herobrinia.attacks;

import me.sirlennox.herobrinia.Attack;
import me.sirlennox.herobrinia.Utils;
import me.sirlennox.herobrinia.entities.EntityHerobrine;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;

public class Lava extends Attack {
    public Lava() {
        super("Lava", "Lava someone");
    }

    @Override
    public void attack(Entity target, Entity herobrine) {
        target.playSound(SoundEvents.BLOCK_SLIME_BLOCK_BREAK, 10, 1);
        Utils.setBlockAtPos(target.world, target.getPos().x, target.getPos().y, target.getPos().z, Blocks.LAVA);
        Utils.setBlockAtPos(target.world, target.getPos().x + 1, target.getPos().y, target.getPos().z, Blocks.LAVA);
        Utils.setBlockAtPos(target.world, target.getPos().x + 1, target.getPos().y, target.getPos().z + 1, Blocks.LAVA);
        Utils.setBlockAtPos(target.world, target.getPos().x, target.getPos().y, target.getPos().z + 1, Blocks.LAVA);
    }
}
