package com.example.flightaflokkat.flightlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flightaflokkat.R

class FlightListActivity : AppCompatActivity() {

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
            //findViewById<TextView>(R.id.textView).text = it.toString()
            val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
            recyclerView.adapter = RecyclerViewAdapter(it)
            recyclerView.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        })

        viewmodel.getLoadingLiveData().observe(this, Observer {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.INVISIBLE
            }
        })

        viewmodel.search(begin, end, isArrival, airportIcao!!)
    }
}