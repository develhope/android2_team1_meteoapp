package co.develhope.meteoapp


import android.content.Context
import co.develhope.meteoapp.data.domainmodel.CardInfo
import co.develhope.meteoapp.data.domainmodel.Weather



object ForecastInfoObject {

    fun setIcon(weather: Weather): Int {
        return when (weather) {
            Weather.SUNNY -> R.drawable.sun
            Weather.FOGGY -> R.drawable.cloud
            Weather.TEMPEST -> R.drawable.rain_cloud_sun
            Weather.RAINY -> R.drawable.rain_cloud_sun
            Weather.HEAVYRAIN -> R.drawable.rain_cloud_sun
            Weather.WINDY -> R.drawable.sun_cloud
            Weather.CLOUDY -> R.drawable.cloud
        }
    }

    fun setDayOfWeek(dayOfWeek: String): String {
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


    private var selectedInfo: CardInfo? = null
    fun saveSelectedCardInfo(info: CardInfo) {
        selectedInfo = info
    }

    fun getSelectedCardInfo(): CardInfo? = selectedInfo


    private var selectedTodayInfo: CardInfo? = null
    fun saveSelectedTodayInfo(todayInfo: CardInfo) {
        selectedTodayInfo = todayInfo
    }

    fun getSelectedTodayInfo(position: Int): CardInfo? {
        return when (position) {
            0 -> selectedTodayInfo
            1 -> selectedTomorrowInfo
            else -> selectedTodayInfo
        }
    }


    private var selectedTomorrowInfo: CardInfo? = null
    fun saveSelectedTomorrowInfo(tomorrowInfo: CardInfo) {
        selectedTomorrowInfo = tomorrowInfo
    }

}