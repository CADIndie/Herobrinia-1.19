package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvents;

public class ObsidianTrap extends Attack {
    public ObsidianTrap() {
        super("ObsidianTrap", "Traps somebody with obsidian");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        target.playSound(SoundEvents.BLOCK_GLASS_BREAK, 10, 1);
        Utils.setBlocks(target.world, target.getPos().add(-1, -1, -1), target.getPos().add(2,  2, 2), Blocks.OBSIDIAN);
    }
}
