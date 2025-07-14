package me.ajh123.immersive_airports.content.weather;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class WeatherSystem {
    private static final String[] NATO_ALPHABET = {
            "Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot",
            "Golf", "Hotel", "India", "Juliett", "Kilo", "Lima",
            "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo",
            "Sierra", "Tango", "Uniform", "Victor", "Whiskey",
            "X-ray", "Yankee", "Zulu"
    };

    public static WeatherInfo getATISReport(World world, BlockPos pos) {
        float tempFloat = world.getBiome(pos).value().getTemperature();
        // Minecraft biome temperature is ~0.0 to 2.0, let's normalize it to a Celsius approximation
        int tempCelsius = Math.round(tempFloat * 20 + 10); // 0.0 = 10°C, 1.0 = 30°C, 2.0 = 50°C

        int dewpoint = tempCelsius - 2; // Fake dewpoint: just a bit under temp for immersion

        int windDir = 0;
        int windSpeed = 0;

        int visibility = 10;
        String cloudCoverage = "Clear";

        if (world.isThundering()) {
            visibility = 3;
            cloudCoverage = "Overcast at 2000 feet";
        } else if (world.isRaining()) {
            visibility = 5;
            cloudCoverage = "Broken clouds at 3000 feet";
        }

        String altimeter = "29.92";
        String airportName = "Minecraft Airport";

        long time = world.getTimeOfDay() % 24000;
        int letterIndex = (int)((time / 12000L) % NATO_ALPHABET.length);
        String infoLetter = NATO_ALPHABET[letterIndex];

        String timeZulu = ZonedDateTime.now(ZoneOffset.UTC)
                .format(DateTimeFormatter.ofPattern("HHmm", Locale.ENGLISH));

        return new WeatherInfo(
                windDir,
                windSpeed,
                visibility,
                cloudCoverage,
                altimeter,
                airportName,
                infoLetter,
                timeZulu,
                tempCelsius,
                dewpoint
        );
    }
}