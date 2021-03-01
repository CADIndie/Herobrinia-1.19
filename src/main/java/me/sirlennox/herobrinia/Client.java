package me.sirlennox.herobrinia;

import me.sirlennox.herobrinia.entities.herobrine.EntityHerobrineRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class Client implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(Main.HEROBRINE_ENTITY_TYPE, (dispatcher, context) -> new EntityHerobrineRenderer(dispatcher));
    }
}