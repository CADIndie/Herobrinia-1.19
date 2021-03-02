package me.sirlennox.herobrinia.items;

import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class LightningStick extends Item {
    public LightningStick() {
        super(new Settings().group(Main.HEROBRINIA_GROUP));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        Utils.spawnLightning(context.getWorld(), context.getHitPos());
        return super.useOnBlock(context);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        Utils.spawnLightning(entity.getEntityWorld(), entity.getPos());
        return super.useOnEntity(stack, user, entity, hand);
    }

    @Override
    public Text getName() {
        return Text.of("§eLightning Stick");
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }
}
