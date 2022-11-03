package co.develhope.meteoapp

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.databinding.HomeScreenRecyclerViewBinding

class HomeScreenAdapter(private val weather: List<CardInfo>) : RecyclerView.Adapter<HomeScreenAdapter.HomeScreenAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeScreenAdapterViewHolder {
        val binding = HomeScreenRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeScreenAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeScreenAdapterViewHolder, position: Int) {
        with(holder){
            binding.dayCard.text = ForecastInfoObject.setDayOfWeek(weather[position].date.dayOfWeek.name)
            binding.minTempCard.text = holder.itemView.context.getString(R.string.rv_tv_min_temp_card, weather[position].minTemperature)
            binding.maxTempCard.text = holder.itemView.context.getString(R.string.rv_tv_max_temp_card, weather[position].maxTemperature)
            binding.dateHomeScreen.text = holder.itemView.context.getString(R.string.rv_tv_date, weather[position].date.dayOfMonth, weather[position].date.monthValue)
            binding.precipitationHomeScreenRecyclerView.text = holder.itemView.context.getString(R.string.rv_tv_precip_percentage, weather[position].rainfall)
            binding.windHomeScreenRecyclerView.text = holder.itemView.context.getString(R.string.rv_tv_wind, weather[position].wind)
            binding.iconHomeScreenRecyclerView.setImageResource(ForecastInfoObject.setIcon(weather[position].weather))
        }
    }

    override fun getItemCount(): Int {
        return weather.size
    }

    inner class HomeScreenAdapterViewHolder(val binding: HomeScreenRecyclerViewBinding)
        :RecyclerView.ViewHolder(binding.root)

}