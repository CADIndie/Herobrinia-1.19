package me.sirlennox.herobrinia.items.herobrineequip;

import me.sirlennox.herobrinia.Main;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HerobrineChestplate extends ArmorItem {

    public HerobrineChestplate() {
        super(Main.HEROBRINIA_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Settings().group(Main.HEROBRINIA_GROUP));
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(net.minecraft.text.Text.literal("§7This chestplate will protect you every time."));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public boolean isFireproof() {
        return true;
    }

    @Override
    public Text getName() {
        return net.minecraft.text.Text.literal("§cHerobrine Chestplate");
    }
}
