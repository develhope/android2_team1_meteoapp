package co.develhope.meteoapp

import android.content.Context
import co.develhope.meteoapp.data.domainmodel.CardInfo
import co.develhope.meteoapp.data.domainmodel.TodayCardInfo
import co.develhope.meteoapp.data.domainmodel.Weather
import com.google.android.material.dialog.MaterialAlertDialogBuilder


object ForecastInfoObject {

    private val weatherList: MutableList<CardInfo> = mutableListOf()

    private val todayWeatherList: MutableList<TodayCardInfo> = mutableListOf()

    fun getWeatherList(): List<CardInfo> {
        return weatherList
    }

    fun getTodayWeatherList(): List<TodayCardInfo> {
        return todayWeatherList
    }

    fun setWeatherList(list: List<CardInfo>) {
        weatherList.clear()
        weatherList.addAll(list)
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

    fun setMonthName(month: String): String {
        return when (month) {
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

    private var selectedInfo: CardInfo? = null
    fun saveSelectedCardInfo(info: CardInfo) {
        selectedInfo = info
    }

    fun getSelectedCardInfo(): CardInfo? = selectedInfo


    private var selectedTodayInfo: CardInfo? = null
    fun saveSelectedTodayInfo(todayInfo: CardInfo) {
        selectedTodayInfo = todayInfo
    }

    fun getSelectedTodayInfo(): CardInfo? = selectedTodayInfo

}