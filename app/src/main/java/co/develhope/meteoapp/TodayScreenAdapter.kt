package co.develhope.meteoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.databinding.TodayRecyclerViewItemBinding

class TodayScreenAdapter(private val todayList: List<TodayCardInfo>) :
    RecyclerView.Adapter<TodayScreenAdapter.TodayScreenAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayScreenAdapterViewHolder {
        val binding = TodayRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodayScreenAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodayScreenAdapterViewHolder, position: Int){
        with(holder){
            binding.todayHour.text = holder.itemView.context.getString(R.string.rv_tv_hour, todayList[position].date.hour)
            binding.todayIcon.setImageResource(ForecastInfoObject.setIcon(todayList[position].weather))
            binding.todayTemperature.text = todayList[position].temperature.toString()
            binding.todayPercentage.text = todayList[position].rainfall.toString()
        }
    }

    override fun getItemCount(): Int {
        return todayList.size
    }

    inner class TodayScreenAdapterViewHolder(val binding: TodayRecyclerViewItemBinding)
        :RecyclerView.ViewHolder(binding.root)
}