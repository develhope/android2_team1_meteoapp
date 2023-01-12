package co.develhope.meteoapp


import co.develhope.meteoapp.data.domainmodel.CardInfo
import co.develhope.meteoapp.data.domainmodel.Weather
import java.util.Locale


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

    fun setDayOfWeek(dayOfWeek: String): Int {
        return when (dayOfWeek) {
            "MONDAY" -> R.string.monday
            "TUESDAY" -> R.string.tuesday
            "WEDNESDAY" -> R.string.wednesday
            "THURSDAY" -> R.string.thursday
            "FRIDAY" -> R.string.friday
            "SATURDAY" -> R.string.saturday
            "SUNDAY" -> R.string.sunday
            else -> R.string.error
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

    fun changeGeocodingSearchLanguage(): String{
        return when (Locale.getDefault().displayLanguage.lowercase()){
            "english" -> "en"
            "italian" -> "it"
            else -> "en"
        }
    }

}