package me.ajh123.immersive_airports.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class IAEnglishLangProvider extends FabricLanguageProvider {
    public IAEnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        // Specifying en_us is optional, as it's the default language code
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("itemGroup.immersive_airports.main", "Immersive Airports");
        translationBuilder.add("block.immersive_airports.radio_tower", "Radio Tower");
        translationBuilder.add("block.immersive_airports.radio_tower_controller", "Radio Tower Controller");
        translationBuilder.add("block.immersive_airports.vhf_antenna", "VHF Antenna");
        translationBuilder.add("block.immersive_airports.ndb_antenna", "NDB Antenna");
        translationBuilder.add("block.immersive_airports.atis_antenna", "ATIS Antenna");
        translationBuilder.add(
                "message.immersive_airports.atis.full",
                "ATIS: %s, Information %s. Time %s Zulu. Wind %s° at %s knots, visibility %s kilometers, %s, temperature %s°, dewpoint %s°, altimeter %s. Advise on initial contact you have Information %s"
        );
        translationBuilder.add(
                "container.immersive_airports.radio_tower_controller",
                "Radio Tower Controller"
        );
    }
}
