package me.sirlennox.herobrinia.entities.herobrine;

import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.utils.TimeUtil;
import me.sirlennox.herobrinia.utils.Utils;
import net.fabricmc.fabric.api.server.PlayerStream;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.entity.feature.SkinOverlayOwner;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Objects;

public class EntityHerobrine extends PathAwareEntity implements SkinOverlayOwner {

    public TimeUtil attackDelayHelper;
    public float health;


    public EntityHerobrine(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
	    this.attackDelayHelper = new TimeUtil();
	    this.setHealth(1000);
	    world.setThunderGradient(0);
	    world.setLightningTicksLeft(0);
    }

    @Override
    public void setHealth(float health) {
        this.health = health;
    }

    @Override
    public float getHealth() {
        return health;
    }

    @Override
    protected void onKilledBy(@Nullable LivingEntity killer) {
        if(this.world.isClient()) return;
        if(killer instanceof PlayerEntity) {
            killer.playSound(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, 10, 1);
            ((PlayerEntity) killer).addExperience(10000);
            killer.setHealth(killer.getMaxHealth());
            Utils.spawnLightning(this.world, this.getPos());
            Utils.setBlocks(this.world, this.getPos(), this.getPos().add(1, 0, 1), Blocks.NETHERITE_BLOCK);
            Utils.setBlockAtPos(this.world, this.getPos().x, this.getPos().y - 1, this.getPos().z, Main.HEROBRINE_BLOCK);
            Utils.setBlockAtPos(this.world, this.getPos().x + 1, this.getPos().y - 1, this.getPos().z, Blocks.GOLD_BLOCK);
            Utils.setBlockAtPos(this.world, this.getPos().x - 1, this.getPos().y - 1, this.getPos().z, Blocks.GOLD_BLOCK);
            Utils.setBlockAtPos(this.world, this.getPos().x, this.getPos().y - 1, this.getPos().z + 1, Blocks.GOLD_BLOCK);
            Utils.setBlockAtPos(this.world, this.getPos().x, this.getPos().y - 1, this.getPos().z - 1, Blocks.GOLD_BLOCK);
            ItemStack is = new ItemStack(Main.HAND_OF_HEROBRINE.asItem(), 1);
            ((PlayerEntity) killer).giveItemStack(is);
        }
        super.onKilledBy(killer);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if(source == DamageSource.FALL || source == DamageSource.ANVIL || source == DamageSource.CACTUS || source == DamageSource.FALLING_BLOCK || source == DamageSource.SWEET_BERRY_BUSH || source == DamageSource.IN_WALL || source == DamageSource.OUT_OF_WORLD) return false;
        return super.damage(source, amount);
    }

    public ServerPlayerEntity target;

    public int ticksExisted = 0;

    @Override
    public void tick() {
        if(target != null) this.lookAtEntity(target, 360, 360);
        if(!this.isDead()) {
            try {
                ServerPlayerEntity nearest = this.getNearestEntity();
            if (this.attackDelayHelper.hasReached(Main.herobrineAttackDelay)) {
                this.attackDelayHelper.reset();
                if (nearest != null) {
                    if(!this.world.isClient()) Utils.randomAttack(nearest, this);
                    target = nearest;
                }

            }

            if(nearest != null) {
                if (ticksExisted % 60 == 0) {
                    if (nearest.distanceTo(this) > 10 && nearest.getPos().y > 0) {
                        this.teleport(nearest.getPos().x + (Main.rndm.nextBoolean() ? Main.rndm.nextInt(3) : -Main.rndm.nextInt(3)), nearest.getPos().y, nearest.getPos().z + (Main.rndm.nextBoolean() ? Main.rndm.nextInt(3) : -Main.rndm.nextInt(3)));
                    }
                }
            }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        ticksExisted++;
        super.tick();
    }

    @Override
    public boolean isImmuneToExplosion() {
        return false;
    }


    @Override
    public boolean canBreatheInWater() {
        return true;
    }

    @Override
    public boolean shouldRenderName() {
        return true;
    }

    @Override
    public boolean handleAttack(Entity attacker) {

        if(this.isDead()) return false;
        if(!(attacker instanceof PlayerEntity)) return false;
        try {
            if(!this.world.isClient()) Utils.randomAttack((LivingEntity) attacker, this);
	    } catch (Throwable t) {}
        return super.handleAttack(attacker);
    }


    @Override
    public boolean isFireImmune() {
        return true;
    }

    @Override
    public Text getName() {
        return Text.of("§cHerobrine");
    }

    @Override
    public String getEntityName() {
        return "§cHerobrine";
    }

    @Override
    public Text getDisplayName() {
        return Text.of("§cHerobrine");
    }

    @Override
    public @Nullable Text getCustomName() {
        return Text.of("§cHerobrine");
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier) {
        return false;
    }





    public ArrayList<ServerPlayerEntity> getPlayers() {
        ArrayList<ServerPlayerEntity> array = new ArrayList<>();
        if(this.getServer() != null) {
            for (Object o : PlayerStream.all(this.getServer()).toArray()) {
                if (o instanceof PlayerEntity) {
                    if (!((PlayerEntity) o).isSpectator()) {
                        array.add((ServerPlayerEntity) o);
                    }
                }
            }
        }

        return array;
    }

    @Override
    public boolean cannotDespawn() {
        return true;
    }

    public ServerPlayerEntity getNearestEntity() {
        ServerPlayerEntity back = null;
        for(ServerPlayerEntity spe : getPlayers()) {
            if(spe.getUuid() != this.getUuid()) {
                if (back == null) {
                    back = spe;
                } else if (spe.distanceTo(this) < back.distanceTo(this)) {
                    back = spe;
                }
            }
        }
        return back;
    }

    @Override
    public boolean shouldRenderOverlay() {
        return true;
    }
}
