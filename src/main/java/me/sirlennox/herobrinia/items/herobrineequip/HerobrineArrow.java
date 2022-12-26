package me.sirlennox.herobrinia.items.herobrineequip;

import me.sirlennox.herobrinia.Main;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HerobrineArrow extends ArrowItem {

    public HerobrineArrow() {
        super(new Settings().group(Main.HEROBRINIA_GROUP));
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        ArrowEntity arrowEntity = new ArrowEntity(world, shooter);
        arrowEntity.initFromStack(stack);
        arrowEntity.setCritical(true);
        arrowEntity.setDamage(arrowEntity.getDamage() + 20);
        arrowEntity.setPunch(3);
        arrowEntity.setFireTicks(1000);
        arrowEntity.setPierceLevel((byte) 3);
        arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
        return arrowEntity;
    }

    @Override
    public boolean isFireproof() {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(net.minecraft.text.Text.literal("§7The munition for the Herobrine Bow"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }

    @Override
    public Text getName() {
        return net.minecraft.text.Text.literal("§cHerobrine Arrow");
    }
}
