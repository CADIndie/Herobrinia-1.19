package me.sirlennox.herobrinia.entities.herobrine;

import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.utils.TimeUtil;
import me.sirlennox.herobrinia.utils.Utils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.EnvironmentInterface;
import net.fabricmc.api.EnvironmentInterfaces;
import net.fabricmc.fabric.api.server.PlayerStream;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.entity.feature.SkinOverlayOwner;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

@EnvironmentInterfaces({@EnvironmentInterface(
        value = EnvType.CLIENT,
        itf = SkinOverlayOwner.class
)})
public class EntityHerobrine extends PathAwareEntity implements SkinOverlayOwner {

    public TimeUtil attackDelayUtil;
    private final ServerBossBar bossBar;
    public static double followRange = 80000;
    public TargetPredicate targetPredicate = (new TargetPredicate()).setBaseMaxDistance(followRange);
    public PlayerEntity target;

    public EntityHerobrine(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
	    this.attackDelayUtil = new TimeUtil();
        this.bossBar = (ServerBossBar)(new ServerBossBar(this.getDisplayName(), BossBar.Color.RED, BossBar.Style.PROGRESS)).setDarkenSky(true);
	    try {
            if (world.isClient) {
                this.setGlowing(true);
            }
        } catch (Throwable ignored) { }
    }


    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, followRange).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23000000417232513D * 1.5).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0D).add(EntityAttributes.GENERIC_MAX_HEALTH, 1000).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1);
    }



    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.goalSelector.add(4, new MeleeAttackGoal(this, 1.5D, false));
        this.targetSelector.add(2 , new FollowTargetGoal<>(this, PlayerEntity.class, false));
        super.initGoals();
    }


    @Override
    protected void onKilledBy(@Nullable LivingEntity killer) {
        if(this.world.isClient()) return;
        this.bossBar.clearPlayers();
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
            Utils.giveItem((PlayerEntity) killer, is);
        }
        super.onKilledBy(killer);
    }


    @Override
    public void onDeath(DamageSource source) {
        Entity killer = source.getAttacker();
        if(killer == null) killer = this.getDamageTracker().getBiggestAttacker();

        if(killer instanceof ProjectileEntity) killer = ((ProjectileEntity) killer).getOwner();
        if(killer instanceof ThrownEntity) killer = ((ThrownEntity) killer).getOwner();

        Utils.spawnLightning(this.world, this.getPos());
        Utils.setBlocks(this.world, this.getPos(), this.getPos().add(1, 0, 1), Blocks.NETHERITE_BLOCK);
        Utils.setBlockAtPos(this.world, this.getPos().x, this.getPos().y - 1, this.getPos().z, Main.HEROBRINE_BLOCK);
        Utils.setBlockAtPos(this.world, this.getPos().x + 1, this.getPos().y - 1, this.getPos().z, Blocks.GOLD_BLOCK);
        Utils.setBlockAtPos(this.world, this.getPos().x - 1, this.getPos().y - 1, this.getPos().z, Blocks.GOLD_BLOCK);
        Utils.setBlockAtPos(this.world, this.getPos().x, this.getPos().y - 1, this.getPos().z + 1, Blocks.GOLD_BLOCK);
        Utils.setBlockAtPos(this.world, this.getPos().x, this.getPos().y - 1, this.getPos().z - 1, Blocks.GOLD_BLOCK);

        ItemStack is = new ItemStack(Main.HAND_OF_HEROBRINE.asItem(), 1);
        if(killer instanceof PlayerEntity) {
            killer.playSound(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, 10, 1);
            ((PlayerEntity) killer).addExperience(10000);
            ((PlayerEntity) killer).setHealth(((PlayerEntity) killer).getMaxHealth());
            Utils.giveItem((PlayerEntity) killer, is);
        }else {
            ItemEntity itemEntity = new ItemEntity(this.world, this.getX(), this.getY(), this.getZ(), is);
            itemEntity.setPickupDelay(40);
            getEntityWorld().spawnEntity(itemEntity);
        }



        super.onDeath(source);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if(source == DamageSource.FALL || source == DamageSource.ANVIL || source == DamageSource.CACTUS || source == DamageSource.FALLING_BLOCK || source == DamageSource.SWEET_BERRY_BUSH || source == DamageSource.IN_WALL) return false;
        return super.damage(source, amount);
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }
    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    @Override
    public void setCustomName(@Nullable Text name) {
        super.setCustomName(name);
        this.bossBar.setName(this.getDisplayName());
    }



    @Override
    public void tick() {
        this.bossBar.setPercent(this.getHealth() / Math.max(this.getHealth(), this.getMaxHealth()));
        if(!this.isDead()) {
            try {
                PlayerEntity nearest = this.getNearestPlayerEntity();
                if (nearest != null) {

                    if (this.attackDelayUtil.hasReached(Main.herobrineAttackDelay)) {
                        this.attackDelayUtil.reset();

                        if (!this.world.isClient()) Utils.randomAttack(nearest, this);
                        target = nearest;
                    }

                  /*  if (nearest.distanceTo(this) > followRange - 5) {
                        double x = nearest.getPos().x + (Main.rndm.nextBoolean() ? Main.rndm.nextInt(3) : -Main.rndm.nextInt(3));
                        double y = nearest.getPos().y;
                        double z = nearest.getPos().z + (Main.rndm.nextBoolean() ? Main.rndm.nextInt(3) : -Main.rndm.nextInt(3));
                        //                BlockPos bp = new BlockPos(x, y, z);
                        if (!nearest.getEntityWorld().equals(this.getEntityWorld()))
                            this.setWorld(nearest.getEntityWorld());
          *//*                 BlockState bs1 = this.getEntityWorld().getBlockState(bp);
                            BlockState bs2 = this.getEntityWorld().getBlockState(bp.up(1));
                            if(bs1 != null && bs2 != null && (!bs1.isOpaque() || !bs2.isOpaque())) {
                                x = nearest.getX();
                                y = nearest.getY();
                                z = nearest.getZ();
                            }


                            bp = new BlockPos(x, y, z);
                            bs1 = this.getEntityWorld().getBlockState(bp);
                            bs2 = this.getEntityWorld().getBlockState(bp.up(1));
*//*
                        //if((bs1 == null || bs1.isOpaque()) || (bs2 == null || bs2.isOpaque())) {
                        this.teleport(x, y, z);
                        //}


                    }*/
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
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
    protected void applyDamage(DamageSource source, float amount) {
        if(this.isDead()) return;
        Entity attacker = source.getAttacker();
        LivingEntity entity = null;


        if(attacker instanceof ProjectileEntity) attacker = ((ProjectileEntity) attacker).getOwner();
        if(attacker instanceof ThrownEntity) attacker = ((ThrownEntity) attacker).getOwner();



        if(attacker instanceof LivingEntity && !(attacker instanceof EntityHerobrine)) entity = (LivingEntity) attacker;

        if(attacker == null) return;

        try {
            if(!this.world.isClient()) Utils.randomAttack(entity, this);
        } catch (Throwable t) {}
        super.applyDamage(source, amount);
    }



    @Override
    public boolean isFireImmune() {
        return true;
    }

    @Override
    public Text getName() {
        return new LiteralText(this.getEntityName());
    }

    @Override
    public String getEntityName() {
        return "§cHerobrine";
    }

    @Override
    public Text getDisplayName() {
        return Team.modifyText(this.getScoreboardTeam(), this.getName()).styled((style) -> style.withHoverEvent(this.getHoverEvent()).withInsertion(this.getUuidAsString()));
    }

    @Override
    public @Nullable Text getCustomName() {
        return getDisplayName();
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
                    if (!((PlayerEntity) o).isSpectator() && !((PlayerEntity) o).isDead()) {
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

    public ServerPlayerEntity getNearestServerPlayerEntity() {
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

    public PlayerEntity getNearestPlayerEntity() {
        return this.world.getClosestPlayer(this.targetPredicate, this, this.getX(), this.getEyeY(), this.getZ());
    }

    @Override
    public boolean shouldRenderOverlay() {
        return true;
    }
}
