package me.sirlennox.herobrinia.items;

import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.utils.Utils;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class HerobrineApple extends SpawnEggItem {
    public HerobrineApple() {
        super(Main.HEROBRINE_ENTITY_TYPE, 0xFFFFFF, 0x000000,  new Item.Settings().group(Main.HEROBRINIA_GROUP));
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isFireproof() {
        return false;
    }

    @Override
    public boolean isFood() {
        return true;
    }

    @Override
    public Text getName() {
        return Text.of("§cHerobrine Apple");
    }

    @Override
    public Text getName(ItemStack is) {
        return Text.of("§cHerobrine Apple");
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        player.playSound(SoundEvents.ENTITY_ENDER_DRAGON_DEATH, SoundCategory.MUSIC, 10, 1);
        player.sendMessage(Text.of("§dRight click on a block to spawn Herobrine there."), false);
        super.onCraft(stack, world, player);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        Utils.spawnLightning(context.getWorld(), context.getHitPos());
        return super.useOnBlock(context);
    }
}
