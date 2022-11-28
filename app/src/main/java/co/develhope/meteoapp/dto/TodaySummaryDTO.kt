package co.develhope.meteoapp

import co.develhope.meteoapp.data.domainmodel.*
import co.develhope.meteoapp.dto.CurrentWeather
import com.google.gson.annotations.SerializedName
import org.threeten.bp.OffsetDateTime


data class TodaySummaryDTO(
    @SerializedName("current_weather")
    val currentWeather: CurrentWeather,
    @SerializedName("elevation")
    val elevation: Double,
    @SerializedName("generationtime_ms")
    val generationtimeMs: Double,
    @SerializedName("hourly")
    val hourlyDTO: HourlyDTO,
    @SerializedName("hourly_units")
    val hourlyUnits: HourlyUnits,
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
    data class HourlyDTO(
        @SerializedName("rain")
        val rain: List<Double>,
        @SerializedName("showers")
        val showers: List<Double>,
        @SerializedName("snowfall")
        val snowfall: List<Double>,
        @SerializedName("temperature_2m")
        val temperature2m: List<Double>,
        @SerializedName("time")
        val time: List<OffsetDateTime>,
        @SerializedName("weathercode")
        val weathercode: List<Int>,
        @SerializedName("windspeed_10m")
        val windspeed10m: List<Double>,
        @SerializedName("relativehumidity_2m")
        val relativeHumidity: List<Int>,
        @SerializedName("apparent_temperature")
        val apparentTemperature: List<Double>,
        @SerializedName("cloudcover")
        val cloudcover: List<Int>,
        @SerializedName("winddirection_10m")
        val windDirection: List<Int>
    ) {
        fun toDomain(): List<TodayCardInfo> {
            return this.time.mapIndexed { index, date ->
                TodayCardInfo(
                    date = date,
                    weather = this.weathercode.getOrNull(index)?.toWeather() ?: Weather.CLOUDY,
                    temperature = this.temperature2m.getOrNull(index)?.toInt() ?: 0,
                    rainfall = this.rain.getOrNull(index)?.toInt() ?: 0,
                    humidity = this.relativeHumidity.getOrNull(index)?.toInt() ?: 0,
                    perceivedTemperature = this.apparentTemperature.getOrNull(index)?.toInt() ?: 0,
                    wind = this.windspeed10m.getOrNull(index)?.toInt() ?: 0,
                    coverage = this.cloudcover.getOrNull(index)?.toInt() ?: 0,
                    windDirection = this.windDirection.getOrNull(index)?.getWindDirection()
                        ?: WindDirection.N,
                    rain = this.rain.getOrNull(index)?.toInt() ?: 0
                )
            }
        }
    }

    data class HourlyUnits(
        @SerializedName("rain")
        val rain: String,
        @SerializedName("showers")
        val showers: String,
        @SerializedName("snowfall")
        val snowfall: String,
        @SerializedName("temperature_2m")
        val temperature2m: String,
        @SerializedName("time")
        val time: String,
        @SerializedName("weathercode")
        val weathercode: String,
        @SerializedName("windspeed_10m")
        val windspeed10m: String
    )
}

