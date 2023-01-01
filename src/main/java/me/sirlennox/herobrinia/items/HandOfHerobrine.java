package me.sirlennox.herobrinia.items;

import me.sirlennox.herobrinia.attack.AttackRegistry;
import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.utils.TimeUtil;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HandOfHerobrine extends Item {

    public HandOfHerobrine() {
        super(new Item.Settings().maxCount(1));
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
       /* if(delay.hasReached(Main.herobrineAttackDelay)) {
            delay.reset();*/
         if(!user.world.isClient()) Utils.randomAttack(entity, user);
        /*}else {
            user.sendMessage(new net.minecraft.text.Text.literal("§cThis item is on cooldown!"), false);
        }*/
        return ActionResult.CONSUME;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(attacker instanceof PlayerEntity) this.useOnEntity(stack, (PlayerEntity) attacker, target, Hand.MAIN_HAND);
        return super.postHit(stack, target, attacker);
    }

    @Override
    public Text getName() {
        return Text.literal("§a§k+++ §r§4§lHand of Herobrine §r§a§k+++");
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(net.minecraft.text.Text.literal("§7The power of herobrine is with you."));
        tooltip.add(Text.literal("§dRight click an entity to start a §cHerobrine Attack §don him."));
        super.appendTooltip(stack, world, tooltip, context);
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
