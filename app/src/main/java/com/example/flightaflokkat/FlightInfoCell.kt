package com.example.flightaflokkat

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout

/**
 * Created by sergio on 3/9/21
 * All rights reserved GoodBarber
 */
class FlightInfoCell : LinearLayout {


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
    }

    //CACA: c'est pas bien de traiter de la donner dans une vue
    // et pourtant il le fait quand même le formateur.
    fun bindData(flight : FlightModel ){
        //fill your views
    }

    private fun initLayout() {
        LayoutInflater.from(context).inflate(R.layout.flight_cell, this, true)
        bindViews()
    }

}