package co.develhope.meteoapp

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.develhope.meteoapp.data.domainmodel.TodayCardInfo
import co.develhope.meteoapp.network.NetworkObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch
import org.threeten.bp.format.DateTimeFormatter

sealed class HourlyForecastResult() {
    data class Success(val data: MutableList<TodayCardInfo>) : HourlyForecastResult()
    data class Error(val e: Exception) : HourlyForecastResult()
    object Loading : HourlyForecastResult()
}

class TodayScreenViewModel : ViewModel() {

    private var _hourlyForecastResult = MutableLiveData<HourlyForecastResult>()
    val hourlyForecastResult: LiveData<HourlyForecastResult>
        get() = _hourlyForecastResult


    fun retrieveRepos() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val selectedInfo = ForecastInfoObject.getSelectedCardInfo()
                if (selectedInfo != null) {
                    _hourlyForecastResult.value = HourlyForecastResult.Success(
                        NetworkObject.getHourlyForecastForASpecificDay(
                            selectedInfo.date.format(DateTimeFormatter.ISO_LOCAL_DATE),
                            selectedInfo.date.format(DateTimeFormatter.ISO_LOCAL_DATE)
                        ).toMutableList()
                    )
                } else {
                    _hourlyForecastResult.value = HourlyForecastResult.Error(java.lang.Exception())
                }

            } catch (e: Exception) {
                e.printStackTrace()
                _hourlyForecastResult.value = HourlyForecastResult.Error(e)
                Log.d("ForecastLog", e.toString())
            }
        }
    }
}