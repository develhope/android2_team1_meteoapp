package co.develhope.meteoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.databinding.FragmentHomeScreenBinding
import org.threeten.bp.OffsetDateTime

class HomeScreen : Fragment() {
    private var bindingHomeScreen: FragmentHomeScreenBinding? = null
    private val binding get() = bindingHomeScreen!!


    val todayHomeScreenDO = CardInfo(OffsetDateTime.now(), 18, 22, 10, 20, Weather.CLOUDY)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingHomeScreen = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterInstanceHomeScreen()

        //TODO this should be in the adapter
        binding.dayCard.text = getString(R.string.cv_tv_today)
        binding.minTempCard.text =
            getString(R.string.rv_tv_min_temp_card, todayHomeScreenDO.minTemperature)
        binding.maxTempCard.text =
            getString(R.string.rv_tv_max_temp_card, todayHomeScreenDO.maxTemperature)
        binding.dateHomeScreen.text = getString(
            R.string.rv_tv_date,
            todayHomeScreenDO.date.dayOfMonth,
            todayHomeScreenDO.date.monthValue
        )
        binding.precipitationHomeScreenRecyclerView.text =
            getString(R.string.rv_tv_precip_percentage, todayHomeScreenDO.rainfall)
        binding.windHomeScreenRecyclerView.text =
            getString(R.string.rv_tv_wind, todayHomeScreenDO.wind)
        binding.iconHomeScreenRecyclerView.setImageResource(
            ForecastInfoObject.setIcon(
                todayHomeScreenDO.weather
            )
        )
    }

    fun adapterInstanceHomeScreen(){
        val adapter = WeatherAdapter(ForecastInfoObject.getWeatherList())
        binding.weatherHomeScreenList.adapter = adapter
        binding.weatherHomeScreenList.layoutManager = LinearLayoutManager(context)
    }
}

