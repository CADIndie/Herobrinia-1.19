package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;

import java.util.ArrayList;

public class SpawnMonsters extends Attack {
    public SpawnMonsters() {
        super("SpawnMonsters", "Spawns monsters");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        ArrayList<MobEntity> monsters = new ArrayList<>();
        monsters.add(new SilverfishEntity(EntityType.SILVERFISH, target.getEntityWorld()));
        monsters.add(new ZombieEntity(target.world));
        monsters.add(new SkeletonEntity(EntityType.SKELETON, target.world));
        monsters.add(new EndermanEntity(EntityType.ENDERMAN, target.world));
        monsters.add(new CreeperEntity(EntityType.CREEPER, target.world));

        for(MobEntity e : monsters) {
            e.getType().spawnFromItemStack((ServerWorld) target.getEntityWorld(), null, null, target.getBlockPos(), SpawnReason.EVENT, true, false);
        }

    }
}
