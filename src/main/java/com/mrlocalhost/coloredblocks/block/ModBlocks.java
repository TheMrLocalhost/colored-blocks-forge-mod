package com.mrlocalhost.coloredblocks.block;

import com.mrlocalhost.coloredblocks.ColoredBlocks;
import com.mrlocalhost.coloredblocks.block.custom.ColoredBlock;
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

    public static final RegistryObject<ColoredBlock> COLORED_STONE_BRICK =
        registerBlock("colored_stone_bricks", Material.STONE, 1.5f, 6.0f);
    public static final RegistryObject<ColoredBlock> COLORED_WOOD_PLANK =
        registerBlock("colored_wood_planks", Material.WOOD,1.5f,2.0f, false);

    private static <T extends Block> RegistryObject<ColoredBlock>
    registerBlock(String name, Material material, float strength, float resistance) {
        return registerBlock(name, material, strength, resistance, true);
    }

    private static <T extends Block> RegistryObject<ColoredBlock>
    registerBlock(String name, Material material, float strength, float resistance, boolean requiresTool) {
        Supplier<ColoredBlock> block = () -> {
            BlockBehaviour.Properties properties = BlockBehaviour.Properties
                    .of(material)
                    .strength(strength)
                    .explosionResistance(resistance)
                    .lightLevel(state -> state.getValue(ColoredBlock.LIT) ? 15 : 0);
            if(requiresTool) {
                properties = properties.requiresCorrectToolForDrops();
            }
            return new ColoredBlock(properties);
        };
        RegistryObject<ColoredBlock> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name, toReturn, ModCreativeTab.COLORED_BLOCKS_TAB);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
