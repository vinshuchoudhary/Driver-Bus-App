package com.example.adminbusapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adminbusapp.R

class StandAdapter(val arr: Array<String>) : RecyclerView.Adapter<StandAdapter.StandViewHolder>() {

    class StandViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val standName = itemView.findViewById<TextView>(R.id.standTxt)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.seat_item, parent, false)
        return StandViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StandViewHolder, position: Int) {

        holder.standName.text = arr[position + 1]
    }

    override fun getItemCount(): Int {
        return arr.size - 1
    }

}