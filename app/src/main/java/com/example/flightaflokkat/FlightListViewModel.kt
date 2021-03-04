package com.example.flightaflokkat

import android.os.Looper
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightstats.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by sergio on 3/4/21
 * All rights reserved GoodBarber
 */
class FlightListViewModel : ViewModel()/*, RequestsManager.RequestListener*/ {
    private val flightListLiveData = MutableLiveData<List<FlightModel>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()

    init {
        isLoadingLiveData.value = false
    }

    fun getFlightListLiveData(): LiveData<List<FlightModel>> {
        return flightListLiveData
    }

    fun getLoadingLiveData(): LiveData<Boolean> {
        return isLoadingLiveData
    }

    fun updateFlightsList(flightList: List<FlightModel>) {
        flightListLiveData.value = flightList
    }

    fun search(begin: Long, end: Long, isArrival: Boolean, airportIcao: String) {

        Log.i("COROUTINES", "ici 1")

        viewModelScope.launch {
            Log.i("COROUTINES", "ici 2")
            Log.i("COROUTINES", (Looper.getMainLooper() == Looper.myLooper()).toString())

            var url = "https://opensky-network.org/api/flights/"
            if (isArrival) {
                url += "arrival"
            } else {
                url += "departure"
            }
            url += "?begin=$begin&end=$end&airport=$airportIcao"

            isLoadingLiveData.value = true
            val result = withContext(Dispatchers.IO) {
                Log.i("COROUTINES", "ici 3")
                Log.i("COROUTINES", (Looper.getMainLooper() == Looper.myLooper()).toString())
                RequestsManager.getSuspended(url, HashMap())
            }

            val flightList = Utils.getFlightListFromJson(result!!)
            flightListLiveData.value = flightList
            isLoadingLiveData.value = false
            Log.i("COROUTINES", "ici 4")
        }

        Log.i("COROUTINES", "ici 5")

        //FlightListAsyncTask(this).execute(url)
    }

/*    override fun onRequestSuccess(result: String?) {
        val flightList = Utils.getFlightListFromJson(result!!)
        flightListLiveData.value = flightList
    }

    override fun onRequestFailed() {
        //TODO
    }*/

}