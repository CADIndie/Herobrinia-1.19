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
    private void tryUseTotem(DamageSource source, CallbackInfoReturnable<Boolean> info) {
        if(info.getReturnValue()) return;
        LivingEntity instance = (LivingEntity) (Object) this;
        ItemStack itemStack = findTotem(instance);
        if (itemStack != null) {
            applyTotem(instance);
            if(source.isOutOfWorld()) {
                instance.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 20 * 10, 5));
                instance.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 40 * 20, 0));
                instance.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 10 * 20, 20));
            }
        }
        info.setReturnValue(itemStack != null);
    }

    private ItemStack findTotem(LivingEntity instance) {
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
        return itemStack;
    }

    private static void applyTotem(LivingEntity instance) {
        instance.setHealth(instance.getMaxHealth());
        instance.clearStatusEffects();
        instance.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 4));
        instance.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 3));
        instance.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
        instance.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 25 * 10, 5));
        instance.addStatusEffect(new StatusEffectInstance(Main.INVULNERABLE_EFFECT, 15 * 20, 0));
        instance.world.sendEntityStatus(instance, (byte)127);
    }

    @Inject(method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", at = @At("RETURN"), cancellable = true)
    public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if(!cir.getReturnValue() || !source.isOutOfWorld()) return;
        LivingEntity instance = (LivingEntity) (Object) this;
        if(instance.hasStatusEffect(Main.INVULNERABLE_EFFECT)) return;

        ItemStack itemStack = findTotem(instance);
        if (itemStack != null) {
            applyTotem(instance);
            instance.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 20 * 10, 5));
            instance.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 40 * 20, 0));
            instance.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 10 * 20, 20));
        }
    }


}
