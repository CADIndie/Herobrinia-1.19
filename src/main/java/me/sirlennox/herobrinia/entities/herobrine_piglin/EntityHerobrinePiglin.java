package me.sirlennox.herobrinia.entities.herobrine_piglin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class EntityHerobrinePiglin extends PiglinEntity {

    public EntityHerobrinePiglin(EntityType<? extends AbstractPiglinEntity> entityType, World world) {
        super(entityType, world);
    }


    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 10).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23000000417232513D * 1.5).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0D).add(EntityAttributes.GENERIC_MAX_HEALTH, 20).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK);
    }

    @Override
    protected boolean isImmuneToZombification() {
        return true;
    }

    @Override
    public Text getName() {
        return getDefaultName();
    }

    @Override
    protected Text getDefaultName() {
        return new LiteralText(getEntityName());
    }

    @Override
    public String getEntityName() {
        return "Herobrine Piglin";
    }
}
