package me.sirlennox.herobrinia.items.herobrineequip;

import me.sirlennox.herobrinia.Main;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.function.Predicate;

public class HerobrineBow extends BowItem {

    public static final Predicate<ItemStack> BOW_PROJECTILES = (stack) -> stack.getItem() instanceof HerobrineArrow;

    public HerobrineBow() {
        super(new Settings().group(Main.HEROBRINIA_GROUP).maxCount(1).maxDamage(384 * 8));
    }

    @Override
    public Text getName() {
        return Text.of("§cHerobrine Bow");
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
