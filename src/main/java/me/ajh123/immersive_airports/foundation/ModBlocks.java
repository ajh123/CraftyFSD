package me.ajh123.immersive_airports.foundation;

import me.ajh123.immersive_airports.ImmersiveAirports;
import me.ajh123.immersive_airports.content.radio.blocks.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final RadioTowerBlock RADIO_TOWER = register(
            new RadioTowerBlock(AbstractBlock.Settings.create().nonOpaque()),
            "radio_tower",
            true
    );
    public static final VHFAntennaBlock VHF_ANTENNA = register(
            new VHFAntennaBlock(AbstractBlock.Settings.create()),
            "vhf_antenna",
            true
    );
    public static final NDBAntennaBlock NDB_ANTENNA = register(
            new NDBAntennaBlock(AbstractBlock.Settings.create()),
            "ndb_antenna",
            true
    );
    public static final ATISAntennaBlock ATIS_ANTENNA = register(
            new ATISAntennaBlock(AbstractBlock.Settings.create()),
            "atis_antenna",
            true
    );
    public static final RadioTowerControllerBlock RADIO_TOWER_CONTROLLER = register(
            new RadioTowerControllerBlock(AbstractBlock.Settings.create()),
            "radio_tower_controller",
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