package me.sirlennox.herobrinia.attacks;

import me.sirlennox.herobrinia.Attack;
import me.sirlennox.herobrinia.Utils;
import me.sirlennox.herobrinia.entities.EntityHerobrine;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundEvents;

public class Web extends Attack {
    public Web() {
        super("Web", "Spawn cobweb around someone");
    }

    @Override
    public void attack(Entity target, Entity herobrine) {
        target.playSound(SoundEvents.BLOCK_WOOL_BREAK, 10, 1);
        Utils.setBlockAtPos(target.world, target.getPos().x, target.getPos().y, target.getPos().z, Blocks.COBWEB);
        Utils.setBlockAtPos(target.world, target.getPos().x + 1, target.getPos().y, target.getPos().z, Blocks.COBWEB);
        Utils.setBlockAtPos(target.world, target.getPos().x + 1, target.getPos().y, target.getPos().z + 1, Blocks.COBWEB);
        Utils.setBlockAtPos(target.world, target.getPos().x, target.getPos().y, target.getPos().z + 1, Blocks.COBWEB);
        Utils.setBlockAtPos(target.world, target.getPos().x, target.getPos().y + 1, target.getPos().z, Blocks.COBWEB);
        Utils.setBlockAtPos(target.world, target.getPos().x + 1, target.getPos().y + 1, target.getPos().z, Blocks.COBWEB);
        Utils.setBlockAtPos(target.world, target.getPos().x + 1, target.getPos().y + 1, target.getPos().z + 1, Blocks.COBWEB);
        Utils.setBlockAtPos(target.world, target.getPos().x, target.getPos().y + 1, target.getPos().z + 1, Blocks.COBWEB);
    }
}
