package me.ajh123.immersive_airports.content.radio.blocks;

import me.ajh123.immersive_airports.content.radio.block_entities.RadioTowerControllerBlockEntity;
import me.ajh123.immersive_airports.foundation.ModBlockEntities;
import me.ajh123.immersive_airports.foundation.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.List;

// This class represents a block for a radio tower in the Immersive Airports mod.
// it's a modular block used to connect attached blocks together, such as antennas or other components.
// This block is not interacted with directly by players, but rather serves as a structural component in the game world.
public class RadioTowerBlock extends Block {
    // Block states:
    // - `north`, `south`, `east`, `west`, `up`, `down`:
    // These directions indicate the sides of the block that are connected to other blocks like antennas.
    public static final BooleanProperty NORTH = Properties.NORTH;
    public static final BooleanProperty EAST = Properties.EAST;
    public static final BooleanProperty SOUTH = Properties.SOUTH;
    public static final BooleanProperty WEST = Properties.WEST;
    public static final BooleanProperty UP = Properties.UP;
    public static final BooleanProperty DOWN = Properties.DOWN;

    public RadioTowerBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState()
            .with(NORTH, false)
            .with(SOUTH, false)
            .with(EAST, false)
            .with(WEST, false)
            .with(UP, false)
            .with(DOWN, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST, UP, DOWN);
    }

    // This method is called when the block is updated by a neighbor block.
    // It checks the neighboring blocks and updates the state of this block accordingly.
    // Neighboring blocks can be antennas or other components that connect to the radio tower.
    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        // Update the state based on the direction of the neighbor block.
        return switch (direction) {
            case NORTH -> state.with(NORTH, canConnectHorizontally(neighborState));
            case SOUTH -> state.with(SOUTH, canConnectHorizontally(neighborState));
            case EAST -> state.with(EAST, canConnectHorizontally(neighborState));
            case WEST -> state.with(WEST, canConnectHorizontally(neighborState));
            case UP -> state.with(UP, canConnectVertically(neighborState));
            case DOWN -> state.with(DOWN, canConnectVertically(neighborState));
        };
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();

        // Initialize the block state with default values based on the surrounding blocks.
        // Return the initialized state for placement.
        return getDefaultState()
            .with(NORTH, canConnectHorizontally(world.getBlockState(pos.north())))
            .with(SOUTH, canConnectHorizontally(world.getBlockState(pos.south())))
            .with(EAST, canConnectHorizontally(world.getBlockState(pos.east())))
            .with(WEST, canConnectHorizontally(world.getBlockState(pos.west())))
            .with(UP, canConnectVertically(world.getBlockState(pos.up())))
            .with(DOWN, canConnectVertically(world.getBlockState(pos.down())));
    }

    private boolean canConnectVertically(BlockState state) {
        // Check if the block can connect vertically to another radio tower block.
        return state.getBlock() instanceof RadioTowerBlock;
    }

    private boolean canConnectHorizontally(BlockState state) {
        // Check if the block can connect horizontally to another component.
        return state.isIn(ModTags.RADIO_TOWER_CONNECTABLE);
    }

    public List<BlockEntity> findHorizontal(World world, BlockPos pos) {
        List<BlockEntity> connectedBlocks = new java.util.ArrayList<>();
        BlockState state = world.getBlockState(pos);
        // Check each horizontal direction
        for (Direction dir : new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST}) {
            BooleanProperty prop = switch (dir) {
                case NORTH -> NORTH;
                case SOUTH -> SOUTH;
                case EAST -> EAST;
                case WEST -> WEST;
                default -> null;
            };
            if (prop != null && state.get(prop)) {
                BlockPos neighborPos = pos.offset(dir);
                BlockEntity be = world.getBlockEntity(neighborPos);
                if (be != null) {
                    connectedBlocks.add(be);
                }
            }
        }
        return connectedBlocks;
    }


    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
        if (!world.isClient) {
            notifyController(world, pos);
        }
    }

    private void notifyController(World world, BlockPos pos) {
        BlockPos scanPos = pos;
        BlockState state = world.getBlockState(scanPos);
        // Walk down the tower until DOWN is false
        while (state.get(RadioTowerBlock.DOWN)) {
            scanPos = scanPos.down();
            state = world.getBlockState(scanPos);
        }
        // Now scanPos is the bottom RadioTowerBlock, check below for controller
        BlockPos belowPos = scanPos.down();
        BlockState belowState = world.getBlockState(belowPos);
        if (belowState.getBlock() instanceof RadioTowerControllerBlock) {
            world.getBlockEntity(belowPos, ModBlockEntities.RADIO_TOWER_CONTROLLER)
                .ifPresent(RadioTowerControllerBlockEntity::onTowerUpdate);
        }
    }
}
