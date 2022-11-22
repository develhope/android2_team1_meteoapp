package co.develhope.meteoapp.data.domainmodel

import org.threeten.bp.OffsetDateTime

data class TodayCardInfo(
    val date: OffsetDateTime,
    val weather: Weather,
    val temperature: Int,
    val rainfall: Int,
    val humidity: Int,
    val perceivedTemperature: Int,
    val wind: Int,
    val coverage: Int,
    val windDirection: Int,
    val rain : Int
)