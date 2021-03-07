package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.world.ServerWorld;

public class VexSpawner extends Attack {
    public VexSpawner() {
        super("VexSpawner", "Spawn vexes");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        for(int i = 0; i < 20; i++) {
            EntityType.VEX.spawnFromItemStack((ServerWorld) herobrine.getEntityWorld(), null, null, herobrine.getBlockPos(), SpawnReason.EVENT, true, false);
        }
    }
}
