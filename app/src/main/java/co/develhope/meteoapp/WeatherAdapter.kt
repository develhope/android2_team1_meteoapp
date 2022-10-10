package co.develhope.meteoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.threeten.bp.format.DateTimeFormatter

class WeatherViewHolder (view : View): RecyclerView.ViewHolder (view){
    val dayCard : TextView
    val minTempCard : TextView
    val maxTempCard : TextView
    val dateHomeScreen : TextView
    val iconHomeScreenRecyclerView : ImageView
    val precipitationHomeScreenRecyclerView : TextView
    val windHomeScreenRecyclerView : TextView

    init {
        dayCard = view.findViewById(R.id.dayCard)
        minTempCard = view.findViewById(R.id.minTempCard)
        maxTempCard = view.findViewById(R.id.maxTempCard)
        dateHomeScreen = view.findViewById(R.id.dateHomeScreen)
        iconHomeScreenRecyclerView = view.findViewById(R.id.iconHomeScreenRecyclerView)
        precipitationHomeScreenRecyclerView = view.findViewById(R.id.precipitationHomeScreenRecyclerView)
        windHomeScreenRecyclerView = view.findViewById(R.id.windHomeScreenRecyclerView)
    }
}

class WeatherAdapter(val weather: List<CardInfo>): RecyclerView.Adapter<WeatherViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val weatherView = LayoutInflater.from(parent.context).inflate(R.layout.home_screen_recycler_view, parent, false)
        return WeatherViewHolder(weatherView)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.dayCard.text = weather[position].date.dayOfWeek.toString()
        holder.minTempCard.text = weather[position].minTemperature.toString()
        holder.maxTempCard.text = weather[position].maxTemperature.toString()
        holder.dateHomeScreen.text = weather[position].date.format(DateTimeFormatter.ISO_LOCAL_DATE)
        holder.precipitationHomeScreenRecyclerView.text = weather[position].rainfall.toString()
        holder.windHomeScreenRecyclerView.text = weather[position].wind.toString()
    }

    override fun getItemCount(): Int {
        return weather.size
    }

}