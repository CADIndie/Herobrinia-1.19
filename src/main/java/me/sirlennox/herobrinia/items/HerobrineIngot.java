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
        return net.minecraft.text.Text.literal("§cHerobrine Ingot");
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(net.minecraft.text.Text.literal("§7This is the begin of every herobrine fighter."));
        tooltip.add(Text.literal("§bUsed to craft many items of the Herobrinia Mod."));
        tooltip.add(Text.literal("§dRight click §cHerobrine §dto heal him."));
        super.appendTooltip(stack, world, tooltip, context);
    }


    @Override
    public boolean isFireproof() {
        return true;
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }
}
