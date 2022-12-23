package co.develhope.meteoapp.ui.adapter

import co.develhope.meteoapp.data.domainmodel.CardInfo
import co.develhope.meteoapp.data.domainmodel.LocationInfo

sealed class SearchScreenItem{
    data class SearchBar(
        val locationInfo: LocationInfo
    ): SearchScreenItem()

    data class LocationCardview(
        val locationInfo: LocationInfo,
        val cardInfo: CardInfo
    ): SearchScreenItem()
}
