package me.sirlennox.herobrinia.items.herobrineequip;

import me.sirlennox.herobrinia.Main;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class HerobrineLeggings extends ArmorItem {

    public HerobrineLeggings() {
        super(Main.HEROBRINIA_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Settings().group(Main.HEROBRINIA_GROUP));
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }

    @Override
    public Text getName() {
        return Text.of("§cHerobrine Leggings");
    }
}
