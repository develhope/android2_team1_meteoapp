package co.develhope.meteoapp.ui.adapter

import co.develhope.meteoapp.data.domainmodel.CardInfo


sealed class HomeScreenItem {
    data class ForecastDetails(
        val info: CardInfo
    ) : HomeScreenItem()

    data class Title(val city: String, val region: String) : HomeScreenItem()

    data class SubTitle(val next5Days: String) : HomeScreenItem()
}
