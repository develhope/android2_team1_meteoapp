package co.develhope.meteoapp.network

import co.develhope.meteoapp.TodaySummary
import co.develhope.meteoapp.dto.WeeklySummaryDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApiService {
    @GET("v1/forecast?")
    suspend fun getHourlyForecastForASpecificDay(
        @Query("latitude") latitude: Double = 41.8955,
        @Query("longitude") longitude: Double = 12.4823,
        @Query("hourly") hourly: List<String> = listOf("temperature_2m", "rain", "showers", "snowfall", "weathercode", "windspeed_10m"),
        @Query("current_weather") current_weather: Boolean = true,
        @Query("timezone") timezone : String = "auto",
        @Query("start_date") start_date : String = "2022-10-03",
        @Query("end_date") end_date : String = "2022-10-03"
    ): Response<TodaySummary>


    @GET("v1/forecast?")
    suspend fun getWeeklyForecast (
        @Query("latitude") latitude: Double = 41.8955,
        @Query("longitude") longitude: Double = 12.4823,
        @Query("daily") daily: List<String> = listOf("weathercode", "temperature_2m_max", "temperature_2m_min", "sunrise", "sunset", "precipitation_sum", "rain_sum"),
        @Query("current_weather") current_weather: Boolean = true,
        @Query("timezone") timezone: String = "auto"
    ) : Response<WeeklySummaryDTO>
}