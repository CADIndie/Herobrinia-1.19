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

    public TimeUtil delay;

    public HandOfHerobrine() {
        super(new Settings().group(Main.HEROBRINIA_GROUP).maxCount(1));
        delay = new TimeUtil();
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
       /* if(delay.hasReached(Main.herobrineAttackDelay)) {
            delay.reset();*/
         if(!user.world.isClient()) Utils.randomAttack(entity, user);
        /*}else {
            user.sendMessage(Text.of("§cThis item is on cooldown!"), false);
        }*/
        return ActionResult.SUCCESS;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(attacker instanceof PlayerEntity) this.useOnEntity(stack, (PlayerEntity) attacker, target, Hand.MAIN_HAND);
        return super.postHit(stack, target, attacker);
    }

    @Override
    public Text getName() {
        return Text.of("§a§k+++ §r§4§lHand of Herobrine §r§a§k+++");
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of("§7The power of herobrine is with you"));
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
