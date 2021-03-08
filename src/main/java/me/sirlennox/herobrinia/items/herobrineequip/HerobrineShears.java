package me.sirlennox.herobrinia.items.herobrineequip;

import me.sirlennox.herobrinia.Main;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class HerobrineShears extends ShearsItem {

    public HerobrineShears() {
        super(new Settings().group(Main.HEROBRINIA_GROUP).maxDamage(2380));
    }

    @Override
    public boolean isFireproof() {
        return true;
    }


    @Override
    public Text getName() {
        return new LiteralText("§cHerobrine Shears");
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }
}
