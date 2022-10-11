package co.develhope.meteoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
//import co.develhope.meteoapp.ForecastInfoObject.setIcon
import co.develhope.meteoapp.databinding.FragmentHomeScreenBinding
import org.threeten.bp.OffsetDateTime
//import org.threeten.bp.format.DateTimeFormatter


class HomeScreen : Fragment() {
    private var bindingHomeScreen: FragmentHomeScreenBinding? = null
    private val binding get() = bindingHomeScreen!!

    //Calendar.DAY_OF_MONTH.plus(1), Calendar.MONTH
    //val dayOfMonth = Calendar.DAY_OF_MONTH.plus(1)
    //val month = Calendar.MONTH
    //resources.getString(R.string.tomorrow_date, dayOfMonth.toString(), month.toString()


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
        val adapter = WeatherAdapter(ForecastInfoObject.getWeatherList())
        binding.weatherHomeScreenList.adapter = adapter
        binding.weatherHomeScreenList.layoutManager = LinearLayoutManager(context)

        binding.dayCard.text = todayHomeScreenDO.date.dayOfWeek.toString()
        binding.minTempCard.text = getString(R.string.minTempCard, todayHomeScreenDO.minTemperature)
        binding.maxTempCard.text = getString(R.string.maxTempCard, todayHomeScreenDO.maxTemperature)
        binding.dateHomeScreen.text = getString(
            R.string.dateHomeScreen,
            todayHomeScreenDO.date.dayOfMonth,
            todayHomeScreenDO.date.monthValue
        )
        binding.precipitationHomeScreenRecyclerView.text = getString(R.string.precipitationPercentage,todayHomeScreenDO.rainfall)
        binding.windHomeScreenRecyclerView.text = getString(R.string.kmhWind,todayHomeScreenDO.wind)
        binding.iconHomeScreenRecyclerView.setImageResource(
            ForecastInfoObject.setIcon(
                todayHomeScreenDO.weather
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

