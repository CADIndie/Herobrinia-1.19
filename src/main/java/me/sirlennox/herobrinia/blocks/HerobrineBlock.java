package me.sirlennox.herobrinia.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;


public class HerobrineBlock extends Block {
    public HerobrineBlock() {
        super(AbstractBlock.Settings.of(Material.METAL, MaterialColor.IRON).requiresTool().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.METAL));
    }

    @Override
    public MutableText getName() {
        return new LiteralText("Herobrine Block");
    }
}
