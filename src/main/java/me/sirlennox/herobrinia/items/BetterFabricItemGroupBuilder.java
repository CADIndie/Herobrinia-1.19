package me.sirlennox.herobrinia.items;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.impl.item.group.ItemGroupExtensions;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BetterFabricItemGroupBuilder {
    private Identifier identifier;
    private Supplier<ItemStack> stackSupplier = () -> {
        return ItemStack.EMPTY;
    };
    private Consumer<List<ItemStack>> stacksForDisplay;
    private String name;

    private BetterFabricItemGroupBuilder(Identifier identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public static BetterFabricItemGroupBuilder create(Identifier identifier, String name) {
        return new BetterFabricItemGroupBuilder(identifier, name);
    }

    public BetterFabricItemGroupBuilder icon(Supplier<ItemStack> stackSupplier) {
        this.stackSupplier = stackSupplier;
        return this;
    }


    public BetterFabricItemGroupBuilder appendItems(Consumer<List<ItemStack>> stacksForDisplay) {
        this.stacksForDisplay = stacksForDisplay;
        return this;
    }

    public ItemGroup build() {
        ((ItemGroupExtensions)ItemGroup.BUILDING_BLOCKS).fabric_expandArray();
        return new ItemGroup(ItemGroup.GROUPS.length - 1, this.name) {
            public ItemStack createIcon() {
                return BetterFabricItemGroupBuilder.this.stackSupplier.get();
            }

            @Override
            public String getName() {
                return BetterFabricItemGroupBuilder.this.name;
            }

            @Override
            public Text getTranslationKey() {
                return Text.of(this.getName());
            }

            public void appendStacks(DefaultedList<ItemStack> stacks) {
                if (BetterFabricItemGroupBuilder.this.stacksForDisplay != null) {
                    BetterFabricItemGroupBuilder.this.stacksForDisplay.accept(stacks);
                } else {
                    super.appendStacks(stacks);
                }
            }
        };
    }
}
