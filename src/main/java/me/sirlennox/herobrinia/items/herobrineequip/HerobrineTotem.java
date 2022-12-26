package me.sirlennox.herobrinia.items.herobrineequip;

import me.sirlennox.herobrinia.Main;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HerobrineTotem extends Item {

    public HerobrineTotem() {
        super(new Settings().group(Main.HEROBRINIA_GROUP));
    }

    @Override
    public Text getName() {
        return Text.literal("§cHerobrine Totem");
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("§7Fell into the void? No problem."));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

}
