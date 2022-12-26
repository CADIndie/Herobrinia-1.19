package me.sirlennox.herobrinia.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.text.Text;

import java.awt.*;

public class InvulnerableEffect extends StatusEffect {

    public InvulnerableEffect() {
        super(StatusEffectCategory.BENEFICIAL, Color.YELLOW.getRGB());
    }

    @Override
    public Text getName() {
        return Text.literal("Invulnerable");
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
        entity.setInvulnerable(true);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);
        entity.setInvulnerable(false);
    }

}
