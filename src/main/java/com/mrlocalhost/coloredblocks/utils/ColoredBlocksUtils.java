package com.mrlocalhost.coloredblocks.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class ColoredBlocksUtils {

    public static void sendMessageToPlayer(Player player, String message) {
        player.sendSystemMessage(Component.literal(message));
    }

}
