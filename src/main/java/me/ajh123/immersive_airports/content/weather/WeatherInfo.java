package me.ajh123.immersive_airports.content.weather;

public record WeatherInfo(
        int windDirection,     // Degrees
        int windSpeed,         // Knots
        int visibilityKm,      // Kilometers
        String skyCondition,   // "Clear", "Few Clouds", "Overcast"
        String altimeter,       // "29.92", "1013.2", etc.
        String airportName,
        String informationLetter,
        String timeZulu,         // e.g., "1500"
        int temperatureCelsius,
        int dewpointCelsius
) { }