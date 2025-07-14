package me.ajh123.immersive_airports.content.radio.antennas;

import me.ajh123.immersive_airports.ImmersiveAirports;
import net.minecraft.util.Identifier;

public class VHFAntenna extends Antenna {
    @Override
    public Identifier getType() {
        return ImmersiveAirports.identifier("vhf");
    }

    @Override
    public int getRangeMeters() {
        return 500;
    }

    @Override
    public int minimumCountRequired() {
        return 2;
    }

    @Override
    protected void onTick() {

    }
}

