package co.develhope.meteoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.develhope.meteoapp.data.domainmodel.LocationInfo
import co.develhope.meteoapp.network.GeocodingNetworkObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class LocationSearchEvent {
    data class SearchCity(val city: String) : LocationSearchEvent()
}

sealed class LocationResult{
    data class Success (val data: List<LocationInfo>): LocationResult()
    data class Error (val e: Exception): LocationResult()
}

class SearchScreenViewModel: ViewModel() {
    private var _locationResult = MutableLiveData<LocationResult>()
    val locationResult: LiveData<LocationResult>
        get() = _locationResult

    fun send(event: LocationSearchEvent) =
        when (event) {
            is LocationSearchEvent.SearchCity -> getLocationResultData(event.city)
        }

    fun getLocationResultData(city: String){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _locationResult.value = LocationResult.Success(GeocodingNetworkObject.getLocationInfo(city = city))
            } catch (e: Exception){
                _locationResult.value = LocationResult.Error(e)
            }
        }
    }
}