package me.sirlennox.herobrinia.items;

import me.sirlennox.herobrinia.Main;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class HerobrineIngot extends Item {

    public HerobrineIngot() {
        super(new Settings().group(Main.HEROBRINIA_GROUP));
    }

    @Override
    public Text getName() {
        return Text.of("§cHerobrine Ingot");
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }
}
