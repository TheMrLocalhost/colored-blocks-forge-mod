package com.mrlocalhost.coloredblocks.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArtistPaletteItem extends Item {
    public ArtistPaletteItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.literal("Right click dye in main-hand to charge palette").withStyle(ChatFormatting.GREEN));
        } else {
            components.add(Component.literal("Equip in off-hand slot").withStyle(ChatFormatting.YELLOW));
            components.add(Component.literal("Press SHIFT for more info").withStyle(ChatFormatting.AQUA));
        }
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            player.sendSystemMessage(Component.literal("The artist palette is supposed to be in the off-hand slot."));
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }
}
