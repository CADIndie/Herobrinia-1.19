package me.sirlennox.herobrinia.items;

import me.sirlennox.herobrinia.attack.AttackRegistry;
import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.utils.TimeUtil;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class HandOfHerobrine extends Item {

    public TimeUtil delay;

    public HandOfHerobrine() {
        super(new Settings().group(Main.HEROBRINIA_GROUP));
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
    public Text getName() {
        return Text.of("§a§k+++ §r§4§lHand of Herobrine §r§a§k+++");
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
