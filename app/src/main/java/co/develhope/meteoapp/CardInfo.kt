package co.develhope.meteoapp


import org.threeten.bp.OffsetDateTime

sealed class HomeScreenRecyclerView{
    data class CardInfo(
        val date: OffsetDateTime,
        val minTemperature: Int,
        val maxTemperature: Int,
        val rainfall: Int,
        val wind: Int,
        val weather: Weather
    ): HomeScreenRecyclerView()

    data class TitleHomeScreen (val city: String, val region: String): HomeScreenRecyclerView()

    data class Next5DaysHomeScreen (val next5Days: String): HomeScreenRecyclerView()
}

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
