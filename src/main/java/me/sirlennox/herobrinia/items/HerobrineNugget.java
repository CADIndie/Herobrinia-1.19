package me.sirlennox.herobrinia.items;

import me.sirlennox.herobrinia.Main;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class HerobrineNugget extends Item {

    public HerobrineNugget() {
        super(new Settings().group(Main.HEROBRINIA_GROUP));
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }

    @Override
    public Text getName() {
        return new LiteralText("§cHerobrine Nugget");
    }

    @Override
    public boolean isFireproof() {
        return true;
    }
}
