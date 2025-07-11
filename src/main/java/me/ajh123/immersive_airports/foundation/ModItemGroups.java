package me.ajh123.immersive_airports.foundation;

import me.ajh123.immersive_airports.ImmersiveAirports;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> MAIN_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), ImmersiveAirports.identifier("main"));
    public static final ItemGroup MAIN_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.RADIO_TOWER.asItem()))
            .displayName(Text.translatable("itemGroup.immersive_airports.main"))
            .build();

    public static void initialize() {
        // This method is called to initialize the item groups.
        // It ensures that the item groups are registered and ready for use in the game.
        Registry.register(Registries.ITEM_GROUP, MAIN_GROUP_KEY, MAIN_GROUP);

        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.MAIN_GROUP_KEY).register((itemGroup) -> {
            itemGroup.add(ModBlocks.RADIO_TOWER.asItem());
        });
    }
}
