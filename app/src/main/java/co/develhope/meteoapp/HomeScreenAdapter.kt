package co.develhope.meteoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.ForecastInfoObject.TYPE_NEXT_5_DAYS
import co.develhope.meteoapp.ForecastInfoObject.TYPE_TITLE_HOME_SCREEN
import co.develhope.meteoapp.ForecastInfoObject.TYPE_WEEKLY_FORECAST_CARDVIEW
import co.develhope.meteoapp.databinding.Next5daysHomeScreenItemBinding
import co.develhope.meteoapp.databinding.TitleHomeScreenItemBinding
import co.develhope.meteoapp.databinding.WeeklyForecastItemBinding
import co.develhope.meteoapp.ui.adapter.HomeScreenItem

class HomeScreenAdapter(private val list: List<HomeScreenItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class TitleViewHolder(val titleBinding: TitleHomeScreenItemBinding) :
        RecyclerView.ViewHolder(titleBinding.root) {
        fun bind(title: HomeScreenItem.TitleHomeScreen) {
            titleBinding.tvHomeTitle.text =
                itemView.context.getString(R.string.rv_title, title.city, title.region)
        }
    }

    class WeeklyForecastViewHolder(val weeklyBinding: WeeklyForecastItemBinding) :
        RecyclerView.ViewHolder(weeklyBinding.root) {
        fun bind(weeklyForecast: HomeScreenItem.CardInfo) {
            weeklyBinding.dateHomeScreen.text = itemView.context.getString(
                R.string.rv_tv_date,
                weeklyForecast.date.dayOfMonth,
                weeklyForecast.date.monthValue
            )
            weeklyBinding.dayCard.text =
                ForecastInfoObject.setDayOfWeek(weeklyForecast.date.dayOfWeek.name)
            weeklyBinding.iconHomeScreenRecyclerView.setImageResource(
                ForecastInfoObject.setIcon(
                    weeklyForecast.weather
                )
            )
            weeklyBinding.maxTempCard.text = itemView.context.getString(
                R.string.rv_tv_max_temp_card,
                weeklyForecast.maxTemperature
            )
            weeklyBinding.minTempCard.text = itemView.context.getString(
                R.string.rv_tv_min_temp_card,
                weeklyForecast.minTemperature
            )
            weeklyBinding.precipitationHomeScreenRecyclerView.text = itemView.context.getString(
                R.string.rv_tv_precip_percentage,
                weeklyForecast.rainfall
            )
            weeklyBinding.windHomeScreenRecyclerView.text =
                itemView.context.getString(R.string.rv_tv_wind, weeklyForecast.wind)

        }
    }

    class Next5DaysViewHolder(val next5DaysBinding: Next5daysHomeScreenItemBinding) :
        RecyclerView.ViewHolder(next5DaysBinding.root) {
        fun bind(next5Days: HomeScreenItem.Next5DaysHomeScreen) {
            next5DaysBinding.tvNextDays.text =
                itemView.context.getString(R.string.rv_next_5_days, next5Days.next5Days)
        }
    }

    private val itemList = arrayListOf<Any>()


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
            is TitleViewHolder -> holder.bind(itemList[position] as HomeScreenItem.TitleHomeScreen)
            is WeeklyForecastViewHolder -> holder.bind(itemList[position] as HomeScreenItem.CardInfo)
            is Next5DaysViewHolder -> holder.bind(itemList[position] as HomeScreenItem.Next5DaysHomeScreen)

        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
            is HomeScreenItem.TitleHomeScreen -> TYPE_TITLE_HOME_SCREEN
            is HomeScreenItem.CardInfo -> TYPE_WEEKLY_FORECAST_CARDVIEW
            is HomeScreenItem.Next5DaysHomeScreen -> TYPE_NEXT_5_DAYS
            else -> throw java.lang.IllegalArgumentException("Invalid Item")
        }
    }

    fun updateList(updateList: List<Any>) {
        itemList.clear()
        itemList.addAll(updateList)
        notifyDataSetChanged()
    }
}