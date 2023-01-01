package me.sirlennox.herobrinia.items.herobrineequip;

import me.sirlennox.herobrinia.Main;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HerobrineAxe extends AxeItem {

    public HerobrineAxe() {
        super(Main.HEROBRINIA_TOOL_MATERIAL, 30, -2.8F, new Item.Settings());
    }

    @Override
    public boolean isFireproof() {
        return true;
    }


    @Override
    public Text getName() {
        return Text.literal("§cHerobrine Axe");
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("§7More wood, MORE!"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
