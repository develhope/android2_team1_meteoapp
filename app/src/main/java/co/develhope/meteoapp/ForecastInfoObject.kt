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

    private val todayWeatherList: List<TodayCardInfo> = listOf(
        TodayCardInfo(OffsetDateTime.now(), Weather.SUNNY, 30, 0),
        TodayCardInfo(OffsetDateTime.now().plusHours(1), Weather.SUNNY, 30, 0),
        TodayCardInfo(OffsetDateTime.now().plusHours(2), Weather.CLOUDY, 28, 5),
        TodayCardInfo(OffsetDateTime.now().plusHours(3), Weather.SUNNY, 29, 0),
        TodayCardInfo(OffsetDateTime.now().plusHours(4), Weather.SUNNY, 30, 0),
        TodayCardInfo(OffsetDateTime.now().plusHours(5), Weather.SUNNY, 28, 0),
        TodayCardInfo(OffsetDateTime.now().plusHours(6), Weather.CLOUDY, 26, 15),
        TodayCardInfo(OffsetDateTime.now().plusHours(7), Weather.RAINY, 25, 60),
        TodayCardInfo(OffsetDateTime.now().plusHours(8), Weather.RAINY, 25, 80),
        TodayCardInfo(OffsetDateTime.now().plusHours(9), Weather.HEAVYRAIN, 23, 100),
        TodayCardInfo(OffsetDateTime.now().plusHours(10), Weather.HEAVYRAIN, 24, 100),
        TodayCardInfo(OffsetDateTime.now().plusHours(11), Weather.TEMPEST, 23, 100),
        TodayCardInfo(OffsetDateTime.now().plusHours(12), Weather.FOGGY, 26, 98)
    )

    fun getWeatherList(): List<CardInfo> {
        return weatherList
    }

    fun getTodayWeatherList(): List<TodayCardInfo>{
        return todayWeatherList
    }




}