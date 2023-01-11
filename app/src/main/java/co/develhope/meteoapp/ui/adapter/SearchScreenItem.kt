package co.develhope.meteoapp.ui.adapter

import co.develhope.meteoapp.data.domainmodel.LocationInfo
import co.develhope.meteoapp.data.domainmodel.TodayCardInfo

sealed class SearchScreenItem{
    data class SearchBar(
        val locationInfo: LocationInfo
    ): SearchScreenItem()

    data class LocationCardview(
        val locationInfo: LocationInfo,
        val cardInfo: TodayCardInfo? = null
    ): SearchScreenItem()
}
