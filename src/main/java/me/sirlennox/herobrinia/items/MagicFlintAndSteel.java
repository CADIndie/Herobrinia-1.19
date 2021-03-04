package me.sirlennox.herobrinia.items;

import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.blocks.HerobrineBlock;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MagicFlintAndSteel extends FlintAndSteelItem {

    //public SpawnEggItem spawnEggItem;

    public MagicFlintAndSteel() {
        super(new Settings().group(Main.HEROBRINIA_GROUP).maxCount(1));
       // this.spawnEggItem = new SpawnEggItem(Main.HEROBRINE_ENTITY_TYPE, 0xFFFFFF, 0x000000,  new Item.Settings().group(Main.HEROBRINIA_GROUP));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().isClient()) return ActionResult.PASS;
        BlockState bs = context.getWorld().getBlockState(context.getBlockPos());
        if(bs != null && bs.getBlock() != null && bs.getBlock() instanceof HerobrineBlock) {
            Utils.spawnLightning(context.getWorld(), context.getHitPos());
           // Utils.setBlockAtPos(context.getWorld(), context.getHitPos(), Blocks.AIR);
         //   spawnEggItem.useOnBlock(context);
            Main.HEROBRINE_ENTITY_TYPE.spawnFromItemStack((ServerWorld) context.getWorld(), null, null, context.getBlockPos(), SpawnReason.EVENT, true, false);
            context.getStack().increment(1);
        }
        return super.useOnBlock(context);
    }

    @Override
    public Text getName() {
        return Text.of("§dMagic Flint and Steel");
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of("§7You have the power to begin the fight"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }
}
