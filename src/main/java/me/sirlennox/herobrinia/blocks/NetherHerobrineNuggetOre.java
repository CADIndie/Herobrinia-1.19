package me.sirlennox.herobrinia.blocks;

import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NetherHerobrineNuggetOre extends HerobriniaBlock {

    public NetherHerobrineNuggetOre() {
        super(AbstractBlock.Settings.of(Material.METAL, Material.METAL.getColor()).requiresTool().strength(30.0F, 1000.0F).sounds(BlockSoundGroup.METAL));
    }

    @Override
    public MutableText getItemName() {
        return Text.literal("§cHerobrine Nugget Ore");
    }

 /*   @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        Utils.giveItem(player, new ItemStack(Main.HEROBRINE_NUGGET, 1));
        super.afterBreak(world, player, pos, state, blockEntity, stack);
    }*/
}
