package co.develhope.meteoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import co.develhope.meteoapp.databinding.FragmentHomeScreenBinding
import org.threeten.bp.OffsetDateTime

class HomeScreen : Fragment() {
    private var bindingHomeScreen: FragmentHomeScreenBinding? = null
    private val binding get() = bindingHomeScreen!!
    private val adapterList by lazy { HomeScreenAdapter() }


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
    }

    fun adapterInstanceHomeScreen(){
        val itemList = ForecastInfoObject.getHomeScreenItem()
        adapterList.updateList(itemList)
        binding.weatherHomeScreenList.apply {
            layoutManager = LinearLayoutManager(this@HomeScreen.context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterList
        }
    }
}

