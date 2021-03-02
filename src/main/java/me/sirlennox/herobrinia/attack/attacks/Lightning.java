package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

public class Lightning extends Attack {
    public Lightning() {
        super("Lightning", "Struck somebody by lightning");
    }

    @Override
    public void attack(LivingEntity target, Entity herobrine) {
        Utils.spawnLightning(target.world, target.getPos());
        Utils.setBlockAtPos(target.world, target.getPos().add(new Vec3d(0, -1, 0)), Blocks.INFESTED_CRACKED_STONE_BRICKS);
    }
}
