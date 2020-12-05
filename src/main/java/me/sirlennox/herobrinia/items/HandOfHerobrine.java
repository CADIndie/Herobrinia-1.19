package me.sirlennox.herobrinia.items;

import me.sirlennox.herobrinia.AttackManager;
import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.TimeHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class HandOfHerobrine extends Item {

    public TimeHelper delay;

    public HandOfHerobrine() {
        super(new Settings().group(Main.HEROBRINIA_GROUP));
        delay = new TimeHelper();
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(delay.hasReached(Main.herobrineAttackDelay)) {
            delay.reset();
            randomAttack(entity, user);
        }else {
            user.sendMessage(Text.of("§cThis item is on cooldown!"), false);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public Text getName() {
        return Text.of("§a§k+++ §r§4§lHand of Herobrine §r§a§k+++");
    }


    @Override
    public Text getName(ItemStack is) {
        return Text.of("§a§k+++ §r§4§lHand of Herobrine §r§a§k+++");
    }

    public void randomAttack(Entity e, Entity player) {
        AttackManager.attacks.get(Main.rndm.nextInt(AttackManager.attacks.size())).attack(e, player);
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
