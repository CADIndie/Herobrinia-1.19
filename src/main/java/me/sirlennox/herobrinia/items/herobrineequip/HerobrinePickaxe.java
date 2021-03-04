package me.sirlennox.herobrinia.items.herobrineequip;

import me.sirlennox.herobrinia.Main;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HerobrinePickaxe extends PickaxeItem {
    public HerobrinePickaxe() {
        super(Main.HEROBRINIA_TOOL_MATERIAL, 5, -2.5F, new Settings().group(Main.HEROBRINIA_GROUP));
    }

    @Override
    public Text getName() {
        return Text.of("§cHerobrine Pickaxe");
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of("§7This pickaxe mines through every block."));

        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }
}
