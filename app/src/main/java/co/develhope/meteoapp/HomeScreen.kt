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

    //Calendar.DAY_OF_MONTH.plus(1), Calendar.MONTH
    //val dayOfMonth = Calendar.DAY_OF_MONTH.plus(1)
    //val month = Calendar.MONTH
    //resources.getString(R.string.tomorrow_date, dayOfMonth.toString(), month.toString()


    val weatherList: List<CardInfo> = listOf(
        CardInfo(OffsetDateTime.now().plusDays(1), 18, 22, 10, 20, Weather.CLOUDY),
        CardInfo(OffsetDateTime.now().plusDays(2), 16, 21, 20, 25, Weather.RAINY),
        CardInfo(OffsetDateTime.now().plusDays(3), 15, 20, 80, 24, Weather.HEAVYRAIN),
        CardInfo(OffsetDateTime.now().plusDays(4), 22, 26, 0, 15, Weather.SUNNY),
        CardInfo(OffsetDateTime.now().plusDays(5), 24, 32, 5, 40, Weather.WINDY)
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingHomeScreen = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = WeatherAdapter(weatherList)
        binding.weatherHomeScreenList.adapter = adapter
        binding.weatherHomeScreenList.layoutManager = LinearLayoutManager(context)

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}