package co.develhope.meteoapp

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.data.domainmodel.CardInfo
import co.develhope.meteoapp.databinding.FragmentHomeScreenBinding
import co.develhope.meteoapp.ui.adapter.HomeScreenItem
import org.threeten.bp.OffsetDateTime

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
        setPullToRefresh()
    }

    private fun setPullToRefresh() {
        binding.swipeRefreshHomescreen.setOnRefreshListener {
            viewModel.retrieveRepos()
        }
    }

    override fun onResume() {
        viewModel.retrieveRepos()
        super.onResume()
    }

    private fun observeRepo() {
        viewModel.weeklyForecastResult.observe(viewLifecycleOwner) {
            when (it) {
                is WeeklyForecastResult.Error -> {
                    ErrorPageFragmentDialog.show(
                        childFragmentManager,
                    ) { viewModel.retrieveRepos() }
                    if (binding.swipeRefreshHomescreen.isRefreshing) {
                        binding.swipeRefreshHomescreen.isRefreshing = false
                    }
                }
                WeeklyForecastResult.Loading -> Unit
                is WeeklyForecastResult.Success -> {
                    setupUi(it.data)
                    if (binding.swipeRefreshHomescreen.isRefreshing) {
                        binding.swipeRefreshHomescreen.isRefreshing = false
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val vibrator: Vibrator =
                        context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    vibrator.vibrate(80)
                    val exit = QuitAppFragmentDialog()
                    exit.show(childFragmentManager, null)
                }

            })
    }

    private fun setupUi(forecastList: List<CardInfo>) {
        forecastList.sortedBy { it.date }
        val itemsToShow: List<HomeScreenItem> = getItemsToShow(forecastList.toMutableList())
        val homeScreenAdapter: HomeScreenAdapter = HomeScreenAdapter(itemsToShow,
            clickListener = object : OnItemClickListenerInterface {
                override fun onItemClicked(
                    forecastDetails: HomeScreenItem.ForecastDetails,
                    position: OffsetDateTime
                ) {
                    ForecastInfoObject.saveSelectedCardInfo(forecastDetails.info)
                    replaceFragment(TodayScreenFragment())
                    val vibrator: Vibrator =
                        context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    vibrator.vibrate(80)
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
        ForecastInfoObject.saveSelectedTodayInfo(forecastList.first())
        ForecastInfoObject.saveSelectedTomorrowInfo(forecastList[1])
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


