package me.sirlennox.herobrinia.items;

import me.sirlennox.herobrinia.Main;
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
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MultiHandOfHerobrineAndNotch extends Item {

    public MultiHandOfHerobrineAndNotch() {
        super(new Item.Settings().maxCount(1));
    }

    @Override
    public Text getName() {
        return Text.literal("§a§k+++ §r§b§lMulti §4§lHand of Herobrine and §6§lNotch §r§a§k+++");
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(net.minecraft.text.Text.literal("§7The power of §cHerobrine §7and §6Notch §7is with you."));
        tooltip.add(net.minecraft.text.Text.literal("§dLeft click for the power of §4Herobrine."));
        tooltip.add(net.minecraft.text.Text.literal("§dRight click for the power of §6Notch."));
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
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(!attacker.world.isClient()) Utils.randomAttack(target, attacker);
        return super.postHit(stack, target, attacker);
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
