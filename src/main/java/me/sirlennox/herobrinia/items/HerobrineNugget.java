package me.sirlennox.herobrinia.items;

import me.sirlennox.herobrinia.Main;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class HerobrineNugget extends Item {

    public HerobrineNugget() {
        super(new Item.Settings());
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }

    @Override
    public Text getName() {
        return Text.literal("§cHerobrine Nugget");
    }

    @Override
    public boolean isFireproof() {
        return true;
    }
}
