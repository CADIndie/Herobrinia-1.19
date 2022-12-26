package me.sirlennox.herobrinia;

import me.sirlennox.herobrinia.entities.herobrinepiglin.EntityHerobrinePiglinRenderer;
import me.sirlennox.herobrinia.entities.herobrine.EntityHerobrineRenderer;
import me.sirlennox.herobrinia.mixins.ModelPredicateProviderRegistryMixin;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProvider;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class Client implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(Main.HEROBRINE_ENTITY_TYPE, (dispatcher, context) -> new EntityHerobrineRenderer(dispatcher));
        EntityRendererRegistry.register(Main.HEROBRINE_PIGLIN_ENTITY_TYPE, (dispatcher, context) -> new EntityHerobrinePiglinRenderer(dispatcher));
        //Register Model Predicates
        registerModelPredicate(Main.HEROBRINE_BOW, new Identifier("pull"), (itemStack, clientWorld, livingEntity, pull) -> livingEntity == null ? 0.0F : livingEntity.getActiveItem() != itemStack ? 0.0F : (float)(itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20.0F);
        registerModelPredicate(Main.HEROBRINE_BOW, new Identifier("pulling"), (itemStack, clientWorld, livingEntity, pulling) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F);
    }

    public static void registerModelPredicate(Item item, Identifier id, ModelPredicateProvider provider) {
        ModelPredicateProviderRegistryMixin.register(item, id, provider);
    }
}