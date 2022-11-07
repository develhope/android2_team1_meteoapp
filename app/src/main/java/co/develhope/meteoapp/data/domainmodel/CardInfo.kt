package co.develhope.meteoapp.data.domainmodel

import co.develhope.meteoapp.Weather
import org.threeten.bp.OffsetDateTime

data class CardInfo(
    val date: OffsetDateTime,
    val minTemperature: Int,
    val maxTemperature: Int,
    val rainfall: Int,
    val wind: Int,
    val weather: Weather
)