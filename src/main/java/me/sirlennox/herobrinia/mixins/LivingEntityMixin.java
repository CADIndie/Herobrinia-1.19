package me.sirlennox.herobrinia.mixins;

import me.sirlennox.herobrinia.Main;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "tryUseTotem", at = @At("RETURN"), cancellable = true)
    private boolean tryUseTotem(DamageSource source, CallbackInfoReturnable<Boolean> info) {
        LivingEntity instance = (LivingEntity) (Object) this;
        if(info.getReturnValue()) return true;
        ItemStack itemStack = null;
        Hand[] var4 = Hand.values();
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Hand hand = var4[var6];
            ItemStack itemStack2 = instance.getStackInHand(hand);
            if (itemStack2.getItem() == Main.HEROBRINE_TOTEM) {
                itemStack = itemStack2.copy();
                itemStack2.decrement(1);
                break;
            }
        }

        if (itemStack != null) {
            applyTotem(instance);
            if(source.isOutOfWorld()) {
                instance.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 20 * 10, 5));
                instance.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 900, 0));
                if(instance.getY() < 0) {
                    instance.teleport(instance.getX(), 0, instance.getZ());
                    instance.setVelocity(0, 100, 0);
                }
            }
        }
        info.setReturnValue(itemStack != null);
        return itemStack != null;
    }

    private static void applyTotem(LivingEntity instance) {
        instance.setHealth(1.0F);
        instance.clearStatusEffects();
        instance.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 3));
        instance.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 3));
        instance.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
        instance.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 20 * 10, 5));
        instance.world.sendEntityStatus(instance, (byte)127);
    }


}
