package co.develhope.meteoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.databinding.FragmentTodayScreenBinding
import org.threeten.bp.OffsetDateTime


class TodayScreen : Fragment() {
    private var bindingTodayScreen: FragmentTodayScreenBinding? = null
    private val binding get() = bindingTodayScreen!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingTodayScreen = FragmentTodayScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterInstanceTodayScreen()

        binding.TodayFullDate.text = getString(
            R.string.rv_tv_day_month_date,
            ForecastInfoObject.setDayOfWeek(OffsetDateTime.now().dayOfWeek.name),
            OffsetDateTime.now().dayOfMonth,
            ForecastInfoObject.setMonthName(OffsetDateTime.now().month.name)
        )


    }

    fun adapterInstanceTodayScreen(){
        val adapter = TodayScreenAdapter(ForecastInfoObject.getTodayWeatherList())
        binding.todayRecyclerViewItem.adapter = adapter
        binding.todayRecyclerViewItem.layoutManager = LinearLayoutManager(context)
    }
}