package co.develhope.meteoapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.data.domainmodel.TodayCardInfo
import co.develhope.meteoapp.databinding.FragmentTodayScreenBinding
import co.develhope.meteoapp.network.NetworkObject
import co.develhope.meteoapp.ui.adapter.TodayScreenItem
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime


class TodayScreenFragment : Fragment() {
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
        setUpUI(ForecastInfoObject.getTodayWeatherList().toMutableList())

        lifecycleScope.launch{
            try {
                val selectedInfo = ForecastInfoObject.getSelectedCardInfo()
                if (selectedInfo != null){
                    val listOfForeCasts = NetworkObject.getHourlyForecastForASpecificDay().toMutableList()
                    setUpUI(listOfForeCasts)
                } else {
                    //TODO
                }

            } catch (e: Exception){
                e.printStackTrace()
                Log.d("ForecastLog", e.toString())
            }
        }

    }

    private fun setUpUI(hourlyForecastList: MutableList<TodayCardInfo>) {
        hourlyForecastList.sortedBy { it.date.hour }
        setHourToShow(hourlyForecastList)

        val itemToShow: List<TodayScreenItem> = getItemToShow(setHourToShow(hourlyForecastList))
        val todayScreenAdapter= TodayScreenAdapter(itemToShow)

        binding.todayRecyclerViewItem.apply {
            layoutManager =
                LinearLayoutManager(
                    this@TodayScreenFragment.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = todayScreenAdapter
        }

    }

    private fun setHourToShow(hourlyForecastList: MutableList<TodayCardInfo>): MutableList<TodayCardInfo> {
        val list: MutableList<TodayCardInfo> = mutableListOf()
        if(OffsetDateTime.now().minute <= 29){
            list.addAll(hourlyForecastList.filter {
                it.date.hour >= OffsetDateTime.now().hour
            })
        } else {
            list.addAll(hourlyForecastList.filter {
                it.date.hour > OffsetDateTime.now().hour
            })
        }
        return list
    }

    private fun getItemToShow(hourlyForecastList: MutableList<TodayCardInfo>): List<TodayScreenItem> {
        val todayScreenList = arrayListOf<TodayScreenItem>()
        todayScreenList.add(
            TodayScreenItem.Title(
                OffsetDateTime.now(),
                "Rome",
                "Lazio"
            )
        )
        todayScreenList.addAll(
            hourlyForecastList.map { TodayScreenItem.HourlyForecastDetails(it) }
        )

        return todayScreenList
    }
}