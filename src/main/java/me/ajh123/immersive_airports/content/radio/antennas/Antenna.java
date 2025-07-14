package me.ajh123.immersive_airports.content.radio.antennas;

import me.ajh123.immersive_airports.content.radio.block_entities.RadioTowerControllerBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class Antenna {
    protected @Nullable RadioTowerControllerBlockEntity controller;

    public void setController(@NotNull RadioTowerControllerBlockEntity controller) {
        this.controller = controller;
    }

    public abstract Identifier getType();
    public abstract int getRangeMeters();
    public abstract int minimumCountRequired();
    protected abstract void onTick();

    public void resetState() {
        controller = null;
    }

    public final void tick() {
        if (controller == null || controller.getWorld() == null) return;
        onTick();
    }

    public @Nullable Box getRangeBox() {
        if (controller == null || controller.getWorld() == null) return null;

        BlockPos pos = controller.getPos();
        return new Box(
                pos.getX() - this.getRangeMeters(), pos.getY() - this.getRangeMeters(), pos.getZ() - this.getRangeMeters(),
                pos.getX() + this.getRangeMeters(), pos.getY() + this.getRangeMeters(), pos.getZ() + this.getRangeMeters()
        );
    }

    public List<PlayerEntity> getNearbyPlayers() {
        if (controller == null || controller.getWorld() == null) return List.of();

        World world = controller.getWorld();
        Box range = getRangeBox();
        return world.getEntitiesByType(TypeFilter.instanceOf(PlayerEntity.class), range, player -> player != null && player.isAlive());
    }
}