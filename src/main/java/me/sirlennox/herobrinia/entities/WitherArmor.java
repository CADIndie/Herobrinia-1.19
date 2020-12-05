package me.sirlennox.herobrinia.entities;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.feature.EnergySwirlOverlayFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.WitherEntityModel;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class WitherArmor extends EnergySwirlOverlayFeatureRenderer<EntityHerobrine,EntityHerobrineModel> {
    private static final Identifier SKIN = new Identifier("textures/entity/wither/wither_armor.png");
    private final EntityHerobrineModel model = new EntityHerobrineModel(0.5F);

    public WitherArmor(FeatureRendererContext<EntityHerobrine, EntityHerobrineModel> featureRendererContext) {
        super(featureRendererContext);
    }

    protected float getEnergySwirlX(float partialAge) {
        return MathHelper.cos(partialAge * 0.02F) * 3.0F;
    }

    protected Identifier getEnergySwirlTexture() {
        return SKIN;
    }

    protected EntityModel<EntityHerobrine> getEnergySwirlModel() {
        return this.model;
    }
}

