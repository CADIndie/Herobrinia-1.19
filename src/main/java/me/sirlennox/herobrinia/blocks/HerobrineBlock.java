package me.sirlennox.herobrinia.blocks;

import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.utils.Utils;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class HerobrineBlock extends HerobriniaBlock {

    public HerobrineBlock() {
        super(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.METAL));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(net.minecraft.text.Text.literal("§7This block is the begin of herobrine"));
        tooltip.add(Text.literal("§dRight click with a Magic Flint and Steel on it to spawn Herobrine."));
        super.appendTooltip(stack, world, tooltip, options);
    }
/*
    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        Utils.giveItem(player, new ItemStack(Main.HEROBRINE_BLOCK, 1));
        super.afterBreak(world, player, pos, state, blockEntity, stack);
    }*/

    @Override
    public MutableText getItemName() {
        return Text.literal("§cHerobrine Block");
    }
}
