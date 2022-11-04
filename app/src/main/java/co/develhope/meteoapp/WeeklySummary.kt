package co.develhope.meteoapp

import com.google.gson.annotations.SerializedName


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
)

data class CurrentWeather(
    @SerializedName("temperature")
    val temperature: Double,
    @SerializedName("time")
    val time: String,
    @SerializedName("weathercode")
    val weathercode: Int,
    @SerializedName("winddirection")
    val winddirection: Double,
    @SerializedName("windspeed")
    val windspeed: Double
)

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
)

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