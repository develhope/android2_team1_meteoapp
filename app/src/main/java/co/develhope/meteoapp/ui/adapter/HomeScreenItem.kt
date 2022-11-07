package co.develhope.meteoapp.ui.adapter

import co.develhope.meteoapp.data.domainmodel.Weather
import org.threeten.bp.OffsetDateTime

sealed class HomeScreenItem{
    data class ForecastDetails(
        val date: OffsetDateTime,
        val minTemperature: Int,
        val maxTemperature: Int,
        val rainfall: Int,
        val wind: Int,
        val weather: Weather
    ): HomeScreenItem()

    data class Title (val city: String, val region: String): HomeScreenItem()

    data class SubTitle (val next5Days: String): HomeScreenItem()
}