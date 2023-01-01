package me.sirlennox.herobrinia.items.herobrineequip;

import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class HerobrineShears extends ShearsItem {

    public HerobrineShears() {
        super(new Item.Settings().maxDamage(2380));
    }

    @Override
    public boolean isFireproof() {
        return true;
    }


    public static final Random RANDOM = new Random();

    @Override
    public Text getName() {
        return Text.literal("§cHerobrine Shears");
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(entity instanceof SheepEntity && ((SheepEntity) entity).isShearable()) {
            ((SheepEntity) entity).setSheared(true);
            int r = 10 + RANDOM.nextInt(10);
            for(int j = 0; j < r; j++) {
                ItemEntity itemEntity = entity.dropItem(Blocks.WHITE_WOOL, 1);
                if (itemEntity != null) {
                    itemEntity.setVelocity(itemEntity.getVelocity().add((RANDOM.nextFloat() - RANDOM.nextFloat()) * 0.1F, RANDOM.nextFloat() * 0.05F, (RANDOM.nextFloat() - RANDOM.nextFloat()) * 0.1F));
                }
            }
            return ActionResult.CONSUME;
        }
        return super.useOnEntity(stack, user, entity, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("§7More wool!"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
