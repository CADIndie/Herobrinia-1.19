package me.sirlennox.herobrinia.items;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LightningStick extends Item {

    public LightningStick(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        Utils.spawnLightning(context.getWorld(), context.getHitPos());
        return super.useOnBlock(context);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(!user.world.isClient()) Utils.spawnLightning(entity.getEntityWorld(), entity.getPos());
        return super.useOnEntity(stack, user, entity, hand);
    }


    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(attacker instanceof PlayerEntity) this.useOnEntity(stack, (PlayerEntity) attacker, target, Hand.MAIN_HAND);
        return super.postHit(stack, target, attacker);
    }

    @Override
    public Text getName() {
        return net.minecraft.text.Text.literal("§eLightning Stick");
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(net.minecraft.text.Text.literal("§7Summons a lightning if you use it."));
        tooltip.add(Text.literal("§dRight click an entity or block to summon a lightning."));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public boolean isFireproof() {
        return true;
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }
}
