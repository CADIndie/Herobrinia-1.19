package me.sirlennox.herobrinia.entities.herobrine;

import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.utils.TimeUtil;
import me.sirlennox.herobrinia.utils.Utils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvironmentInterface;
import net.fabricmc.api.EnvironmentInterfaces;
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
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

@EnvironmentInterfaces({@EnvironmentInterface(
        value = EnvType.CLIENT,
        itf = SkinOverlayOwner.class
)})
public class EntityHerobrine extends TameableEntity implements SkinOverlayOwner {

    public TimeUtil attackDelayUtil;
    private final ServerBossBar bossBar;
    public static double followRange = 80000;
    public TargetPredicate targetPredicate = (new TargetPredicate()).setBaseMaxDistance(followRange);
    public LivingEntity target;

    public EntityHerobrine(EntityType<? extends TameableEntity> entityType, World world) {
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

    @Environment(EnvType.CLIENT)
    @Override
    public int getTeamColorValue() {
        return 0xFF0000;
    }

    public static final Item TAME_ITEM = Main.HEROBRINE_ROSE;
    public static final Item HEAL_ITEM = Main.HEROBRINE_INGOT;
    public final float healAmount = getMaxHealth() / 10;

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        if(this.world.isClient) return ActionResult.PASS;
        ItemStack itemStack = player.getStackInHand(hand);
        if(itemStack == null) return super.interactMob(player, hand);
        Item item = itemStack.getItem();
        if(item == null) return super.interactMob(player, hand);
        if (item == TAME_ITEM) {
            if(this.isTamed()) return ActionResult.FAIL;
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            this.setOwner(player);
            this.world.sendEntityStatus(this, (byte)7);
            this.setTarget(null);
            this.setAttacking(false);
            this.setAttacking(null);
            this.getNavigation().stop();
            if(this.followTargetGoal != null) {
                this.followTargetGoal.setTargetEntity(null);
            }
            return ActionResult.SUCCESS;
        }else if(item == HEAL_ITEM) {
            if(this.getHealth() == this.getMaxHealth()) return ActionResult.FAIL;
            if (!player.getAbilities().creativeMode) itemStack.decrement(1);
            float h = this.getHealth() + healAmount;
            if(h > getMaxHealth()) h = this.getMaxHealth();
            this.setHealth(h);
            return ActionResult.SUCCESS;
        }

        return super.interactMob(player, hand);
    }





    public FollowTargetGoal<PlayerEntity> followTargetGoal;

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.goalSelector.add(4, new MeleeAttackGoal(this, 1.5D, false) /*{
            @Override
            public boolean shouldContinue() {
                if(!EntityHerobrine.this.isTamed() || target == null) return super.shouldContinue();
                return EntityHerobrine.this.isAllowedToAttack(target)  && super.shouldContinue();
            }

            @Override
            public boolean canStart() {
                if(!EntityHerobrine.this.isTamed() || target == null) return super.canStart();
                return EntityHerobrine.this.isAllowedToAttack(target) && super.canStart();
            }

            @Override
            public void tick() {
                if(!EntityHerobrine.this.isTamed() || EntityHerobrine.this.isAllowedToAttack(this.mob.getTarget())) {
                    super.tick();
                }
            }
        }*/);
        this.targetSelector.add(2 , this.followTargetGoal = new FollowTargetGoal<PlayerEntity>(this, PlayerEntity.class, false) {

            @Override
            protected void findClosestTarget() {
                if(EntityHerobrine.this.isTamed()) {
                    LivingEntity e = EntityHerobrine.this.getOwnerAttackingEntity();
                    if(EntityHerobrine.this.isAllowedToAttack(e)) this.targetEntity = e;
                }else {
                    super.findClosestTarget();
                }
            }
        });
        this.goalSelector.add(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.add(7, new AnimalMateGoal(this, 1.0D));
        super.initGoals();
    }

    @Override
    public boolean canAttackWithOwner(LivingEntity a, LivingEntity owner) {
        return isAllowedToAttack(target, owner);
    }

    private LivingEntity getOwnerAttackingEntity() {
        return getOwnerAttackingEntity(getOwner());
    }

    private LivingEntity getOwnerAttackingEntity(LivingEntity owner) {
        return !isTamed() ? null : owner == null ? null : owner.getAttacker() == null ? owner.getAttacking() : owner.getAttacker();
    }

    private boolean isAllowedToAttack(LivingEntity e, LivingEntity owner) {
        if(e == null) return false;
        if(!isTamed()) return !e.equals(this);
        if(owner == null) return false;
        LivingEntity a = getOwnerAttackingEntity();
        if(a == null || a.equals(this) || a.equals(owner)) return false;
        if(a instanceof TameableEntity) {
            TameableEntity t = (TameableEntity) a;
            if(!t.isTamed() || t.getOwner() == null) {
                return a.equals(e);
            } else if(t.getOwner().equals(owner)) {
                return false;
            }
        }
        return a.equals(e);
    }

    private boolean isAllowedToAttack(LivingEntity e) {
       return isAllowedToAttack(e, getOwner());
    }

    @Override
    public void onDeath(DamageSource source) {
		if(this.world.isClient()) return;
        this.bossBar.clearPlayers();
        Entity killer = source.getAttacker();
        if(killer == null) killer = this.getDamageTracker().getBiggestAttacker();

        if(killer instanceof ProjectileEntity) killer = ((ProjectileEntity) killer).getOwner();
        if(killer instanceof ThrownEntity) killer = ((ThrownEntity) killer).getOwner();

        Utils.spawnLightning(this.world, this.getPos(), true);
        Utils.setBlocks(this.world, this.getPos(), this.getPos().add(1, 0, 1), Blocks.NETHERITE_BLOCK);
        Utils.setBlockAtPos(this.world, this.getPos().x, this.getPos().y - 1, this.getPos().z, Main.HEROBRINE_BLOCK);
        Utils.setBlockAtPos(this.world, this.getPos().x + 1, this.getPos().y - 1, this.getPos().z, Blocks.NETHERITE_BLOCK);
        Utils.setBlockAtPos(this.world, this.getPos().x - 1, this.getPos().y - 1, this.getPos().z, Blocks.NETHERITE_BLOCK);
        Utils.setBlockAtPos(this.world, this.getPos().x, this.getPos().y - 1, this.getPos().z + 1, Blocks.NETHERITE_BLOCK);
        Utils.setBlockAtPos(this.world, this.getPos().x, this.getPos().y - 1, this.getPos().z - 1, Blocks.NETHERITE_BLOCK);

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
        if(source == DamageSource.FALL || source == DamageSource.anvil() || source == DamageSource.CACTUS || source == DamageSource.fallingBlock() || source == DamageSource.SWEET_BERRY_BUSH || source == DamageSource.IN_WALL) return false;
        if(this.isDead() || this.getHealth() - amount <= 0) return super.damage(source, amount);
        Entity attacker = source.getAttacker();
        LivingEntity entity = null;


        if(attacker instanceof ProjectileEntity) attacker = ((ProjectileEntity) attacker).getOwner();
        if(attacker instanceof ThrownEntity) attacker = ((ThrownEntity) attacker).getOwner();



        if(attacker instanceof LivingEntity && !(attacker instanceof EntityHerobrine)) entity = (LivingEntity) attacker;
        if(!isAllowedToAttack(entity)) return super.damage(source, amount);

        try {
            if(!this.world.isClient()) Utils.randomAttack(entity, this);
        } catch (Throwable ignored) {}
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
        super.tick();
        this.bossBar.setPercent(this.getHealth() / Math.max(this.getHealth(), this.getMaxHealth()));
        if(!this.isDead()) {
            try {
                LivingEntity nearest = this.getNearestPlayerEntity();
                if(this.isTamed()) nearest = getOwnerAttackingEntity();
                if (isAllowedToAttack(nearest)) {

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
    public boolean isFireImmune() {
        return true;
    }

    @Override
    public Text getName() {
        return new TextComponent(this.getEntityName());
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

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }
}
