package co.develhope.meteoapp.data.domainmodel

data class LocationInfo(
    val city: String,
    val region: String,
    val country: String,
    val population: Int,
    val latitude: Double,
    val longitude: Double
)
