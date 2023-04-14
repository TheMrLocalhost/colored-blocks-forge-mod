package com.mrlocalhost.coloredblocks.item;

import com.mrlocalhost.coloredblocks.ColoredBlocks;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, ColoredBlocks.MOD_ID);

    public static final RegistryObject<Item> PAINTBRUSH = ITEMS.register("paintbrush",
        () -> new Item(new Item.Properties()
            .tab(ModCreativeTab.COLORED_BLOCKS_TAB)
            .stacksTo(1)));
    public static final RegistryObject<Item> ARTIST_PALETTE = ITEMS.register("artist_palette",
        () -> new Item(new Item.Properties()
            .tab(ModCreativeTab.COLORED_BLOCKS_TAB)
            .stacksTo(1)));
    public static final RegistryObject<Item> CLEANING_CLOTH = ITEMS.register("cleaning_cloth",
        () -> new Item(new Item.Properties()
            .tab(ModCreativeTab.COLORED_BLOCKS_TAB)
            .stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
