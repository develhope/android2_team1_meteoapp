package co.develhope.meteoapp

import org.threeten.bp.OffsetDateTime

object ForecastInfoObject {

    private val weatherList: List<CardInfo> = listOf(
        CardInfo(OffsetDateTime.now().plusDays(1), 18, 22, 10, 20, Weather.CLOUDY),
        CardInfo(OffsetDateTime.now().plusDays(2), 16, 21, 20, 25, Weather.RAINY),
        CardInfo(OffsetDateTime.now().plusDays(3), 15, 20, 80, 24, Weather.HEAVYRAIN),
        CardInfo(OffsetDateTime.now().plusDays(4), 22, 26, 0, 15, Weather.SUNNY),
        CardInfo(OffsetDateTime.now().plusDays(5), 24, 32, 5, 40, Weather.WINDY)
    )

    fun getWeatherList(): List<CardInfo> {
        return weatherList
    }
}