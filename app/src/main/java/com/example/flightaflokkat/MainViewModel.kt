package com.example.flightaflokkat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

/**
 * Created by sergio on 3/2/21
 * All rights reserved GoodBarber
 */
class MainViewModel : ViewModel() {

    private val beginCalendarLiveData: MutableLiveData<Calendar> = MutableLiveData()
    private val endCalendarLiveData: MutableLiveData<Calendar> = MutableLiveData()

    enum class DateType {
        BEGIN, END
    }

    init {
        beginCalendarLiveData.value = Calendar.getInstance()
        endCalendarLiveData.value = Calendar.getInstance()
    }

    fun getBeginCalendarLiveData(): LiveData<Calendar> {
        return beginCalendarLiveData
    }

    fun updateCalendarLiveData(dateType: DateType, year: Int, month: Int, day: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)

        when (dateType) {
            DateType.BEGIN -> beginCalendarLiveData.value = calendar
            DateType.END -> endCalendarLiveData.value = calendar
        }

    }

    fun getEndCalendarLiveData(): LiveData<Calendar> {
        return endCalendarLiveData
    }

}