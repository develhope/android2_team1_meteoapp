package co.develhope.meteoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val dayCard: TextView
    val minTempCard: TextView
    val maxTempCard: TextView
    val dateHomeScreen: TextView
    val iconHomeScreenRecyclerView: ImageView
    val precipitationHomeScreenRecyclerView: TextView
    val windHomeScreenRecyclerView: TextView

    init {
        dayCard = view.findViewById(R.id.dayCard)
        minTempCard = view.findViewById(R.id.minTempCard)
        maxTempCard = view.findViewById(R.id.maxTempCard)
        dateHomeScreen = view.findViewById(R.id.dateHomeScreen)
        iconHomeScreenRecyclerView = view.findViewById(R.id.iconHomeScreenRecyclerView)
        precipitationHomeScreenRecyclerView =
            view.findViewById(R.id.precipitationHomeScreenRecyclerView)
        windHomeScreenRecyclerView = view.findViewById(R.id.windHomeScreenRecyclerView)
    }
}

class WeatherAdapter(val weather: List<CardInfo>) : RecyclerView.Adapter<WeatherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val weatherView = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_screen_recycler_view, parent, false)
        return WeatherViewHolder(weatherView)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.dayCard.text = weather[position].date.dayOfWeek.toString()
        holder.minTempCard.text = holder.itemView.context.getString(
            R.string.minTempCard,
            weather[position].minTemperature
        )
        holder.maxTempCard.text = holder.itemView.context.getString(
            R.string.maxTempCard,
            weather[position].maxTemperature
        )
        holder.dateHomeScreen.text = holder.itemView.context.getString(
            R.string.dateHomeScreen,
            weather[position].date.dayOfMonth,
            weather[position].date.monthValue
        )
        holder.precipitationHomeScreenRecyclerView.text = holder.itemView.context.getString(
            R.string.precipitationPercentage,
            weather[position].rainfall
        )
        holder.windHomeScreenRecyclerView.text =
            holder.itemView.context.getString(R.string.kmhWind, weather[position].wind)
        holder.iconHomeScreenRecyclerView.setImageResource(ForecastInfoObject.setIcon(weather[position].weather))
    }

    override fun getItemCount(): Int {
        return weather.size
    }

}