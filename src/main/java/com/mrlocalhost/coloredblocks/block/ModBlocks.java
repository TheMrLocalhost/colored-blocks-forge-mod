package com.mrlocalhost.coloredblocks.block;

import com.mrlocalhost.coloredblocks.ColoredBlocks;
import com.mrlocalhost.coloredblocks.item.ModCreativeTab;
import com.mrlocalhost.coloredblocks.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ColoredBlocks.MOD_ID);

    public static final RegistryObject<Block> COLORED_STONE_BRICK = registerBlock("colored_stone_bricks",
        () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
            .strength(1.5f)
            .explosionResistance(6.0f)
            .requiresCorrectToolForDrops()),
        ModCreativeTab.COLORED_BLOCKS_TAB);

    public static final RegistryObject<Block> COLORED_WOOD_PLANK = registerBlock("colored_wood_planks",
        () -> new Block(BlockBehaviour.Properties.of(Material.WOOD)
            .strength(1.5f)
            .explosionResistance(2.0f)),
        ModCreativeTab.COLORED_BLOCKS_TAB);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
