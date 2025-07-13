package me.ajh123.immersive_airports.client.datagen;

import me.ajh123.immersive_airports.foundation.ModBlocks;
import me.ajh123.immersive_airports.foundation.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class IABlockTagProvider extends FabricTagProvider<Block> {
    public IABlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BLOCK, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.CLIMBABLE)
                .add(ModBlocks.RADIO_TOWER);

        getOrCreateTagBuilder(ModTags.RADIO_TOWER_CONNECTABLE)
                .add(ModBlocks.NDB_ANTENNA)
                .add(ModBlocks.ATIS_ANTENNA)
                .add(ModBlocks.VHF_ANTENNA);
    }
}
