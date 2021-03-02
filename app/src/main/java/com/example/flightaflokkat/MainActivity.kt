package com.example.flightaflokkat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.flightstats.Utils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Récupérer aiportlist
        val airportList = Utils.generateAirportList()

        //Générer list de noms d'aeroport
        val airportListName = ArrayList<String>()
        for (airport in airportList) {
            airportListName.add(airport.getFormattedName())
        }

        //Générer arrayadapter
        val arrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, airportListName)

        //Spinner adapter = arrayadapter
        val spinner = findViewById<Spinner>(R.id.airportSpinner)
        spinner.adapter = arrayAdapter


    }
}