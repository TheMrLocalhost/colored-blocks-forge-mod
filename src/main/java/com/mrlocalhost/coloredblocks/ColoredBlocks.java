package com.mrlocalhost.coloredblocks;

import com.mojang.logging.LogUtils;
import com.mrlocalhost.coloredblocks.block.ModBlocks;
import com.mrlocalhost.coloredblocks.item.ModItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ColoredBlocks.MOD_ID)
public class ColoredBlocks {
    public static final String MOD_ID = "colored_blocks";
    public static final Logger LOGGER = LogUtils.getLogger();
    public ColoredBlocks() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus); //Register Items
        ModBlocks.register(modEventBus); //Register Blocks
        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }
    private void commonSetup(final FMLCommonSetupEvent event) {
        //LOGGER.info("Preparing "+MOD_ID);
    }
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            //LOGGER.info("Preparing "+MOD_ID+" for client...");
        }
    }
}
