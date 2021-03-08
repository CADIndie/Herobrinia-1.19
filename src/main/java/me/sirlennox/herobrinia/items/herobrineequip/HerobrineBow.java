package me.sirlennox.herobrinia.items.herobrineequip;

import me.sirlennox.herobrinia.Main;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class HerobrineBow extends BowItem {

    public static final Predicate<ItemStack> BOW_PROJECTILES = (stack) -> stack.getItem() instanceof HerobrineArrow;

    public HerobrineBow() {
        super(new Settings().group(Main.HEROBRINIA_GROUP).maxCount(1).maxDamage(384 * 8));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new net.minecraft.text.LiteralText("§7The strongest bow, the world has ever seen."));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public boolean isFireproof() {
        return true;
    }

    @Override
    public Text getName() {
        return new net.minecraft.text.LiteralText("§cHerobrine Bow");
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return BOW_PROJECTILES;
    }
}
