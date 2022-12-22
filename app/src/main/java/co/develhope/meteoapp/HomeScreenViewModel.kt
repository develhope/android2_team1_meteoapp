package co.develhope.meteoapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.develhope.meteoapp.data.domainmodel.CardInfo
import co.develhope.meteoapp.network.GeocodingNetworkObject
import co.develhope.meteoapp.network.NetworkObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class WeeklyForecastResult() {
    data class Success(val data: List<CardInfo>) : WeeklyForecastResult()
    data class Error(val e: Exception) : WeeklyForecastResult()
    object Loading : WeeklyForecastResult()
}

class HomeScreenViewModel : ViewModel() {

    private var _weeklyForecastResult = MutableLiveData<WeeklyForecastResult>()
    val weeklyForecastResult: LiveData<WeeklyForecastResult>
        get() = _weeklyForecastResult

    fun retrieveRepos() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _weeklyForecastResult.value = WeeklyForecastResult.Loading
                _weeklyForecastResult.value =
                    WeeklyForecastResult.Success(NetworkObject.getWeeklySummary())
                Log.d("ForecastLog", "weekly: $weeklyForecastResult")
                Log.d("GeocodingLog", "${GeocodingNetworkObject.getLocationInfo().getOrNull(0)}")
                Log.d("GeocodingLog", "${GeocodingNetworkObject.getLocationInfo().getOrNull(1)}")
                Log.d("GeocodingLog", "${GeocodingNetworkObject.getLocationInfo().getOrNull(2)}")
            } catch (e: Exception) {
                e.printStackTrace()
                _weeklyForecastResult.value = WeeklyForecastResult.Error(e)
                Log.e("ForecastLog", e.toString())
            }
        }
    }
}