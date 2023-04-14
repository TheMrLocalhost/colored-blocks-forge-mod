package com.mrlocalhost.coloredblocks.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ArtistPaletteItem extends Item {
    public ArtistPaletteItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            player.sendSystemMessage(Component.literal("The artist palette is supposed to be in the off-hand slot."));
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }
}
