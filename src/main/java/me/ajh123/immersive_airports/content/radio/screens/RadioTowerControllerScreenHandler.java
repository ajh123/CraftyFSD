package me.ajh123.immersive_airports.content.radio.screens;

import me.ajh123.immersive_airports.foundation.ModScreenHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;

public class RadioTowerControllerScreenHandler extends ScreenHandler {

    public RadioTowerControllerScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext screenHandlerContext) {
        super(ModScreenHandlers.RADIO_TOWER_CONTROLLER_SCREEN_HANDLER, syncId);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
