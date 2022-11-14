package co.develhope.meteoapp.data.domainmodel

enum class Weather {

    SUNNY, CLOUDY, RAINY, FOGGY, TEMPEST, WINDY, HEAVYRAIN

}

fun Int.toWeather(): Weather{
    return when (this) {
        1 -> Weather.SUNNY
        else -> Weather.HEAVYRAIN
    }
}