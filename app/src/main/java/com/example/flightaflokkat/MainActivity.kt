package com.example.flightaflokkat

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.flightstats.Utils
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private val beginCalendar = Calendar.getInstance()
    private val endCalendar = Calendar.getInstance()

    private lateinit var beginDateTexview: TextView
    private lateinit var endDateTexview: TextView

    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        beginDateTexview = findViewById(R.id.dateBeginView)
        endDateTexview = findViewById(R.id.dateEndView)

        //Récupérer aiportlist
        val airportList = Utils.generateAirportList()

        //Générer list de noms d'aeroport
        val airportListName = ArrayList<String>()
        for (airport in airportList) {
            airportListName.add(airport.getFormattedName())
        }

        //Générer arrayadapter
        val arrayAdapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                airportListName
            )

        //Spinner adapter = arrayadapter
        val spinner = findViewById<Spinner>(R.id.airportSpinner)
        spinner.adapter = arrayAdapter

        //show picker on click
        beginDateTexview.setOnClickListener {
            setupAndShowDatePickerDialog(beginDateTexview, beginCalendar)
        }

        endDateTexview.setOnClickListener {
            setupAndShowDatePickerDialog(endDateTexview, endCalendar)
        }

        //update the date to current date on the launch of the activity
        displayDate(beginDateTexview, beginCalendar)
        displayDate(endDateTexview, endCalendar)
    }

    private fun displayDate(textView: TextView, calendar: Calendar) {
        textView.text = dateFormatter.format(calendar.time)
    }

    private fun setupAndShowDatePickerDialog(textView: TextView, calendar: Calendar) {
        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                displayDate(textView, calendar)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}