package com.mrlocalhost.coloredblocks.item;

import com.mrlocalhost.coloredblocks.ColoredBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ModCreativeTab {
    public static final CreativeModeTab COLORED_BLOCKS_TAB = new CreativeModeTab(ColoredBlocks.MOD_ID) {
        @Override
        @NotNull
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ARTIST_PALETTE.get());
        }
    };
}
