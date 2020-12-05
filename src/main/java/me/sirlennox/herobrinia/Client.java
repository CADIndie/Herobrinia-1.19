package me.sirlennox.herobrinia;

import me.sirlennox.herobrinia.entities.EntityHerobrineRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.PlayerEntityRenderer;

@Environment(EnvType.CLIENT)
public class Client implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(Main.HEROBRINE_ENTITY_TYPE, (dispatcher, context) -> {
            return new EntityHerobrineRenderer(dispatcher);
        });
    }
}