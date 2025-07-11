package me.ajh123.immersive_airports.foundation;

import me.ajh123.immersive_airports.ImmersiveAirports;
import me.ajh123.immersive_airports.content.radio.RadioTowerBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final RadioTowerBlock RADIO_TOWER = register(
            new RadioTowerBlock(Block.Settings.create().nonOpaque()),
            "radio_tower",
            true
    );

    public static <T extends Block> T register(T block, String name, boolean shouldRegisterItem) {
        Identifier id = ImmersiveAirports.identifier(name);

        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static void initialize() {}
}