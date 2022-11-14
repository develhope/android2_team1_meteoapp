package co.develhope.meteoapp.dto


import co.develhope.meteoapp.data.domainmodel.CardInfo
import co.develhope.meteoapp.data.domainmodel.toWeather
import com.google.gson.annotations.SerializedName
import org.threeten.bp.OffsetDateTime


data class WeeklySummaryDTO(
    @SerializedName("current_weather")
    val currentWeather: CurrentWeather,
    @SerializedName("daily")
    val dailyDTO: DailyDTO,
    @SerializedName("daily_units")
    val dailyUnitsDTO: DailyUnitsDTO,
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
    data class DailyDTO(
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
        val time: List<OffsetDateTime>,
        @SerializedName("weathercode")
        val weathercode: List<Int>
    ) {
        fun toDomain(): CardInfo {
            return CardInfo(
                date = time.first(),
                minTemperature = temperature2mMin.first().toInt(),
                maxTemperature = temperature2mMax.first().toInt(),
                rainfall = rainSum.first().toInt(),
                wind = 0,
                weather = weathercode.first().toWeather()
            )
        }
    }

    data class DailyUnitsDTO(
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