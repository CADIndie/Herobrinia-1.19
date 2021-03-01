package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class Void extends Attack {
    public Void() {
        super("void", "Void a player");
    }

    @Override
    public void attack(LivingEntity target, Entity herobrine) {
   //     new Thread(() -> {
            try {
             /*   for(int i = (int) target.getPos().y; i > 0; i--) {
                    Utils.setBlockAtPos(target.world, target.getPos().x, i, target.getPos().z, Blocks.AIR);
                }*/
                Utils.setBlocks(target.world, target.getPos().x, target.getPos().x, target.getPos().y, 0, target.getPos().z, target.getPos().z, Blocks.AIR);
            }catch (Exception e) {}
     //   }, "PlayerVoidThread").start();
    }
}
