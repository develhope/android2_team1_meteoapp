package co.develhope.meteoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.OffsetDateTime

class TodayScreenViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val todayHour: TextView
    val todayIcon: ImageView
    val todayTemperature: TextView
    val todayPercentage: TextView

    init {

        todayHour = view.findViewById(R.id.todayHour)
        todayIcon = view.findViewById(R.id.todayIcon)
        todayTemperature = view.findViewById(R.id.todayTemperature)
        todayPercentage = view.findViewById(R.id.todayPercentage)

    }

}


class TodayScreenAdapter(val todayList: List<TodayCardInfo>) :
    RecyclerView.Adapter<TodayScreenViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayScreenViewHolder {
        val todayScreenView = LayoutInflater.from(parent.context)
            .inflate(R.layout.today_recycler_view_item, parent, false)
        return TodayScreenViewHolder(todayScreenView)
    }

    override fun onBindViewHolder(holder: TodayScreenViewHolder, position: Int) {

        holder.todayHour.text =
            holder.itemView.context.getString(R.string.rv_tv_hour, todayList[position].date.hour)
        holder.todayTemperature.text = todayList[position].temperature.toString()
        holder.todayPercentage.text = todayList[position].rainfall.toString()
        holder.todayIcon.setImageResource(ForecastInfoObject.setIcon(todayList[position].weather))

    }

    override fun getItemCount(): Int {
        return todayList.size
    }


}