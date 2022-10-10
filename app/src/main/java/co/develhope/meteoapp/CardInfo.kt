package co.develhope.meteoapp


import org.threeten.bp.OffsetDateTime

data class CardInfo(
    val date: OffsetDateTime,
    val minTemperature: Int,
    val maxTemperature: Int,
    val rainfall: Int,
    val wind: Int,
    val weather: Weather
)

data class TodayCardInfo(
    val date: OffsetDateTime,
    val weather: Weather,
    val temperature: Int,
    val rainfall: Int
)


enum class Weather {

    SUNNY, CLOUDY, RAINY, FOGGY, TEMPEST, WINDY, HEAVYRAIN

}
