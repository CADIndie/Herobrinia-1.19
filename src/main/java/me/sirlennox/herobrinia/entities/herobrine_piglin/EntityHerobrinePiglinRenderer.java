package me.sirlennox.herobrinia.entities.herobrine_piglin;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.PiglinEntityRenderer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;

public class EntityHerobrinePiglinRenderer extends PiglinEntityRenderer {

    public EntityHerobrinePiglinRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, false);
    }

    @Override
    public Identifier getTexture(MobEntity mobEntity) {
        return new Identifier("herobrinia", "textures/entity/herobrine_piglin/herobrine_piglin.png");
    }
}
