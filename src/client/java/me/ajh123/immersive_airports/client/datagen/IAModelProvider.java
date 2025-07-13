package me.ajh123.immersive_airports.client.datagen;

import me.ajh123.immersive_airports.ImmersiveAirports;
import me.ajh123.immersive_airports.content.radio.blocks.AbstractAntennasBlock;
import me.ajh123.immersive_airports.foundation.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Optional;
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

        Identifier basicAntenna = ImmersiveAirports.identifier("block/basic_antenna");
        Block[] antennas = new Block[]{
                ModBlocks.VHF_ANTENNA,
                ModBlocks.NDB_ANTENNA,
                ModBlocks.ATIS_ANTENNA
        };
        for (Block antenna : antennas) {
            VariantSetting<VariantSettings.Rotation> yRot = VariantSettings.Y;
            blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(
                    antenna
            ).coordinate(BlockStateVariantMap.create(AbstractAntennasBlock.FACING)
                    .register(Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, basicAntenna))
                    .register(Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, basicAntenna).put(yRot, VariantSettings.Rotation.R90))
                    .register(Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, basicAntenna).put(yRot, VariantSettings.Rotation.R180))
                    .register(Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, basicAntenna).put(yRot, VariantSettings.Rotation.R270))
            ));
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        Identifier basicAntenna = ImmersiveAirports.identifier("block/basic_antenna");
        Model antennaModel = new Model(Optional.of(basicAntenna), Optional.empty());
        Block[] antennas = new Block[]{
                ModBlocks.VHF_ANTENNA,
                ModBlocks.NDB_ANTENNA,
                ModBlocks.ATIS_ANTENNA
        };
        for (Block antenna : antennas) {
            itemModelGenerator.register(antenna.asItem(), antennaModel);
        }
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
