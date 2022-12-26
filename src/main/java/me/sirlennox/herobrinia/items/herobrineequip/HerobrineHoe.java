package me.sirlennox.herobrinia.items.herobrineequip;

import me.sirlennox.herobrinia.Main;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HerobrineHoe extends HoeItem {
    public HerobrineHoe() {
        super(Main.HEROBRINIA_TOOL_MATERIAL, 12, -3.0F, new Settings().group(Main.HEROBRINIA_GROUP));
    }


    @Override
    public Text getName() {
        return Text.literal("§cHerobrine Hoe");
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("§7The farmers tool!"));
        super.appendTooltip(stack, world, tooltip, context);
    }

}
