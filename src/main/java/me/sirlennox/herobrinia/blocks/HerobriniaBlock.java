package me.sirlennox.herobrinia.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.text.MutableText;

public abstract class HerobriniaBlock extends Block {


    public HerobriniaBlock(AbstractBlock.Settings settings) {
        super(settings);
    }


    public abstract MutableText getItemName();

    @Override
    public MutableText getName() {
        return this.getItemName();
    }

    public boolean isFireproof() {
        return true;
    }
}
