package co.develhope.meteoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.app.NavUtils
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.data.domainmodel.TodayCardInfo
import co.develhope.meteoapp.databinding.FragmentTodayScreenBinding
import co.develhope.meteoapp.ui.adapter.TodayScreenItem
import org.threeten.bp.OffsetDateTime


class TodayScreenFragment : Fragment() {
    private var bindingTodayScreen: FragmentTodayScreenBinding? = null
    private val binding get() = bindingTodayScreen!!
    private val viewModel: TodayScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingTodayScreen = FragmentTodayScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeRepo()
        viewModel.retrieveRepos()
        setPullToRefresh()
    }

    private fun setPullToRefresh(){
        binding.swipeRefreshTodayscreen.setOnRefreshListener {
            viewModel.retrieveRepos()
        }
    }


    private fun observeRepo() {
        viewModel.hourlyForecastResult.observe(viewLifecycleOwner) {
            when (it) {
                is HourlyForecastResult.Error -> {

                    Toast.makeText(
                        context,
                        "Error",
                        Toast.LENGTH_SHORT
                    ).show()
                    if(binding.swipeRefreshTodayscreen.isRefreshing){
                        binding.swipeRefreshTodayscreen.isRefreshing = false
                    }
                    ErrorPageFragmentDialog.show(
                        childFragmentManager,
                    ){viewModel.retrieveRepos()}

                }
                HourlyForecastResult.Loading -> Unit
                is HourlyForecastResult.Success -> {
                    setUpUI(it.data)
                    if(binding.swipeRefreshTodayscreen.isRefreshing){
                        binding.swipeRefreshTodayscreen.isRefreshing = false
                    }
                }

            }
        }
    }

    private fun setUpUI(hourlyForecastList: MutableList<TodayCardInfo>) {
        hourlyForecastList.sortedBy { it.date.hour }
        val todayScreenAdapter = if (hourlyForecastList[0].date.dayOfYear == OffsetDateTime.now().dayOfYear){
            setHourToShow(hourlyForecastList)
            val itemToShow: List<TodayScreenItem> = getItemToShow(setHourToShow(hourlyForecastList))
            TodayScreenAdapter(itemToShow)
        } else {
            val itemToShow: List<TodayScreenItem> = getItemToShow(hourlyForecastList)
            TodayScreenAdapter(itemToShow)
        }

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
        if (OffsetDateTime.now().minute <= 29) {
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
                ForecastInfoObject.getSelectedCardInfo()?.date ?: OffsetDateTime.now(),
                "Rome",
                "Lazio"
            )
        )
        todayScreenList.addAll(
            hourlyForecastList.map { TodayScreenItem.HourlyForecastDetails(it) }
        )

        return todayScreenList
    }

    //BETA TEST
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val vibrator: Vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    vibrator.vibrate(80)
                    val intent = Intent(context,MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    (activity as MainActivity).startActivity(intent)
                    (activity as MainActivity).overridePendingTransition(com.google.android.material.R.anim.abc_popup_enter, com.google.android.material.R.anim.abc_popup_exit)
                }
            })
    }
}