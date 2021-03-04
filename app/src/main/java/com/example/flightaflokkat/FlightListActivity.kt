package com.example.flightaflokkat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.flightstats.Utils

class FlightListActivity : AppCompatActivity(), RequestsManager.RequestListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_list)

        val begin = intent.getLongExtra("BEGIN", 0)
        val end = intent.getLongExtra("END", 0)
        val isArrival = intent.getBooleanExtra("IS_ARRIVAL", false)
        val airportIcao = intent.getStringExtra("AIRPORT_ICAO")

        var url = "https://opensky-network.org/api/flights/"
        if (isArrival) {
            url += "arrival"
        } else {
            url += "departure"
        }
        url+= "?begin=$begin&end=$end&airport=$airportIcao"

        FlightListAsyncTask(this).execute(url)
    }

    override fun onRequestSuccess(result: String?) {
        val flightList= Utils.getFlightListFromJson(result!!)

        findViewById<TextView>(R.id.textView).text = flightList.toString()



    }

    override fun onRequestFailed() {
        findViewById<TextView>(R.id.textView).text = "Tu t'es planté"
    }
}