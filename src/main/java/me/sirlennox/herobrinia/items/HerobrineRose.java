package me.sirlennox.herobrinia.items;

import me.sirlennox.herobrinia.Main;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HerobrineRose extends Item {
    public HerobrineRose() {
        super(new Item.Settings());
    }

    @Override
    public boolean isFireproof() {
        return true;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }


    @Override
    public Text getName() {
        return net.minecraft.text.Text.literal("§cHerobrine Rose");
    }


    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(net.minecraft.text.Text.literal("§7Be a friend of §cHerobrine"));
        tooltip.add(Text.literal("§dRight click §cHerobrine §dto tame him."));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }
}
