package me.sirlennox.herobrinia.mixins;

import me.sirlennox.herobrinia.Main;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Shadow
    private MinecraftClient client;

    @Inject(method = "onEntityStatus", at = @At("RETURN"), cancellable = true)
    public void onEntityStatus(EntityStatusS2CPacket packet, CallbackInfo ci) {
        ClientPlayNetworkHandler instance = (ClientPlayNetworkHandler) (Object) this;
        Entity entity = packet.getEntity(instance.getWorld());
        if(packet.getStatus() == (byte) 127) {
            this.client.particleManager.addEmitter(entity, ParticleTypes.TOTEM_OF_UNDYING, 30);
            instance.getWorld().playSound(entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ITEM_TOTEM_USE, entity.getSoundCategory(), 1.0F, 1.0F, false);
            if (entity == this.client.player) {
                this.client.gameRenderer.showFloatingItem(getActiveHerobrineTotem(this.client.player));
            }
        }
    }

    private static ItemStack getActiveHerobrineTotem(PlayerEntity player) {
        Hand[] var1 = Hand.values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Hand hand = var1[var3];
            ItemStack itemStack = player.getStackInHand(hand);
            if (itemStack.getItem() == Main.HEROBRINE_TOTEM) {
                return itemStack;
            }
        }

        return new ItemStack(Main.HEROBRINE_TOTEM);
    }
}
