package me.sirlennox.herobrinia.attacks;

import me.sirlennox.herobrinia.Attack;
import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.Utils;
import me.sirlennox.herobrinia.entities.EntityHerobrine;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Random;

public class Void extends Attack {
    public Void() {
        super("void", "Void a player");
    }

    @Override
    public void attack(Entity target, Entity herobrine) {
        new Thread(() -> {
            try {
             /*   for(int i = (int) target.getPos().y; i > 0; i--) {
                    Utils.setBlockAtPos(target.world, target.getPos().x, i, target.getPos().z, Blocks.AIR);
                }*/
                Utils.setBlocks(target.world, target.getPos().x, target.getPos().x, target.getPos().y, 0, target.getPos().z, target.getPos().z, Blocks.AIR);
            }catch (Exception e) {}
        }, "PlayerVoidThread").start();
    }
}
