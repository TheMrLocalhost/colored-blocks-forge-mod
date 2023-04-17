package com.mrlocalhost.coloredblocks.block.entity;

import com.mrlocalhost.coloredblocks.ColoredBlocks;
import com.mrlocalhost.coloredblocks.utils.ColoredBlocksConstants;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
        DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ColoredBlocks.MOD_ID);

    public static final RegistryObject<BlockEntityType<ColoredBlockEntity>> COLORED_STONE_BRICK_ENTITY =
        BLOCK_ENTITIES.register("colored_block_entity", () ->
            BlockEntityType.Builder.of(
                ColoredBlockEntity::new,
                ColoredBlocksConstants.COLORED_BLOCKS.toArray(Block[]::new)
            ).build(null)
        );

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
