package me.sirlennox.herobrinia.blocks;

import me.sirlennox.herobrinia.Main;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class HerobrineBlock extends Block {
    public HerobrineBlock() {
        super(AbstractBlock.Settings.of(Material.METAL, MaterialColor.IRON).requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.METAL));
    }

    @Override
    public MutableText getName() {
        return new LiteralText("§cHerobrine Block");
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        player.giveItemStack(new ItemStack(Main.HEROBRINE_BLOCK, 1));
        super.afterBreak(world, player, pos, state, blockEntity, stack);
    }
}
