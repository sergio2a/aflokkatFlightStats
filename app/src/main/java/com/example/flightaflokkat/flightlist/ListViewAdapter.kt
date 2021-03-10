package com.example.flightaflokkat.flightlist

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.flightaflokkat.flightlist.FlightInfoCell
import com.example.flightaflokkat.models.FlightModel

/**
 * Created by sergio on 3/9/21
 * All rights reserved GoodBarber
 */
class ListViewAdapter : BaseAdapter {

    var flightList: List<FlightModel>

    constructor(pFlightList: List<FlightModel>) : super() {
        flightList = pFlightList
    }

    override fun getView(position: Int, convertView: View?, container: ViewGroup?): View {
        Log.i("LISTVIEW", "getView call with position $position and convertView = $convertView")
        //Creation vue
        var flightInfoCell :FlightInfoCell? = null
        if(convertView == null)
            flightInfoCell= FlightInfoCell(container!!.context)
        else
            flightInfoCell = convertView as FlightInfoCell?
        //BindData with postion
        flightInfoCell?.bindData(flightList[position])
        //return view
        return flightInfoCell!!
    }

    override fun getCount(): Int {
        return flightList.size
    }

    override fun getItem(p0: Int): Any {
        return flightList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 1000L
    }
}