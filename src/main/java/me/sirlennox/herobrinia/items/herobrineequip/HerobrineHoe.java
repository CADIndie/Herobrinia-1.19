package me.sirlennox.herobrinia.items.herobrineequip;

import me.sirlennox.herobrinia.Main;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class HerobrineHoe extends HoeItem {
    public HerobrineHoe() {
        super(Main.HEROBRINIA_TOOL_MATERIAL, 12, -3.0F, new Settings().group(Main.HEROBRINIA_GROUP));
    }


    @Override
    public Text getName() {
        return new LiteralText("§cHerobrine Hoe");
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }
}
