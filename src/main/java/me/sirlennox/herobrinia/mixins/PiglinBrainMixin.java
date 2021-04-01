package me.sirlennox.herobrinia.mixins;

import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.entities.herobrine_piglin.EntityHerobrinePiglin;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collections;
import java.util.List;

@Mixin(PiglinBrain.class)
public class PiglinBrainMixin {


    @Inject(method = "getBarteredItem(Lnet/minecraft/entity/mob/PiglinEntity;)Ljava/util/List;", at = @At("RETURN"), cancellable = true)
    private static List<ItemStack> getBarteredItem(PiglinEntity piglin, CallbackInfoReturnable<List<ItemStack>> info) {
        LootTable lootTable = piglin.world.getServer().getLootManager().getTable(LootTables.PIGLIN_BARTERING_GAMEPLAY);
        List<ItemStack> list = lootTable.generateLoot((new LootContext.Builder((ServerWorld)piglin.world)).parameter(LootContextParameters.THIS_ENTITY, piglin).random(piglin.world.random).build(LootContextTypes.BARTER));
        if(piglin instanceof EntityHerobrinePiglin) {
            Item i = null;
            int count = 1;
            int r = Utils.RANDOM.nextInt(100);
            if(r <= 1) {
                i = Main.HEROBRINE_BLOCK.asItem();
            }else if(r <= 2) {
                i = Main.HEROBRINE_INGOT;
            }else if(r <= 10) {
                i = Main.HEROBRINE_NUGGET;
                if(r <= 5) count = 3;
            }
            if(i != null) list = Collections.singletonList(new ItemStack(i, count));
        }
        info.setReturnValue(list);
        return list;
    }
}
