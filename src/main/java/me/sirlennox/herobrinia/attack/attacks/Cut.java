package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;

public class Cut extends Attack {
    public Cut() {
        super("Cut", "Destroy every blocks in your near");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
     //   new Thread(() -> {
            Utils.setBlocks(target.world, target.getPos().x - 10, target.getPos().x + 10, target.getPos().y - 10, target.getPos().y + 10, target.getPos().z - 10, target.getPos().z + 10, Blocks.AIR);
       // }, "BlockDestroyerThread").start();
    }
}
