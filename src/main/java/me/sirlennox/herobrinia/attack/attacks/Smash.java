package me.sirlennox.herobrinia.attack.attacks;

import com.google.common.collect.Lists;
import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class Smash extends Attack {
    public Smash() {
        super("Smash", "Smashes a player");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        FallingBlockEntity fallingBlockEntity = new FallingBlockEntity(target.world, target.getPos().x, target.getPos().y + 10, target.getPos().z, Blocks.BEDROCK.getDefaultState()) {
            @Override
            public boolean handleFallDamage(float fallDistance, float damageMultiplier) {
                int i = MathHelper.ceil(fallDistance - 1.0F);
                if (i > 0) {
                    List<Entity> list = Lists.newArrayList(this.world.getOtherEntities(this, this.getBoundingBox()));
                    DamageSource damageSource = DamageSource.FALLING_BLOCK;

                    for (Entity entity : list) {
                        entity.damage(damageSource, (float) 20);
                        entity.playSound(SoundEvents.BLOCK_ANVIL_FALL, 10, 1);
                    }
                }

                return false;
            }
        };
        fallingBlockEntity.setHurtEntities(true);
        fallingBlockEntity.timeFalling = 100000;
        target.world.spawnEntity(fallingBlockEntity);
    }
}
