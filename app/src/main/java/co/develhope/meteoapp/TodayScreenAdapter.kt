package co.develhope.meteoapp


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.databinding.TitleTodayScreenItemBinding
import co.develhope.meteoapp.databinding.TodayForecastItemBinding
import co.develhope.meteoapp.ui.adapter.TodayScreenItem

class TodayScreenAdapter(val list: List<TodayScreenItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_TITLE_TODAY_SCREEN = 0
    private val TYPE_HOURLY_FORECAST = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {

            TYPE_TITLE_TODAY_SCREEN -> TitleViewHolder(
                TitleTodayScreenItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )

            TYPE_HOURLY_FORECAST -> HourlyForecastViewHolder(
                TodayForecastItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false

                )
            )

            else -> throw java.lang.IllegalArgumentException("Invalid View Type")

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

            is TitleViewHolder -> holder.bind(list[position] as TodayScreenItem.Title)
            is HourlyForecastViewHolder -> holder.bind(list[position] as TodayScreenItem.HourlyForecastDetails)

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is TodayScreenItem.Title -> TYPE_TITLE_TODAY_SCREEN
            is TodayScreenItem.HourlyForecastDetails -> TYPE_HOURLY_FORECAST
        }
    }

    class TitleViewHolder(val titleTodayScreenItemBinding: TitleTodayScreenItemBinding) :
        RecyclerView.ViewHolder(titleTodayScreenItemBinding.root) {
        fun bind(title: TodayScreenItem.Title) {
            titleTodayScreenItemBinding.TodayFullDate.text = itemView.context.getString(
                R.string.cv_tv_calendar_date,
                title.date.dayOfMonth,
                title.date.monthValue,
                title.date.year
            )
        }

    }


    class HourlyForecastViewHolder(val todayForecastItemBinding: TodayForecastItemBinding) :
        RecyclerView.ViewHolder(todayForecastItemBinding.root) {

        fun bind(hourlyForecastDetails: TodayScreenItem.HourlyForecastDetails) {

            todayForecastItemBinding.todayHour.text = itemView.context.getString(
                R.string.rv_tv_hour,
                hourlyForecastDetails.todayCardInfo.date.hour
            )
            todayForecastItemBinding.todayIcon.setImageResource(
                ForecastInfoObject.setIcon(
                    hourlyForecastDetails.todayCardInfo.weather
                )
            )

            todayForecastItemBinding.todayTemperature.text = itemView.context.getString(
                R.string.rv_tv_max_temp_card,
                hourlyForecastDetails.todayCardInfo.temperature
            )

            todayForecastItemBinding.todayPercentage.text = itemView.context.getString(
                R.string.rv_tv_precip_percentage,
                hourlyForecastDetails.todayCardInfo.rainfall
            )

        }


    }
}
