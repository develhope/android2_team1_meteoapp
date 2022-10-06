package co.develhope.meteoapp

import java.util.Date

data class CardInfo(
    val date: Date,
    val minTemperature: Int,
    val maxTemperature: Int,
    val rainfall: Int,
    val wind: Int,
    val weather: Weather
) {

}

enum class Weather {

    SUNNY, CLOUDY, RAINY, FOGGY, TEMPEST, WINDY, HEAVYRAIN

}
