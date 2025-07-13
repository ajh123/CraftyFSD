package me.ajh123.immersive_airports.foundation;

import me.ajh123.immersive_airports.ImmersiveAirports;
import me.ajh123.immersive_airports.content.radio.antennas.ATISAntenna;
import me.ajh123.immersive_airports.content.radio.antennas.Antenna;
import me.ajh123.immersive_airports.content.radio.antennas.NDBAntenna;
import me.ajh123.immersive_airports.content.radio.antennas.VHFAntenna;
import me.ajh123.immersive_airports.content.radio.block_entities.*;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<AntennaBlockEntity<ATISAntenna>> ATIS_BLOCK_ENTITY = register(
            "atis_antenna",
            antennaFactory(() -> ModBlockEntities.ATIS_BLOCK_ENTITY, ATISAntenna::new),
            ModBlocks.ATIS_ANTENNA
    );

    public static final BlockEntityType<AntennaBlockEntity<NDBAntenna>> NDB_BLOCK_ENTITY = register(
            "ndb_antenna",
            antennaFactory(() -> ModBlockEntities.NDB_BLOCK_ENTITY, NDBAntenna::new),
            ModBlocks.NDB_ANTENNA
    );

    public static final BlockEntityType<AntennaBlockEntity<VHFAntenna>> VHF_BLOCK_ENTITY = register(
            "vhf_antenna",
            antennaFactory(() -> ModBlockEntities.VHF_BLOCK_ENTITY, VHFAntenna::new),
            ModBlocks.VHF_ANTENNA
    );

    public static final BlockEntityType<RadioTowerControllerBlockEntity> RADIO_TOWER_CONTROLLER = register(
            "radio_tower_controller",
            RadioTowerControllerBlockEntity::new,
            ModBlocks.RADIO_TOWER_CONTROLLER
    );

    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            BlockEntityType.BlockEntityFactory <? extends T> entityFactory,
            Block... blocks
    ) {
        Identifier id = ImmersiveAirports.identifier(name);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, BlockEntityType.Builder.<T>create(entityFactory, blocks).build());
    }

    private static <T extends Antenna> BlockEntityType.BlockEntityFactory<AntennaBlockEntity<T>> antennaFactory(
            AntennaBlockEntity.EntityTypeFactory<T> type, AntennaBlockEntity.AntennaFactory<T> antennaFactory
    ) {
        return (pos, state) -> AntennaBlockEntity.builder(pos, state, type, antennaFactory);
    }

    public static void initialize() {}
}
