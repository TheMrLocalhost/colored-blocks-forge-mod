package com.mrlocalhost.coloredblocks.block.custom;

import com.mrlocalhost.coloredblocks.ColoredBlocks;
import com.mrlocalhost.coloredblocks.block.entity.ColoredBlockEntity;
import com.mrlocalhost.coloredblocks.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import static com.mrlocalhost.coloredblocks.utils.ColoredBlocksUtils.sendMessageToPlayer;

public class ColoredBlock extends BaseEntityBlock {

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
                    ItemStack deposedGlowDye = new ItemStack(ModItems.GLOW_DYE.get(), 1);
                    if (!player.isCreative()) {
                        player.addItem(deposedGlowDye);
                    }
                }
            }
        } else if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND && ColoredBlocks.LOGGER.isDebugEnabled()) {
            //Debug output to player
            // TODO remove after done testing functionality of paintbrush and world/player sync interactions
            ItemStack mainHandStack = player.getItemInHand(InteractionHand.MAIN_HAND);
            if (!mainHandStack.isEmpty()) {
                BlockEntity entity = level.getBlockEntity(blockPos);
                if (entity == null) {
                    sendMessageToPlayer(player, "Entity not found within block");
                } else {
                    CompoundTag nbt = entity.serializeNBT();
                    if (mainHandStack.getItem() == Items.WHITE_DYE) {
                        nbt.putInt("r", 0);
                        nbt.putInt("g", 0);
                        nbt.putInt("b", 0);
                        sendMessageToPlayer(player, "Reset nbt color");
                    } else if (mainHandStack.getItem() == Items.RED_DYE) {
                        int prev = nbt.getInt("r");
                        nbt.putInt("r", prev+1);
                        sendMessageToPlayer(player, "RGB: ");
                    } else if (mainHandStack.getItem() == Items.GREEN_DYE) {
                        int prev = nbt.getInt("g");
                        nbt.putInt("g", prev+1);
                    } else if (mainHandStack.getItem() == Items.BLUE_DYE) {
                        int prev = nbt.getInt("b");
                        nbt.putInt("b", prev+1);
                    }
                    sendMessageToPlayer(player, "Colored Block debug info:");
                    sendMessageToPlayer(player, "     Block: " + blockState.getBlock().getName().getString());
                    sendMessageToPlayer(player, "     Pos (x,y,z): %d, %d, %d".formatted(blockPos.getX(), blockPos.getY(), blockPos.getZ()));
                    sendMessageToPlayer(player, "     Lit: " + blockState.getValue(ColoredBlock.LIT));
                    sendMessageToPlayer(player, "     New RGB (r,g,b): %d, %d, %d".formatted(
                        nbt.getInt("r"), nbt.getInt("g"), nbt.getInt("b")));
                    entity.deserializeNBT(nbt);
                }
            } else {
                sendMessageToPlayer(player, "Colored Block debug info:");
                sendMessageToPlayer(player, "     Block: " + blockState.getBlock().getName().getString());
                sendMessageToPlayer(player, "     Pos (x,y,z): %d, %d, %d".formatted(blockPos.getX(), blockPos.getY(), blockPos.getZ()));
                sendMessageToPlayer(player, "     Lit: " + blockState.getValue(ColoredBlock.LIT));
                BlockEntity entity = level.getBlockEntity(blockPos);
                if (entity != null) {
                    CompoundTag nbt = entity.serializeNBT();
                    sendMessageToPlayer(player, "     RGB (r,g,b): %d, %d, %d".formatted(
                        nbt.getInt("r"), nbt.getInt("g"), nbt.getInt("b")));
                } else {
                    sendMessageToPlayer(player, "     Entity not found within block");
                }
            }
        }
        return super.use(blockState, level, blockPos, player, hand, blockHitResult);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(LIT);
    }

    /* BLOCK ENTITY */

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ColoredBlockEntity(blockPos, blockState);
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }
}
