package co.develhope.meteoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.data.domainmodel.CardInfo
import co.develhope.meteoapp.data.domainmodel.Weather
import co.develhope.meteoapp.databinding.FragmentHomeScreenBinding
import co.develhope.meteoapp.ui.adapter.HomeScreenItem
import org.threeten.bp.OffsetDateTime

class HomeScreen : Fragment() {
    private var bindingHomeScreen: FragmentHomeScreenBinding? = null
    private val binding get() = bindingHomeScreen!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingHomeScreen = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi(){
        val forecastList : List<CardInfo> = ForecastInfoObject.getWeatherList()
        val itemsToShow : List<HomeScreenItem> = getItemsToShow(forecastList)
        val homeScreenAdapter : HomeScreenAdapter = HomeScreenAdapter(itemsToShow)
        binding.weatherHomeScreenList.apply {
            layoutManager = LinearLayoutManager(this@HomeScreen.context, LinearLayoutManager.VERTICAL, false)
            adapter = homeScreenAdapter
        }
    }

    private fun getItemsToShow(forecastList: List<CardInfo>): List<HomeScreenItem> {
        val homeScreenList = arrayListOf<HomeScreenItem>()
        //TODO La lista non è giusta, utilizzare forecastList
        homeScreenList.add(HomeScreenItem.Title("Rome", "Lazio"))
        homeScreenList.add(HomeScreenItem.ForecastDetails(OffsetDateTime.now(), 17, 24, 15, 22, Weather.CLOUDY))
        homeScreenList.add(HomeScreenItem.SubTitle("Next 5 Days"))

        return homeScreenList
    }

//    fun getHomeScreenItem(): List<Any>{
//        val homeScreenList = arrayListOf<Any>()
//
//        homeScreenList.add(HomeScreenRecyclerView.TitleHomeScreen("Rome", "Lazio"))
//        homeScreenList.add(HomeScreenRecyclerView.CardInfo(OffsetDateTime.now(), 17, 24, 15, 22, Weather.CLOUDY))
//        homeScreenList.add(HomeScreenRecyclerView.Next5DaysHomeScreen("Next 5 Days"))
//        homeScreenList.add(getWeatherList())
//
//        return homeScreenList
//    }
}

