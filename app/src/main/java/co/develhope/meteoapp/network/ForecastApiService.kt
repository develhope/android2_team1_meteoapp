package co.develhope.meteoapp.network

import co.develhope.meteoapp.TodaySummaryDTO
import co.develhope.meteoapp.dto.WeeklySummaryDTO
import co.develhope.meteoapp.prefs
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApiService {
    @GET("v1/forecast?")
    suspend fun getHourlyForecastForASpecificDay(
        @Query("latitude") latitude: Double = prefs.latidudePref.toDouble(),
        @Query("longitude") longitude: Double = prefs.longitudePref.toDouble(),
        @Query("hourly") hourly: List<String> = listOf(
            "temperature_2m",
            "rain",
            "showers",
            "snowfall",
            "weathercode",
            "windspeed_10m",
            "relativehumidity_2m",
            "apparent_temperature",
            "cloudcover",
            "winddirection_10m"
        ),
        @Query("current_weather") current_weather: Boolean = true,

        @Query("timezone") timezone : String = "auto",
        @Query("start_date") start_date : String,
        @Query("end_date") end_date : String

    ): Response<TodaySummaryDTO>

    @GET("v1/forecast?")
    suspend fun getWeeklyForecast(
        @Query("latitude") latitude: Double = prefs.latidudePref.toDouble(),
        @Query("longitude") longitude: Double = prefs.longitudePref.toDouble(),
        @Query("daily") daily: List<String> = listOf(
            "weathercode",
            "temperature_2m_max",
            "temperature_2m_min",
            "sunrise",
            "sunset",
            "precipitation_sum",
            "rain_sum",
            "windspeed_10m_max"
        ),
        @Query("current_weather") current_weather: Boolean = true,
        @Query("timezone") timezone: String = "auto"
    ): Response<WeeklySummaryDTO>
}