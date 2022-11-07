package co.develhope.meteoapp.dto

import co.develhope.meteoapp.CardInfo
import co.develhope.meteoapp.toWeather
import com.google.gson.annotations.SerializedName
import org.threeten.bp.OffsetDateTime


data class WeeklySummary(
    @SerializedName("current_weather")
    val currentWeather: CurrentWeather,
    @SerializedName("daily")
    val daily: Daily,
    @SerializedName("daily_units")
    val dailyUnits: DailyUnits,
    @SerializedName("elevation")
    val elevation: Double,
    @SerializedName("generationtime_ms")
    val generationtimeMs: Double,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int
) {
    data class Daily(
        @SerializedName("precipitation_sum")
        val precipitationSum: List<Double>,
        @SerializedName("rain_sum")
        val rainSum: List<Double>,
        @SerializedName("sunrise")
        val sunrise: List<String>,
        @SerializedName("sunset")
        val sunset: List<String>,
        @SerializedName("temperature_2m_max")
        val temperature2mMax: List<Double>,
        @SerializedName("temperature_2m_min")
        val temperature2mMin: List<Double>,
        @SerializedName("time")
        val time: List<String>,
        @SerializedName("weathercode")
        val weathercode: List<Int>
    ) {
        fun toDomain(): CardInfo {
            return CardInfo(
                date = OffsetDateTime.now(), //time.first(),
                minTemperature = temperature2mMin.first().toInt(),
                maxTemperature = temperature2mMax.first().toInt(),
                rainfall = rainSum.first().toInt(),
                wind = 0,
                weather = weathercode.first().toWeather()
            )
        }
    }

    data class DailyUnits(
        @SerializedName("precipitation_sum")
        val precipitationSum: String,
        @SerializedName("rain_sum")
        val rainSum: String,
        @SerializedName("sunrise")
        val sunrise: String,
        @SerializedName("sunset")
        val sunset: String,
        @SerializedName("temperature_2m_max")
        val temperature2mMax: String,
        @SerializedName("temperature_2m_min")
        val temperature2mMin: String,
        @SerializedName("time")
        val time: String,
        @SerializedName("weathercode")
        val weathercode: String
    )
}