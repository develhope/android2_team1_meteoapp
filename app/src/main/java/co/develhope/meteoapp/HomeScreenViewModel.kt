package co.develhope.meteoapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.develhope.meteoapp.data.domainmodel.CardInfo
import co.develhope.meteoapp.network.NetworkObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private var _listOfForeCasts = MutableLiveData<List<CardInfo>>()
    val listOfForeCasts: LiveData<List<CardInfo>>
        get() = _listOfForeCasts

    fun retrieveRepos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _listOfForeCasts.value = NetworkObject.getWeeklySummary()

                Log.d("ForecastLog", "weekly: $listOfForeCasts")
                Log.d("ForecastLog", "hourly: ${NetworkObject.getHourlyForecastForASpecificDay()}")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("ForecastLog", e.toString())
            }
        }
    }
}