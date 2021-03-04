package me.sirlennox.herobrinia.items;

import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HerobrineIngot extends Item {

    public HerobrineIngot() {
        super(new Settings().group(Main.HEROBRINIA_GROUP));
    }

    @Override
    public Text getName() {
        return Text.of("§cHerobrine Ingot");
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of("§7This is the begin of every herobrine fighter"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }
}
