package me.sirlennox.herobrinia.items;

import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.blocks.HerobrineBlock;
import me.sirlennox.herobrinia.utils.Utils;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

public class MagicFlintAndSteel extends FlintAndSteelItem {

    public SpawnEggItem spawnEggItem;

    public MagicFlintAndSteel() {
        super(new Settings().group(Main.HEROBRINIA_GROUP));
        this.spawnEggItem = new SpawnEggItem(Main.HEROBRINE_ENTITY_TYPE, 0xFFFFFF, 0x000000,  new Item.Settings().group(Main.HEROBRINIA_GROUP));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState bs = context.getWorld().getBlockState(context.getBlockPos());
        if(bs != null && bs.getBlock() != null && bs.getBlock() instanceof HerobrineBlock) {
            Utils.spawnLightning(context.getWorld(), context.getHitPos());
           // Utils.setBlockAtPos(context.getWorld(), context.getHitPos(), Blocks.AIR);
            spawnEggItem.useOnBlock(context);
        }
        return super.useOnBlock(context);
    }

    @Override
    public Text getName() {
        return Text.of("§dMagic Flint and Steel");
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.getName();
    }
}
