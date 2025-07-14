package me.ajh123.immersive_airports.content.radio.block_entities;

import me.ajh123.immersive_airports.content.radio.antennas.Antenna;
import me.ajh123.immersive_airports.content.radio.antennas.AntennaProvider;
import me.ajh123.immersive_airports.content.radio.blocks.RadioTowerBlock;
import me.ajh123.immersive_airports.content.radio.screens.RadioTowerControllerScreenHandler;
import me.ajh123.immersive_airports.foundation.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RadioTowerControllerBlockEntity extends BlockEntity {
    private final List<Antenna> rawAntennas = new ArrayList<>();
    private final List<Antenna> collapsedAntennas = new ArrayList<>();
    private boolean firstTick = true;
    private boolean pendingEnumeration = false;

    public RadioTowerControllerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RADIO_TOWER_CONTROLLER, pos, state);
    }

    public void onTowerUpdate() {
        if (world != null && !world.isClient) {
            // Schedule enumeration for next tick, makes sure block states are synchronised before enumeration
            pendingEnumeration = true;
        }
    }

    /**
     * Enumerates all AntennaProvider blocks attached horizontally to contiguous RadioTowerBlocks above this controller.
     * Respects the block states of each RadioTowerBlock for valid connections and vertical connections via UP property.
     */
    public void enumerateTowerAntennas() {
        if (world == null) return;

        List<Antenna> antennas = new ArrayList<>();
        BlockPos scanPos = this.pos.up();

        while (true) {
            BlockState towerState = world.getBlockState(scanPos);

            // Ensure the block is actually a RadioTowerBlock
            if (towerState.getBlock() instanceof RadioTowerBlock rt) {
                List<BlockEntity> entities = rt.findHorizontal(world, scanPos);
                for (BlockEntity entity : entities) {
                    if (entity instanceof AntennaProvider provider) {
                        Antenna antenna = provider.getAntenna();
                        if (antenna != null) {
                            antennas.add(antenna);
                            // If the antenna is not already in the rawAntennas list, reset its state and set the controller
                            if (!rawAntennas.contains(antenna)) {
                                antenna.resetState();
                                antenna.setController(this);
                            }
                        }
                    }
                }
            } else {
                break;
            }

            // Move up only if the tower says "UP = true"
            BooleanProperty upProp = getPropertyForDirection(Direction.UP);
            if (!towerState.contains(upProp) || !towerState.get(upProp)) break;

            scanPos = scanPos.up();
        }

        this.rawAntennas.clear();
        this.rawAntennas.addAll(antennas);
        this.collapseAntennas(); // Collapse to unique antenna types
    }

    // Helper to get the BooleanProperty for a given direction
    public static BooleanProperty getPropertyForDirection(Direction dir) {
        return switch (dir) {
            case NORTH -> RadioTowerBlock.NORTH;
            case SOUTH -> RadioTowerBlock.SOUTH;
            case EAST  -> RadioTowerBlock.EAST;
            case WEST  -> RadioTowerBlock.WEST;
            case UP    -> RadioTowerBlock.UP;
            case DOWN  -> RadioTowerBlock.DOWN;
        };
    }

    public ActionResult onUse(PlayerEntity player, BlockHitResult hit) {
        if (world == null || world.isClient) return ActionResult.SUCCESS;
//        // Log devices to player's chat
//        Text message = Text.literal("Radio Tower Antennas:\n");
//        if (rawAntennas.isEmpty()) {
//            message.getSiblings().add(Text.literal("No antennas connected."));
//        } else {
//            for (Antenna antenna : this.collapsedAntennas) {
//                message.getSiblings().add(Text.literal(
//                        String.format("- %s x%d (Required: %d)\n", antenna.getType(), getAntennaCount(antenna), antenna.minimumCountRequired())
//                ));
//            }
//        }
//        player.sendMessage(message, false);
        // Open the GUI for the player
        BlockState state = world.getBlockState(pos);
        player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        return ActionResult.CONSUME;
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, RadioTowerControllerBlockEntity radioTowerControllerBlockEntity) {
        if (world.isClient) return; // Only run on server side

        if (radioTowerControllerBlockEntity.firstTick) {
            radioTowerControllerBlockEntity.pendingEnumeration = true; // Set pending enumeration on first tick
            radioTowerControllerBlockEntity.firstTick = false; // Reset first tick flag
        }
        if (radioTowerControllerBlockEntity.pendingEnumeration) {
            radioTowerControllerBlockEntity.enumerateTowerAntennas();
            radioTowerControllerBlockEntity.pendingEnumeration = false;
        }

        // Tick all antennas
        for (Antenna antenna : radioTowerControllerBlockEntity.rawAntennas) {
            // Don't tick if we don't meet the minimum count required
            if (radioTowerControllerBlockEntity.getAntennaCount(antenna) >= antenna.minimumCountRequired()) {
                antenna.tick();
            }
        }
    }

    /**
     * Returns a list of antennas, collapsed to exactly one of each type.
     * This is used to avoid duplicate antennas in the list.
     */
    private void collapseAntennas() {
        List<Antenna> collapsed = new ArrayList<>();
        for (Antenna antenna : rawAntennas) {
            boolean found = false;
            for (Antenna existing : collapsed) {
                if (existing.getType().equals(antenna.getType())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                collapsed.add(antenna);
            }
        }
        this.collapsedAntennas.clear();
        this.collapsedAntennas.addAll(collapsed);
    }

    private int getAntennaCount(Antenna type) {
        int count = 0;
        for (Antenna antenna : rawAntennas) {
            if (antenna.getType().equals(type.getType())) {
                count++;
            }
        }
        return count;
    }
}
