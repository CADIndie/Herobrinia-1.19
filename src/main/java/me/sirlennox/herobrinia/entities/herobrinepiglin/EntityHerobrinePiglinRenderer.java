package me.sirlennox.herobrinia.entities.herobrinepiglin;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PiglinEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;

public class EntityHerobrinePiglinRenderer extends PiglinEntityRenderer {

    EntityModelLayer mainLayer;
    EntityModelLayer innerArmorLayer;
    EntityModelLayer outerArmorLayer;
    EntityRendererFactory.Context ctx;

    public EntityHerobrinePiglinRenderer(EntityRenderDispatcher dispatcher) {
        super(ctx, mainLayer, innerArmorLayer, outerArmorLayer, false);
    }

    @Override
    public Identifier getTexture(MobEntity mobEntity) {
        return new Identifier("herobrinia", "textures/entity/herobrine_piglin/herobrine_piglin.png");
    }
}
