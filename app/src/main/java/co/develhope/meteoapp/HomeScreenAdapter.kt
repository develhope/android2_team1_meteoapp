package co.develhope.meteoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.databinding.Next5daysHomeScreenItemBinding
import co.develhope.meteoapp.databinding.TitleHomeScreenItemBinding
import co.develhope.meteoapp.databinding.WeeklyForecastItemBinding
import co.develhope.meteoapp.ui.adapter.HomeScreenItem
import org.threeten.bp.OffsetDateTime

class HomeScreenAdapter(
    private val list: List<HomeScreenItem>,
    private val clickListener: OnItemClickListenerInterface
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_TITLE_HOME_SCREEN = 0
    private val TYPE_WEEKLY_FORECAST_CARDVIEW = 1
    private val TYPE_NEXT_5_DAYS = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_TITLE_HOME_SCREEN -> TitleViewHolder(
                TitleHomeScreenItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            TYPE_WEEKLY_FORECAST_CARDVIEW -> WeeklyForecastViewHolder(
                WeeklyForecastItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            TYPE_NEXT_5_DAYS -> Next5DaysViewHolder(
                Next5daysHomeScreenItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw java.lang.IllegalArgumentException("Invalid View Type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TitleViewHolder -> holder.bind(list[position] as HomeScreenItem.Title)
            is WeeklyForecastViewHolder -> holder.bind(
                list[position] as HomeScreenItem.ForecastDetails,
                clickListener
            )
            is Next5DaysViewHolder -> holder.bind(list[position] as HomeScreenItem.SubTitle)

        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is HomeScreenItem.Title -> TYPE_TITLE_HOME_SCREEN
            is HomeScreenItem.ForecastDetails -> TYPE_WEEKLY_FORECAST_CARDVIEW
            is HomeScreenItem.SubTitle -> TYPE_NEXT_5_DAYS
        }
    }

    class TitleViewHolder(val titleBinding: TitleHomeScreenItemBinding) :
        RecyclerView.ViewHolder(titleBinding.root) {
        fun bind(title: HomeScreenItem.Title) {
            titleBinding.tvHomeTitle.text =
                itemView.context.getString(R.string.rv_title, title.city, title.region)
        }
    }

    class WeeklyForecastViewHolder(val weeklyBinding: WeeklyForecastItemBinding) :
        RecyclerView.ViewHolder(weeklyBinding.root) {

        fun bind(
            weeklyForecast: HomeScreenItem.ForecastDetails,
            clickListener: OnItemClickListenerInterface
        ) {
            itemView.setOnClickListener {
                clickListener.onItemClicked(weeklyForecast, weeklyForecast.info.date)

            }
            weeklyBinding.dateHomeScreen.text = itemView.context.getString(
                R.string.rv_tv_date,
                weeklyForecast.info.date.dayOfMonth,
                weeklyForecast.info.date.monthValue
            )
            weeklyBinding.dayCard.text =
                ForecastInfoObject.setDayOfWeek(weeklyForecast.info.date.dayOfWeek.name)
            weeklyBinding.iconHomeScreenRecyclerView.setImageResource(
                ForecastInfoObject.setIcon(
                    weeklyForecast.info.weather
                )
            )
            weeklyBinding.maxTempCard.text = itemView.context.getString(
                R.string.rv_tv_max_temp_card,
                weeklyForecast.info.maxTemperature
            )
            weeklyBinding.minTempCard.text = itemView.context.getString(
                R.string.rv_tv_min_temp_card,
                weeklyForecast.info.minTemperature
            )
            weeklyBinding.precipitationHomeScreenRecyclerView.text = itemView.context.getString(
                R.string.rv_tv_precip_percentage,
                weeklyForecast.info.rainfall
            )
            weeklyBinding.windHomeScreenRecyclerView.text =
                itemView.context.getString(R.string.rv_tv_wind, weeklyForecast.info.wind)

        }
    }

    class Next5DaysViewHolder(val next5DaysBinding: Next5daysHomeScreenItemBinding) :
        RecyclerView.ViewHolder(next5DaysBinding.root) {
        fun bind(next5Days: HomeScreenItem.SubTitle) {
            next5DaysBinding.tvNextDays.text =
                itemView.context.getString(R.string.rv_next_5_days, next5Days.next5Days)
        }
    }
}

interface OnItemClickListenerInterface {

    fun onItemClicked(forecastDetails: HomeScreenItem.ForecastDetails, position: OffsetDateTime)

}