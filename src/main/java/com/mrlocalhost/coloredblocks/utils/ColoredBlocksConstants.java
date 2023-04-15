package com.mrlocalhost.coloredblocks.utils;

import com.mrlocalhost.coloredblocks.block.ModBlocks;
import com.mrlocalhost.coloredblocks.block.custom.ColoredBlock;

import java.util.ArrayList;
import java.util.List;

public class ColoredBlocksConstants {

    public static final List<ColoredBlock> COLORED_BLOCKS = new ArrayList<>() {{
        add(ModBlocks.COLORED_STONE_BRICK.get());
        add(ModBlocks.COLORED_WOOD_PLANK.get());
    }};

}
