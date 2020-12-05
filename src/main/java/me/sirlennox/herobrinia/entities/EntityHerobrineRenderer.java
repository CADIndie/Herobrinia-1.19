package me.sirlennox.herobrinia.entities;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.WitherArmorFeatureRenderer;
import net.minecraft.util.Identifier;
public class EntityHerobrineRenderer extends MobEntityRenderer<EntityHerobrine, EntityHerobrineModel> {
    public EntityHerobrineRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new EntityHerobrineModel(0.5f), 0.5f);
        this.addFeature(new WitherArmor(this));
    }

    @Override
    public Identifier getTexture(EntityHerobrine entity) {
        return new Identifier("herobrinia", "textures/entity/herobrine/herobrine.png");
    }
}
