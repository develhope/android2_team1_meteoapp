package co.develhope.meteoapp.data.domainmodel

enum class WindDirection {
    N, NE, E, SE, S, SW, W, NW
}

fun Int.getWindDirection(): WindDirection {
    return when (this) {
        in 0..29 -> WindDirection.N
        in 30..59 -> WindDirection.NE
        in 60..119 -> WindDirection.E
        in 120..149 -> WindDirection.SE
        in 150..209 -> WindDirection.S
        in 210..239 -> WindDirection.SW
        in 240..299 -> WindDirection.W
        in 300..329 -> WindDirection.NW
        else -> WindDirection.N
    }
}