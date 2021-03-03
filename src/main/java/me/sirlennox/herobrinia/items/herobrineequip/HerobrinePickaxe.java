package me.sirlennox.herobrinia.items.herobrineequip;

import me.sirlennox.herobrinia.Main;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.text.Text;

public class HerobrinePickaxe extends PickaxeItem {
    public HerobrinePickaxe() {
        super(Main.HEROBRINIA_TOOL_MATERIAL, 5, -2.5F, new Settings().group(Main.HEROBRINIA_GROUP));
    }

    @Override
    public Text getName() {
        return Text.of("§cHerobrine Pickaxe");
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }
}
