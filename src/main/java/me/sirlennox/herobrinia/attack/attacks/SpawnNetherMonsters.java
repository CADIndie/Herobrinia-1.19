package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.*;
import net.minecraft.server.world.ServerWorld;

import java.util.ArrayList;

public class SpawnNetherMonsters extends Attack {
    public SpawnNetherMonsters() {
        super("SpawnNetherMonsters", "Spawns nether monsters");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        ArrayList<MobEntity> monsters = new ArrayList<>();
        monsters.add(new MagmaCubeEntity(EntityType.MAGMA_CUBE, target.getEntityWorld()));
        monsters.add(new PiglinBruteEntity(EntityType.PIGLIN_BRUTE, target.world));
        monsters.add(new HoglinEntity(EntityType.HOGLIN, target.world));
        monsters.add(new GhastEntity(EntityType.GHAST, target.world));
        monsters.add(new WitherSkeletonEntity(EntityType.WITHER_SKELETON, target.world));
        monsters.add(new BlazeEntity(EntityType.BLAZE, target.world));

        for(MobEntity e : monsters) {
            e.getType().spawnFromItemStack((ServerWorld) target.getEntityWorld(), null, null, target.getBlockPos(), SpawnReason.EVENT, true, false);
        }

    }
}
