package co.develhope.meteoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.databinding.FragmentHomeScreenBinding

class HomeScreen : Fragment() {
    private var bindingHomeScreen: FragmentHomeScreenBinding? = null
    private val binding get() = bindingHomeScreen!!
    private val homeScreenAdapter by lazy { HomeScreenAdapter() }


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
        val itemList = ForecastInfoObject.getHomeScreenItem()
        homeScreenAdapter.updateList(itemList)
        binding.weatherHomeScreenList.apply {
            layoutManager = LinearLayoutManager(this@HomeScreen.context, LinearLayoutManager.VERTICAL, false)
            adapter = homeScreenAdapter
        }
    }
}

