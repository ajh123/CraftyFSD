package me.ajh123.immersive_airports;

import me.ajh123.immersive_airports.foundation.Initialisation;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class ImmersiveAirports implements ModInitializer {
    public static final String MOD_ID = "immersive_airports";

    @Override
    public void onInitialize() {
        Initialisation.initialize();
    }

    public static Identifier identifier(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
