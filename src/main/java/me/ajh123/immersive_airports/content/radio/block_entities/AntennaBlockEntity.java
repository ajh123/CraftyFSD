package me.ajh123.immersive_airports.content.radio.block_entities;

import me.ajh123.immersive_airports.content.radio.antennas.Antenna;
import me.ajh123.immersive_airports.content.radio.antennas.AntennaProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;

public class AntennaBlockEntity<T extends Antenna> extends BlockEntity implements AntennaProvider {
    private final T antenna;

    public AntennaBlockEntity(
            EntityTypeFactory<T> type,
            BlockPos pos,
            BlockState state,
            AntennaFactory<T> antennaFactory
    ) {
        super(type.getEntityType(), pos, state);
        this.antenna = antennaFactory.create();
    }

    @Override
    public T getAntenna() {
        return antenna;
    }

    public static <T extends Antenna> AntennaBlockEntity<T> builder(
            BlockPos blockPos,
            BlockState blockState,
            EntityTypeFactory<T> type,
            AntennaFactory<T> antennaFactory
    ) {
        return new AntennaBlockEntity<>(type, blockPos, blockState, antennaFactory);
    }

    /**
     * A functional interface for a factory that creates a new instance of an Antenna.
     *
     * @param <T> The type of Antenna.
     */
    @FunctionalInterface
    public interface AntennaFactory<T> {
        T create();
    }

    /**
     * A functional interface for a factory that provides the BlockEntityType for a specific Antenna type.
     *
     * @param <T> The type of Antenna.
     */
    @FunctionalInterface
    public interface EntityTypeFactory<T extends Antenna> {
        BlockEntityType<AntennaBlockEntity<T>> getEntityType();
    }
}
