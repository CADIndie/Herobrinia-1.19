package me.sirlennox.herobrinia.items.herobrineequip;

import me.sirlennox.herobrinia.Main;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class HerobrineShears extends ShearsItem {

    public HerobrineShears() {
        super(new Settings().group(Main.HEROBRINIA_GROUP).maxDamage(2380));
    }

    @Override
    public boolean isFireproof() {
        return true;
    }


    public static final Random RANDOM = new Random();

    @Override
    public Text getName() {
        return new LiteralText("§cHerobrine Shears");
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(entity instanceof SheepEntity && ((SheepEntity) entity).isShearable()) {
            ((SheepEntity) entity).setSheared(true);
            user.giveItemStack(new ItemStack(Blocks.WHITE_WOOL, 5 + RANDOM.nextInt(10)));
            return ActionResult.CONSUME;
        }
        return super.useOnEntity(stack, user, entity, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new LiteralText("§7More wool!"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
