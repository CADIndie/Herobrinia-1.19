package me.sirlennox.herobrinia.attacks;

import me.sirlennox.herobrinia.Attack;
import me.sirlennox.herobrinia.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;

public class Cut extends Attack {
    public Cut() {
        super("Cut", "Destroy every blocks in your near");
    }

    @Override
    public void attack(Entity target, Entity herobrine) {
     //   new Thread(() -> {
            Utils.setBlocks(target.world, target.getPos().x - 10, target.getPos().x + 10, target.getPos().y - 10, target.getPos().y + 10, target.getPos().z - 10, target.getPos().z + 10, Blocks.AIR);
       // }, "BlockDestroyerThread").start();
    }
}
