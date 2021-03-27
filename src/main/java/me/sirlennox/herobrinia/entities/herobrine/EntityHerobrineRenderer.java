package me.sirlennox.herobrinia.entities.herobrine;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class EntityHerobrineRenderer extends MobEntityRenderer<EntityHerobrine, EntityHerobrineModel> {

    public HashMap<Float, Identifier> states = new HashMap<>();

    public EntityHerobrineRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new EntityHerobrineModel(0.5f), 0.5f);
        this.addFeature(new WitherArmor(this));
        registerState(80f, 1);
        registerState(60f, 2);
        registerState(50f, 3);
        registerState(30f, 4);
        registerState(20f, 5);
    }

    public void registerState(float healthPercent, int id) {
        this.states.put(healthPercent, new Identifier("herobrinia", "textures/entity/herobrine/herobrine_damaged_" + id + ".png"));
    }

    @Override
    public Identifier getTexture(EntityHerobrine entity) {
        AtomicReference<Identifier> id = new AtomicReference<>(new Identifier("herobrinia", "textures/entity/herobrine/herobrine.png"));
        float health = entity.getHealth();
        AtomicReference<Float> last = new AtomicReference<>();
        last.set(null);
        if(health < entity.getMaxHealth()) {
            this.states.forEach((k, v) -> {
                float h = (entity.getMaxHealth() / 100) * k;
                if (health <= h && (last.get() == null ||h < last.get())) {
                    id.set(v);
                    last.set(h);
                }
            });
        }
        return id.get();
    }
}
