package me.ajh123.immersive_airports.content.radio.blocks;

import com.mojang.serialization.MapCodec;
import me.ajh123.immersive_airports.content.radio.antennas.VHFAntenna;
import me.ajh123.immersive_airports.content.radio.block_entities.AntennaBlockEntity;
import me.ajh123.immersive_airports.foundation.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class VHFAntennaBlock extends AbstractAntennasBlock {
    public VHFAntennaBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(VHFAntennaBlock::new);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AntennaBlockEntity<>(
                () -> ModBlockEntities.VHF_BLOCK_ENTITY,
                pos,
                state,
                VHFAntenna::new
        );
    }
}
