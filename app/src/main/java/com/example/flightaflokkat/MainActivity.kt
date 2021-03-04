package com.example.flightaflokkat

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.flightstats.Utils
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var beginDateTexview: TextView
    private lateinit var endDateTexview: TextView

    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy")

    private lateinit var viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        beginDateTexview = findViewById(R.id.dateBeginView)
        endDateTexview = findViewById(R.id.dateEndView)

        viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewmodel.getBeginCalendarLiveData().observe(this, Observer {
            displayDate(beginDateTexview, it)
        })

        viewmodel.getEndCalendarLiveData().observe(this, Observer {
            displayDate(endDateTexview, it)
        })


        val spinner = findViewById<Spinner>(R.id.airportSpinner)

        viewmodel.getAirportNamesListLiveData().observe(this, Observer {
            //Générer arrayadapter
            val arrayAdapter =
                ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_spinner_dropdown_item,
                    it
                )

            spinner.adapter = arrayAdapter
        })

        //show picker on click
        beginDateTexview.setOnClickListener {
            setupAndShowDatePickerDialog(
                MainViewModel.DateType.BEGIN,
                viewmodel.getBeginCalendarLiveData().value!!
            )
        }

        endDateTexview.setOnClickListener {
            setupAndShowDatePickerDialog(MainViewModel.DateType.END, viewmodel.getEndCalendarLiveData().value!!)
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            search()
        }
    }

    private fun displayDate(textView: TextView, calendar: Calendar) {
        textView.text = dateFormatter.format(calendar.time)
    }

    private fun setupAndShowDatePickerDialog(dateType: MainViewModel.DateType, calendar: Calendar) {
        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                viewmodel.updateCalendarLiveData(dateType, year, month, day)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun search() {
         //Get begin date --> seconds
         val begin = viewmodel.getBeginCalendarLiveData().value!!.timeInMillis / 1000

         //get end date --> seconds
         val end = viewmodel.getEndCalendarLiveData().value!!.timeInMillis / 1000

         //airport type --> boolean isArrival
         val isArrival = findViewById<Switch>(R.id.switchView).isChecked

         // airport --> String icao
         val airportSelectedIndex = findViewById<Spinner>(R.id.airportSpinner).selectedItemPosition
         val selectedAirport = viewmodel.getAirportListLiveData().value!![airportSelectedIndex]
         val airportIcao = selectedAirport.icao

         Log.i(
             "SEARCH",
             "begin = $begin, end = $end, isArrival = $isArrival, airportIcao = $airportIcao"
         )
    }
}