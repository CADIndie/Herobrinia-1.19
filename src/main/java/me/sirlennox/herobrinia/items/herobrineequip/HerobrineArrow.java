package me.sirlennox.herobrinia.items.herobrineequip;

import me.sirlennox.herobrinia.Main;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class HerobrineArrow extends ArrowItem {

    public HerobrineArrow() {
        super(new Settings().group(Main.HEROBRINIA_GROUP));
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        ArrowEntity arrowEntity = new ArrowEntity(world, shooter);
        arrowEntity.initFromStack(stack);
        arrowEntity.setCritical(true);
        arrowEntity.setDamage(20);
        arrowEntity.setPunch(3);
        arrowEntity.setFireTicks(1000);
        return arrowEntity;
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }

    @Override
    public Text getName() {
        return Text.of("§cHerobrine Arrow");
    }
}
