package me.sirlennox.herobrinia.items.herobrineequip;

import me.sirlennox.herobrinia.Main;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HerobrineShovel extends ShovelItem {
    public HerobrineShovel() {
        super(Main.HEROBRINIA_TOOL_MATERIAL, 10, -2.8F, new Settings().group(Main.HEROBRINIA_GROUP));
    }

    @Override
    public boolean isFireproof() {
        return true;
    }

    @Override
    public Text getName() {
        return new LiteralText("§cHerobrine Shovel");
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new LiteralText("§7Dig faster than the minecraft world refreshes!"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
