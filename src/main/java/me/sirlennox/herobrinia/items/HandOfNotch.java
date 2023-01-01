package me.sirlennox.herobrinia.items;

import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.utils.TimeUtil;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HandOfNotch extends Item {


    public HandOfNotch() {
        super(new Item.Settings().maxCount(1));
    }

    @Override
    public Text getName() {
        return Text.literal("§a§k+++ §r§6§lHand of Notch §r§a§k+++");
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(net.minecraft.text.Text.literal("§7The power of notch is with you."));
        tooltip.add(Text.literal("§dRight click a block or an entity to teleport."));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().isClient()) return ActionResult.CONSUME;
        context.getPlayer().teleport(context.getBlockPos().getX(), context.getBlockPos().getY() + 1, context.getBlockPos().getZ());
        return ActionResult.CONSUME;
    }


    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(user.getEntityWorld().isClient()) return ActionResult.CONSUME;
        user.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 10, 1);
        user.teleport(entity.getX(), entity.getY(), entity.getZ());
        return ActionResult.CONSUME;
    }

    @Override
    public Text getName(ItemStack is) {
        return this.getName();
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isFireproof() {
        return true;
    }


}
