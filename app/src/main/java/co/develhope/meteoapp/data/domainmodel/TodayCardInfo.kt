package co.develhope.meteoapp.data.domainmodel

import co.develhope.meteoapp.Weather
import org.threeten.bp.OffsetDateTime

data class TodayCardInfo(
    val date: OffsetDateTime,
    val weather: Weather,
    val temperature: Int,
    val rainfall: Int
)