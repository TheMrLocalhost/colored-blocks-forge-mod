package com.mrlocalhost.coloredblocks.block.custom;

import com.mrlocalhost.coloredblocks.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class ColoredBlock extends Block {

    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public ColoredBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
            .setValue(LIT, false)
        );
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND && player.isCrouching()) {
            ItemStack mainHandStack = player.getItemInHand(InteractionHand.MAIN_HAND);
            if (mainHandStack.isEmpty()) {
                if (blockState.getValue(LIT)) {
                    level.setBlock(blockPos, blockState.cycle(LIT), 3);
                    ItemStack deposedTorch = new ItemStack(ModItems.GLOW_DYE.get(), 1);
                    player.addItem(deposedTorch);
                }
            }
        }
        return super.use(blockState, level, blockPos, player, hand, blockHitResult);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(LIT);
    }
}
