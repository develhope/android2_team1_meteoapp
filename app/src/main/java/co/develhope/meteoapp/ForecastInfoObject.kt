package co.develhope.meteoapp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.threeten.bp.OffsetDateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ForecastInfoObject {

    const val TYPE_TITLE_HOME_SCREEN = 0
    const val TYPE_WEEKLY_FORECAST_CARDVIEW = 1
    const val TYPE_NEXT_5_DAYS = 2

    fun getHomeScreenItem(): ArrayList<Any>{
        val homeScreenList = arrayListOf<Any>()

        homeScreenList.add(HomeScreenRecyclerView.TitleHomeScreen("Rome", "Lazio"))
        homeScreenList.add(HomeScreenRecyclerView.CardInfo(OffsetDateTime.now(), 17, 24, 15, 22, Weather.CLOUDY))
        homeScreenList.add(HomeScreenRecyclerView.Next5DaysHomeScreen("Next 5 Days"))
        homeScreenList.add(getWeatherList())

        return homeScreenList
    }

    private val weatherList: List<Any> = listOf(
        HomeScreenRecyclerView.CardInfo(OffsetDateTime.now().plusDays(1), 18, 22, 10, 20, Weather.CLOUDY),
        HomeScreenRecyclerView.CardInfo(OffsetDateTime.now().plusDays(2), 16, 21, 20, 25, Weather.RAINY),
        HomeScreenRecyclerView.CardInfo(OffsetDateTime.now().plusDays(3), 15, 20, 80, 24, Weather.HEAVYRAIN),
        HomeScreenRecyclerView.CardInfo(OffsetDateTime.now().plusDays(4), 22, 26, 0, 15, Weather.SUNNY),
        HomeScreenRecyclerView.CardInfo(OffsetDateTime.now().plusDays(5), 24, 32, 5, 40, Weather.WINDY)
    )

    private val todayWeatherList: List<TodayCardInfo> = listOf(
        TodayCardInfo(OffsetDateTime.now(), Weather.SUNNY, 30, 0),
        TodayCardInfo(OffsetDateTime.now().plusHours(1), Weather.SUNNY, 30, 0),
        TodayCardInfo(OffsetDateTime.now().plusHours(2), Weather.CLOUDY, 28, 5),
        TodayCardInfo(OffsetDateTime.now().plusHours(3), Weather.SUNNY, 29, 0),
        TodayCardInfo(OffsetDateTime.now().plusHours(4), Weather.SUNNY, 30, 0),
        TodayCardInfo(OffsetDateTime.now().plusHours(5), Weather.SUNNY, 28, 0),
        TodayCardInfo(OffsetDateTime.now().plusHours(6), Weather.CLOUDY, 26, 15),
        TodayCardInfo(OffsetDateTime.now().plusHours(7), Weather.RAINY, 25, 60),
        TodayCardInfo(OffsetDateTime.now().plusHours(8), Weather.RAINY, 25, 80),
        TodayCardInfo(OffsetDateTime.now().plusHours(9), Weather.HEAVYRAIN, 23, 100),
        TodayCardInfo(OffsetDateTime.now().plusHours(10), Weather.HEAVYRAIN, 24, 100),
        TodayCardInfo(OffsetDateTime.now().plusHours(11), Weather.TEMPEST, 23, 100),
        TodayCardInfo(OffsetDateTime.now().plusHours(12), Weather.FOGGY, 26, 98)
    )

    fun getWeatherList(): List<Any> {
        return weatherList
    }

    fun getTodayWeatherList(): List<TodayCardInfo> {
        return todayWeatherList
    }

    fun setIcon(weather: Weather): Int {
        return when (weather) {
            Weather.SUNNY -> R.drawable.sun
            Weather.FOGGY -> R.drawable.sun
            Weather.TEMPEST -> R.drawable.rain_cloud_sun
            Weather.RAINY -> R.drawable.rain_cloud_sun
            Weather.HEAVYRAIN -> R.drawable.rain_cloud_sun
            Weather.WINDY -> R.drawable.sun_cloud
            Weather.CLOUDY -> R.drawable.sun_cloud
        }
    }

    fun setDayOfWeek (dayOfWeek: String): String {
        return when (dayOfWeek) {
            "MONDAY" -> "Monday"
            "TUESDAY" -> "Tuesday"
            "WEDNESDAY" -> "Wednesday"
            "THURSDAY" -> "Thursday"
            "FRIDAY" -> "Friday"
            "SATURDAY" -> "Saturday"
            "SUNDAY" -> "Sunday"
            else -> "error"
        }
    }

    fun setMonthName (month : String): String {
        return when (month){
            "JANUARY" -> "January"
            "FEBRUARY" -> "February"
            "MARCH" -> "March"
            "APRIL" -> "April"
            "MAY" -> "MAY"
            "JUNE" -> "June"
            "JULY" -> "July"
            "AUGUST" -> "August"
            "SEPTEMBER" -> "September"
            "OCTOBER" -> "October"
            "NOVEMBER" -> "November"
            "DECEMBER" -> "December"
            else -> "error"
        }
    }

    val logging = HttpLoggingInterceptor()
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.open-meteo.com")
        .build()

    val service = retrofit.create(HourlyForecastApiService::class.java)

}