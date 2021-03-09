package com.example.flightaflokkat.flightlist

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.flightaflokkat.models.FlightModel
import com.example.flightaflokkat.R
import com.example.flightstats.Utils

/**
 * Created by sergio on 3/9/21
 * All rights reserved GoodBarber
 */
class FlightInfoCell : LinearLayout {

    lateinit var depDateTextView: TextView
    lateinit var depAirportTextView: TextView
    lateinit var depHourTextView: TextView
    lateinit var arrDateTextView: TextView
    lateinit var arrAirportTextView: TextView
    lateinit var arrHourTextView: TextView
    lateinit var flightDurationTextView: TextView
    lateinit var flightNameTextView: TextView


    constructor(context: Context?) : super(context) {
        initLayout()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initLayout()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initLayout()
    }

    private fun bindViews() {
        // make the find view by ids for your view
        depDateTextView = findViewById(R.id.depDateTextView)
        depHourTextView = findViewById(R.id.depHourTextView)
        depAirportTextView = findViewById(R.id.depAirportView)
        arrDateTextView = findViewById(R.id.arrDateTextView)
        arrHourTextView = findViewById(R.id.arrHourTextView)
        arrAirportTextView = findViewById(R.id.arrAirportTextView)
        flightDurationTextView = findViewById(R.id.flightDurationTextView)
        flightNameTextView = findViewById(R.id.flightNumberTextView)
    }

    //CACA: c'est pas bien de traiter de la donneé dans une vue
    fun bindData(flight: FlightModel) {
        //fill your views
        //flight.name ->  TextView
        depDateTextView.text = Utils.timestampToString(flight.firstSeen * 1000L)
        depAirportTextView.text = flight.estDepartureAirport
        depHourTextView.text = Utils.timestampToHourMinute(flight.firstSeen * 1000L)
        arrDateTextView.text = Utils.timestampToString(flight.lastSeen * 1000L)
        arrAirportTextView.text = flight.estArrivalAirport
        arrHourTextView.text = Utils.timestampToHourMinute(flight.lastSeen * 1000L)
        flightDurationTextView.text = Utils.formatFlightDuration(flight.lastSeen - flight.firstSeen)
        flightNameTextView.text = "N°${flight.callsign}"
    }

    private fun initLayout() {
        LayoutInflater.from(context).inflate(R.layout.flight_cell, this, true)
        bindViews()
    }

}