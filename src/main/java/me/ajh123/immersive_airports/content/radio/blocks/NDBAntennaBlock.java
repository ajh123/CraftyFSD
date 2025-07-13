package me.ajh123.immersive_airports.content.radio.blocks;

import com.mojang.serialization.MapCodec;
import me.ajh123.immersive_airports.content.radio.antennas.NDBAntenna;
import me.ajh123.immersive_airports.content.radio.block_entities.AntennaBlockEntity;
import me.ajh123.immersive_airports.foundation.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class NDBAntennaBlock extends AbstractAntennasBlock {
    public NDBAntennaBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(NDBAntennaBlock::new);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AntennaBlockEntity<>(
                () -> ModBlockEntities.NDB_BLOCK_ENTITY,
                pos,
                state,
                NDBAntenna::new
        );
    }
}
