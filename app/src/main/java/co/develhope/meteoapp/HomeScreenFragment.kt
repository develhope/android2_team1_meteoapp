package co.develhope.meteoapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.data.domainmodel.CardInfo
import co.develhope.meteoapp.databinding.FragmentHomeScreenBinding
import co.develhope.meteoapp.network.NetworkObject
import co.develhope.meteoapp.ui.adapter.HomeScreenItem
import kotlinx.coroutines.launch

class HomeScreenFragment : Fragment() {
    private var bindingHomeScreen: FragmentHomeScreenBinding? = null
    private val binding get() = bindingHomeScreen!!
    private val viewModel: HomeScreenViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingHomeScreen = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeRepo()
        viewModel.retrieveRepos()

    }

    private fun observeRepo (){
        viewModel.listOfForeCasts.observe(viewLifecycleOwner) {
            setupUi(it)
        }
    }

    private fun setupUi(forecastList: List<CardInfo>) {
        forecastList.sortedBy { it.date }
        val itemsToShow: List<HomeScreenItem> = getItemsToShow(forecastList.toMutableList())
        val homeScreenAdapter: HomeScreenAdapter = HomeScreenAdapter(itemsToShow,
            clickListener = object : OnItemClickListenerInterface {
                override fun onItemClicked(forecastDetails: HomeScreenItem.ForecastDetails) {
                    replaceFragment(TodayScreenFragment())
                }
            })
        binding.weatherHomeScreenList.apply {
            layoutManager =
                LinearLayoutManager(
                    this@HomeScreenFragment.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = homeScreenAdapter
        }
    }

    private fun replaceFragment(todayScreenFragment: TodayScreenFragment) {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, todayScreenFragment)
        fragmentTransaction.commit()

    }

    private fun getItemsToShow(forecastList: MutableList<CardInfo>): List<HomeScreenItem> {
        val homeScreenList = arrayListOf<HomeScreenItem>()
        homeScreenList.add(HomeScreenItem.Title("Rome", "Lazio"))
        homeScreenList.add(HomeScreenItem.ForecastDetails(forecastList.first()))
        homeScreenList.add(HomeScreenItem.SubTitle("Next 5 Days"))
        forecastList.removeFirst()
        forecastList.removeLast()
        homeScreenList.addAll(
            forecastList.map {
                HomeScreenItem.ForecastDetails(it)
            }
        )
        return homeScreenList
    }
}
