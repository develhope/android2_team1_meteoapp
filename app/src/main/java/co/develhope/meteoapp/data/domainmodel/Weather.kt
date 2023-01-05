package co.develhope.meteoapp.data.domainmodel

enum class Weather {

    SUNNY, CLOUDY, RAINY, FOGGY, TEMPEST, WINDY, HEAVYRAIN

}

fun Int.toWeather(): Weather {
    return when (this) {
        0  -> Weather.SUNNY
        1, 2, 3 -> Weather.CLOUDY
        in 45..48 -> Weather.FOGGY
        in 51..77 -> Weather.RAINY
        80, 81, 82 -> Weather.HEAVYRAIN
        in 95..99 -> Weather.HEAVYRAIN
        else -> Weather.SUNNY
    }
}