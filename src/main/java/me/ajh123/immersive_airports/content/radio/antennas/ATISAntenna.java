package me.ajh123.immersive_airports.content.radio.antennas;

import me.ajh123.immersive_airports.ImmersiveAirports;
import me.ajh123.immersive_airports.content.weather.WeatherInfo;
import me.ajh123.immersive_airports.content.weather.WeatherSystem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ATISAntenna extends Antenna {
    private int tickCounter = 0; // counts ticks

    @Override
    public void resetState() {
        super.resetState();
        tickCounter = 0; // reset tick counter when the antenna state is reset
    }

    @Override
    public Identifier getType() {
        return ImmersiveAirports.identifier("atis");
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
        tickCounter++;
        if (tickCounter >= 6000) {  // 5 minutes
            tickCounter = 0;

            List<PlayerEntity> nearbyPlayers = getNearbyPlayers();
            if (nearbyPlayers.isEmpty()) return;

            for (PlayerEntity player : nearbyPlayers) {
                if (player.isAlive()) {
                    broadcast(player);
                }
            }
        }
    }

    private void broadcast(PlayerEntity player) {
        if (controller == null || controller.getWorld() == null) return;

        WeatherInfo info = WeatherSystem.getATISReport(controller.getWorld(), controller.getPos());

        player.sendMessage(
                Text.translatable(
                        "message.immersive_airports.atis.full",
                        info.airportName(),
                        info.informationLetter(),
                        info.timeZulu(),
                        info.windDirection(),
                        info.windSpeed(),
                        info.visibilityKm(),
                        info.skyCondition(),
                        info.temperatureCelsius(),
                        info.dewpointCelsius(),
                        info.altimeter(),
                        info.informationLetter()
                ),
                false
        );
    }
}

