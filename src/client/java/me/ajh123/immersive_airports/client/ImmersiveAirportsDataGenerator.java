package me.ajh123.immersive_airports.client;

import me.ajh123.immersive_airports.client.datagen.IABlockTagProvider;
import me.ajh123.immersive_airports.client.datagen.IAEnglishLangProvider;
import me.ajh123.immersive_airports.client.datagen.IAModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ImmersiveAirportsDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(IAEnglishLangProvider::new);
        pack.addProvider(IAModelProvider::new);
        pack.addProvider(IABlockTagProvider::new);
    }
}
