package com.example.flightaflokkat.flightlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flightaflokkat.models.FlightModel

/**
 * Created by sergio on 3/9/21
 * All rights reserved GoodBarber
 */
class RecyclerViewAdapter(private val flightList: List<FlightModel>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FlightInfoCell(viewGroup.context))
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val flightInfoCell = viewHolder.itemView as FlightInfoCell
        flightInfoCell.bindData(flightList[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = flightList.size

}