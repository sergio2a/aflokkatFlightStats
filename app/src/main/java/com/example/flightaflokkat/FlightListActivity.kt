package com.example.flightaflokkat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.flightstats.Utils

class FlightListActivity : AppCompatActivity(){

    private lateinit var viewmodel: FlightListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_list)

        viewmodel = ViewModelProvider(this).get(FlightListViewModel::class.java)

        val begin = intent.getLongExtra("BEGIN", 0)
        val end = intent.getLongExtra("END", 0)
        val isArrival = intent.getBooleanExtra("IS_ARRIVAL", false)
        val airportIcao = intent.getStringExtra("AIRPORT_ICAO")

        val progressBar = findViewById<View>(R.id.progressbar)

        viewmodel.getFlightListLiveData().observe(this, Observer {
            findViewById<TextView>(R.id.textView).text = it.toString()
        })

        viewmodel.getLoadingLiveData().observe(this, Observer {
           if(it){
               progressBar.visibility = View.VISIBLE
           }
           else{
               progressBar.visibility = View.INVISIBLE
           }
        })

        viewmodel.search(begin, end, isArrival, airportIcao!!)
    }
}