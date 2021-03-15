package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;

/* Not working */
public class WaterTrap extends Attack {

    public WaterTrap() {
        super("WaterTrap", "Traps somebody in water and obsidian");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        Utils.setBlocks(target.world, target.getPos().add(-1, -1, -1), target.getPos().add(2, 2, 2), Blocks.OBSIDIAN);
        Utils.setBlocks(target.world, target.getPos().add(-2, 2, -2), target.getPos().add(2, 2, 2), Blocks.WHITE_STAINED_GLASS_PANE);
        Utils.setBlocks(target.world, target.getPos(), target.getPos().add(0, 1, 0), Blocks.WATER);


    }
}
