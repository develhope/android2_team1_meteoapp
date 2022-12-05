package co.develhope.meteoapp.network

import co.develhope.meteoapp.ForecastInfoObject
import co.develhope.meteoapp.TodaySummaryDTO
import co.develhope.meteoapp.dto.WeeklySummaryDTO
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApiService {
    @GET("v1/forecast?")
    suspend fun getHourlyForecastForASpecificDay(
        @Query("latitude") latitude: Double = 41.8955,
        @Query("longitude") longitude: Double = 12.4823,
        @Query("hourly") hourly: List<String> = listOf("temperature_2m", "rain", "showers", "snowfall", "weathercode", "windspeed_10m", "relativehumidity_2m", "apparent_temperature", "cloudcover", "winddirection_10m"),
        @Query("current_weather") current_weather: Boolean = true,
        @Query("timezone") timezone : String = "auto",
        @Query("start_date") start_date : String = OffsetDateTime.now().plusDays(0).format(DateTimeFormatter.ISO_LOCAL_DATE),
        @Query("end_date") end_date : String = OffsetDateTime.now().plusDays(0).format(DateTimeFormatter.ISO_LOCAL_DATE)
    ): Response<TodaySummaryDTO>


    @GET("v1/forecast?")
    suspend fun getWeeklyForecast (
        @Query("latitude") latitude: Double = 41.8955,
        @Query("longitude") longitude: Double = 12.4823,
        @Query("daily") daily: List<String> = listOf("weathercode", "temperature_2m_max", "temperature_2m_min", "sunrise", "sunset", "precipitation_sum", "rain_sum", "windspeed_10m_max"),
        @Query("current_weather") current_weather: Boolean = true,
        @Query("timezone") timezone: String = "auto"
    ) : Response<WeeklySummaryDTO>
}