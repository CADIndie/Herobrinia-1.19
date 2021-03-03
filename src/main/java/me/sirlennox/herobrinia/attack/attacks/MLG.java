package me.sirlennox.herobrinia.attack.attacks;

import me.sirlennox.herobrinia.attack.Attack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

public class MLG extends Attack {

    public MLG() {
        super("MLG", "Do you like MLGs?");
    }

    @Override
    public void attack(LivingEntity target, LivingEntity herobrine) {
        target.setVelocity(0, 5, 0);
        if(target instanceof PlayerEntity) {
            Set<Item> items = new java.util.HashSet<>(Collections.emptySet());
            items.add(Items.WATER_BUCKET);
            if(!((PlayerEntity) target).inventory.containsAny(items)) {
                ((PlayerEntity) target).giveItemStack(new ItemStack(Items.WATER_BUCKET, 1));
            }
        }
    }
}
