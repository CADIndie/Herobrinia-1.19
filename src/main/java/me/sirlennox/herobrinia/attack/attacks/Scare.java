package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;

public class Scare extends Attack {

    public Scare() {
        super("Scare", "Scares somebody");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        target.playSound(SoundEvents.ENTITY_CAT_HISS, 10, 1);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 20 * 5, 2));
        for(int i = 0; i < 10; i++) {
            EntityType.BAT.spawnFromItemStack((ServerWorld) target.getEntityWorld(), null, null, target.getBlockPos(), SpawnReason.EVENT, true, false);
        }
    }
}
