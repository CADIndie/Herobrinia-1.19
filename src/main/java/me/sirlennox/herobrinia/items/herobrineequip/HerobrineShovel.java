package me.sirlennox.herobrinia.items.herobrineequip;

import me.sirlennox.herobrinia.Main;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class HerobrineShovel extends ShovelItem {
    public HerobrineShovel() {
        super(Main.HEROBRINIA_TOOL_MATERIAL, 10, -2.8F, new Settings().group(Main.HEROBRINIA_GROUP));
    }

    @Override
    public boolean isFireproof() {
        return true;
    }

    @Override
    public Text getName() {
        return new LiteralText("§cHerobrine Shovel");
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }
}
