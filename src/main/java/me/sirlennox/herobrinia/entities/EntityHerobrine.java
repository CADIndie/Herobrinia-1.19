package me.sirlennox.herobrinia.entities;

import me.sirlennox.herobrinia.AttackManager;
import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.TimeHelper;
import me.sirlennox.herobrinia.Utils;
import me.sirlennox.herobrinia.items.HandOfHerobrine;
import net.fabricmc.fabric.api.server.PlayerStream;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.entity.feature.SkinOverlayOwner;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.DefaultAttributeRegistry;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.jmx.Server;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class EntityHerobrine extends PathAwareEntity implements SkinOverlayOwner {

    public TimeHelper attackDelayHelper;
    public float health;


    public EntityHerobrine(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
	this.attackDelayHelper = new TimeHelper();
	this.setHealth(2000);
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
        if(killer instanceof PlayerEntity) {
            ((PlayerEntity) killer).playSound(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, 10, 1);
            ((PlayerEntity) killer).addExperience(Integer.MAX_VALUE);
          //  ((PlayerEntity) killer).playSound(SoundEvents.BLOCK_ANVIL_HIT, 10, 1);
            ((PlayerEntity) killer).setHealth(20);
            ((PlayerEntity) killer).sendMessage(Text.of("§cYou killed herobrine and got the §4§lHand of Herobrine§r§c!"), false);
            ((PlayerEntity) killer).sendMessage(Text.of("§dRight click an entity to try out an attack on him."), false);
            LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
            lightning.setPos(this.getPos().x, this.getPos().y, this.getPos().z);
            Utils.setBlockAtPos(world, this.getPos().x, this.getPos().y - 1, this.getPos().z, Blocks.NETHERITE_BLOCK);
            Utils.setBlockAtPos(world, this.getPos().x + 1, this.getPos().y - 1, this.getPos().z, Blocks.GOLD_BLOCK);
            Utils.setBlockAtPos(world, this.getPos().x - 1, this.getPos().y - 1, this.getPos().z, Blocks.GOLD_BLOCK);
            Utils.setBlockAtPos(world, this.getPos().x, this.getPos().y - 1, this.getPos().z + 1, Blocks.GOLD_BLOCK);
            Utils.setBlockAtPos(world, this.getPos().x, this.getPos().y - 1, this.getPos().z - 1, Blocks.GOLD_BLOCK);
            world.spawnEntity(lightning);
            ((PlayerEntity) killer).giveItemStack(Main.HAND_OF_HEROBRINE.getDefaultStack());
        }
        super.onKilledBy(killer);
    }

    public int ticksExisted = 0;
    @Override
    public void tick() {

        if(!isDead()) {
            try {
                ServerPlayerEntity nearest = this.getNearestEntity();
            if (this.attackDelayHelper.hasReached(Main.herobrineAttackDelay)) {
                this.attackDelayHelper.reset();



                if (nearest != null) {
                        this.randomAttack(nearest);
                    }

            }

            if(nearest != null) {
                if (ticksExisted % 60 == 0) {
                    if (nearest.distanceTo(this) > 10) {
                        this.teleport(nearest.getPos().x + (Main.rndm.nextBoolean() ? Main.rndm.nextInt(3) : -Main.rndm.nextInt(3)), nearest.getPos().y, nearest.getPos().z + (Main.rndm.nextBoolean() ? Main.rndm.nextInt(3) : -Main.rndm.nextInt(3)));
                    }
                }
            }

            } catch (Exception e) {
            } //Idk


        }

        ticksExisted++;
        super.tick();
    }

    @Override
    public boolean shouldRenderName() {
        return true;
    }

    @Override
    public boolean handleAttack(Entity attacker) {
        //Protect yourself
	try {
        this.randomAttack(attacker);
	} catch (Exception e) {} //Idk
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

    public void randomAttack(Entity e) {
        AttackManager.attacks.get(Main.rndm.nextInt(AttackManager.attacks.size())).attack(e, this);
    }

    public void attackLivingEntity(LivingEntity e) {
        super.attackLivingEntity(e);
    }




    public ArrayList<ServerPlayerEntity> getPlayers() {
        ArrayList<ServerPlayerEntity> array = new ArrayList<>();
        for(Object o : PlayerStream.all(this.getServer()).toArray()) {
            if(o instanceof PlayerEntity) {
                if(!((PlayerEntity) o).isSpectator()) {
                    array.add((ServerPlayerEntity) o);
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
