package com.mrlocalhost.coloredblocks.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import com.mrlocalhost.coloredblocks.block.custom.ColoredBlock;
import static com.mrlocalhost.coloredblocks.utils.ColoredBlocksConstants.COLORED_BLOCKS;

public class GlowDyeItem extends Item {
    public GlowDyeItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.literal("Glow Dye can be reclaimed by sneak right-clicking with an empty hand.").withStyle(ChatFormatting.GREEN));
        } else {
            components.add(Component.literal("Sneak right-click on colored block to light").withStyle(ChatFormatting.YELLOW));
            components.add(Component.literal("Press SHIFT for more info").withStyle(ChatFormatting.AQUA));
        }
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        if (player == null) {
            return InteractionResult.FAIL;
        }
        InteractionHand hand = context.getHand();
        ItemStack mainHandStack = context.getItemInHand();
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        if (COLORED_BLOCKS.contains(blockState.getBlock())) {
            if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND && player.isCrouching()) {
                if (!blockState.getValue(ColoredBlock.LIT)) {
                    mainHandStack.setCount(mainHandStack.getCount()-1);
                    level.setBlock(blockPos, blockState.cycle(ColoredBlock.LIT), 3);
                }
            }
        }
        return InteractionResult.PASS;
    }
}
