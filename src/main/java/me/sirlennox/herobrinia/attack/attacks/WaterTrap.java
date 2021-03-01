package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class WaterTrap extends Attack {

    public WaterTrap() {
        super("WaterTrap", "Traps somebody in water and obsidian");
    }

    @Override
    public void attack(LivingEntity target, Entity herobrine) {
        Utils.setBlocks(target.world, target.getPos().add(-1, -1, -1), target.getPos().add(1, 1, 1), Blocks.OBSIDIAN);
        Utils.setBlocks(target.world, target.getPos().add(-1, 1, -1), target.getPos().add(1, 1, 1), Blocks.WHITE_STAINED_GLASS_PANE);
        Utils.setBlocks(target.world, target.getPos(), target.getPos().add(0, 1, 0), Blocks.WATER);


    }
}
