package com.mrlocalhost.coloredblocks.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ColoredBlockEntity extends BlockEntity {

    private final String[] rgbKeys = new String[]{"r", "g", "b"};
    private final CompoundTag rgbNbt = new CompoundTag();

    public ColoredBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.COLORED_STONE_BRICK_ENTITY.get(), blockPos, blockState);
        rgbNbt.putInt("r", 0);
        rgbNbt.putInt("g", 0);
        rgbNbt.putInt("b", 0);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) { //saving from class to world
        for (String key: rgbKeys) {
            nbt.putInt(key, rgbNbt.getInt(key));
        }
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) { //loading from world into class
        super.load(nbt);
        for (String key: rgbKeys) {
            rgbNbt.putInt(key, nbt.getInt(key));
        }
    }


}
