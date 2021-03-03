package me.sirlennox.herobrinia.items.herobrineequip;

import me.sirlennox.herobrinia.Main;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.text.Text;

public class HerobrineSword extends SwordItem {

    public HerobrineSword() {
        super(Main.HEROBRINIA_TOOL_MATERIAL, 20, -1.5F, new Settings().group(Main.HEROBRINIA_GROUP));
    }


    @Override
    public Text getName() {
        return Text.of("§cHerobrine Sword");
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }
}
