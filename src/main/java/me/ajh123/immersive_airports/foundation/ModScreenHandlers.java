package me.ajh123.immersive_airports.foundation;

import me.ajh123.immersive_airports.content.radio.screens.RadioTowerControllerScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;

public class ModScreenHandlers {
    public static ScreenHandlerType<RadioTowerControllerScreenHandler> RADIO_TOWER_CONTROLLER_SCREEN_HANDLER = register(
            "radio_tower_controller",
            (syncId, playerInventory) -> new RadioTowerControllerScreenHandler(syncId, playerInventory, ScreenHandlerContext.EMPTY)
    );

    public static void initialize() {}

    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String id, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER, id, new ScreenHandlerType<>(factory, FeatureFlags.VANILLA_FEATURES));
    }
}

