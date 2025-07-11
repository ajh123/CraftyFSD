package me.ajh123.immersive_airports.foundation;

import me.ajh123.immersive_airports.ImmersiveAirports;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class ModTags {
    public static final TagKey<Block> RADIO_TOWER_CONNECTABLE = TagKey.of(RegistryKeys.BLOCK, ImmersiveAirports.identifier("radio_tower_connectable"));
}
