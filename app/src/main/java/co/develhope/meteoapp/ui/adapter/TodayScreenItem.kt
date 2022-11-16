package co.develhope.meteoapp.ui.adapter

import co.develhope.meteoapp.data.domainmodel.TodayCardInfo
import org.threeten.bp.OffsetDateTime


sealed class TodayScreenItem {

    data class Title(
        val date: OffsetDateTime,
        val city: String,
        val region: String
    ) : TodayScreenItem()

    data class HourlyForecastDetails(
        val todayCardInfo: TodayCardInfo
    ) : TodayScreenItem()


}