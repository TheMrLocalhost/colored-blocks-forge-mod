package com.mrlocalhost.coloredblocks.utils;

import com.mrlocalhost.coloredblocks.block.ModBlocks;
import com.mrlocalhost.coloredblocks.block.custom.ColoredBlock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColoredBlocksConstants {

    public static final List<ColoredBlock> COLORED_BLOCKS = new ArrayList<>() {{
        add(ModBlocks.COLORED_STONE_BRICK.get());
        add(ModBlocks.COLORED_WOOD_PLANK.get());
    }};

    public static final Map<String, Integer> COLOR_REFERENCES = new HashMap<>() {{
        put("red", 0xAA0D0D);
        put("orange", 0xFF7519);
        put("yellow", 0xFFCC2F);
        put("lime", 0x80FF00);
        put("green", 0x547F00);
        put("cyan", 0x0D8282);
        put("light_blue", 0x89C1FF);
        put("blue", 0x2639AF);
        put("purple", 0x7D00B7);
        put("magenta", 0xBF3BB8);
        put("pink", 0xFF99CA);
        put("brown", 0x7C5235);
        put("white", 0xFFFFFF);
        put("light_gray", 0xA8ADAD);
        put("gray", 0x4B5050);
        put("black", 0x161616);
    }};

}
