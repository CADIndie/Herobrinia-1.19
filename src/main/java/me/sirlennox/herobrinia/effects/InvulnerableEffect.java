package me.sirlennox.herobrinia.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.awt.*;

public class InvulnerableEffect extends StatusEffect {

    public InvulnerableEffect() {
        super(StatusEffectType.BENEFICIAL, Color.YELLOW.getRGB());
    }

    @Override
    public Text getName() {
        return new LiteralText("Invulnerable");
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
