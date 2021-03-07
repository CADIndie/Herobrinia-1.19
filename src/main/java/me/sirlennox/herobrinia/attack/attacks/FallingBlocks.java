package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;

public class FallingBlocks extends Attack {
    public FallingBlocks() {
        super("FallingBlocks", "Falling blocks from the air");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
      //  new Thread(() -> {
            Utils.setBlocks(target.world, target.getPos().x - 10, target.getPos().x + 10, target.getPos().y + 10, target.getPos().y + 13, target.getPos().z - 10, target.getPos().z + 10, Blocks.ANVIL);
        //}, "FallingBlocksPlacer").start();
    }
}
