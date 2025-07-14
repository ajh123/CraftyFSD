package me.ajh123.immersive_airports.client;

import me.ajh123.immersive_airports.client.screens.RadioTowerControllerScreen;
import me.ajh123.immersive_airports.foundation.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ImmersiveAirportsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.RADIO_TOWER_CONTROLLER_SCREEN_HANDLER, RadioTowerControllerScreen::new);
    }
}
