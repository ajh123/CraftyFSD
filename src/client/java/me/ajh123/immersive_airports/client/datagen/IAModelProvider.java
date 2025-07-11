package me.ajh123.immersive_airports.client.datagen;

import me.ajh123.immersive_airports.ImmersiveAirports;
import me.ajh123.immersive_airports.foundation.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class IAModelProvider extends FabricModelProvider {
    public IAModelProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        Identifier radioTowerId = ImmersiveAirports.identifier("block/radio_tower");
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(
                ModBlocks.RADIO_TOWER,
                BlockStateVariant.create().put(VariantSettings.MODEL, radioTowerId)
        ));

//        blockStateModelGenerator.blockStateCollector.accept(createBlockWithConnections(ModBlocks.RADIO_TOWER, radioTowerId));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    }

//    private static BlockStateSupplier createBlockWithConnections(Block block, Identifier modelId) {
//        VariantSetting<Boolean> uvlock = VariantSettings.UVLOCK;
//        return VariantsBlockStateSupplier.create(block)
//                .coordinate(BlockStateVariantMap.create(Properties.UP)
//                    .register(true, BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(uvlock, true))
//                    .register(false, BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(uvlock, true))
//                ).coordinate(BlockStateVariantMap.create(Properties.DOWN)
//                        .register(true, BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(uvlock, true))
//                        .register(false, BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(uvlock, true))
//                ).coordinate(BlockStateVariantMap.create(Properties.NORTH)
//                        .register(true, BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(uvlock, true))
//                        .register(false, BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(uvlock, true))
//                ).coordinate(BlockStateVariantMap.create(Properties.EAST)
//                        .register(true, BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(uvlock, true))
//                        .register(false, BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(uvlock, true))
//                ).coordinate(BlockStateVariantMap.create(Properties.SOUTH)
//                        .register(true, BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(uvlock, true))
//                        .register(false, BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(uvlock, true))
//                ).coordinate(BlockStateVariantMap.create(Properties.WEST)
//                        .register(true, BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(uvlock, true))
//                        .register(false, BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(uvlock, true))
//                );
//    }
}
