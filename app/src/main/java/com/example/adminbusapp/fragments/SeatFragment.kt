package com.example.adminbusapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adminbusapp.BUS_NUM_KEY
import com.example.adminbusapp.R
import com.example.adminbusapp.ROUTE_NUM_KEY
import com.example.adminbusapp.SeatStatusActivity
import com.example.adminbusapp.adapters.StandAdapter
import com.example.adminbusapp.objects.FindingStandObject
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SeatFragment : Fragment() {

    lateinit var imgView: ImageView

    private lateinit var dbRef: DatabaseReference
    lateinit var selectedSeat: ImageView
    private lateinit var recycView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dbRef = FirebaseDatabase.getInstance().reference

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_seat, container, false)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val intent = requireActivity().intent
        val routeNum = intent.getStringExtra(ROUTE_NUM_KEY)?:""
        val busNum = intent.getStringExtra(BUS_NUM_KEY)?:""
        changingBus(routeNum,busNum)
        FindingStandObject.findStand(routeNum!!)
        val arr = FindingStandObject.standList

        recycView = view.findViewById(R.id.standRecycView)
        val btn = view.findViewById<Button>(R.id.seatStatusBtn)
        recycView.setHasFixedSize(true)
        recycView.layoutManager = LinearLayoutManager(requireContext())
        recycView.adapter = StandAdapter(arr)
        btn.setOnClickListener {
            startActivity(Intent(context,SeatStatusActivity::class.java))
        }


    }

    companion object{
        var routeNumber = ""
        var busNumber = ""
        fun changingBus(a: String , b: String){
            routeNumber = a
            busNumber = b
        }
    }

    fun seatClick(v: android.view.View){



        when (v.id) {

            R.id.S1 -> imgView = v.findViewById(R.id.S1)
            R.id.S2 -> imgView = v.findViewById(R.id.S2)
            R.id.S3 -> imgView = v.findViewById(R.id.S3)
            R.id.S4 -> imgView = v.findViewById(R.id.S4)
            R.id.S5 -> imgView = v.findViewById(R.id.S5)
            R.id.S6 -> imgView = v.findViewById(R.id.S6)
            R.id.S7 -> imgView = v.findViewById(R.id.S7)
            R.id.S8 -> imgView = v.findViewById(R.id.S8)
            R.id.S9 -> imgView = v.findViewById(R.id.S9)
            R.id.S10 -> imgView = v.findViewById(R.id.S10)
            R.id.S11 -> imgView = v.findViewById(R.id.S11)
            R.id.S12 -> imgView = v.findViewById(R.id.S12)
            R.id.S13 -> imgView = v.findViewById(R.id.S13)
            R.id.S14 -> imgView = v.findViewById(R.id.S14)
            R.id.S15 -> imgView = v.findViewById(R.id.S15)
            R.id.S16 -> imgView = v.findViewById(R.id.S16)
            R.id.S17 -> imgView = v.findViewById(R.id.S17)
            R.id.S18 -> imgView = v.findViewById(R.id.S18)
            R.id.S19 -> imgView = v.findViewById(R.id.S19)
            R.id.S20 -> imgView = v.findViewById(R.id.S20)
            R.id.S21 -> imgView = v.findViewById(R.id.S21)
            R.id.S22 -> imgView = v.findViewById(R.id.S22)
            R.id.S23 -> imgView = v.findViewById(R.id.S23)
            R.id.S24 -> imgView = v.findViewById(R.id.S24)
            R.id.S25 -> imgView = v.findViewById(R.id.S25)
            R.id.S26 -> imgView = v.findViewById(R.id.S26)
            R.id.S27 -> imgView = v.findViewById(R.id.S27)
            R.id.S28 -> imgView = v.findViewById(R.id.S28)
            R.id.S29 -> imgView = v.findViewById(R.id.S29)
            R.id.S30 -> imgView = v.findViewById(R.id.S30)
            R.id.S31 -> imgView = v.findViewById(R.id.S31)
            R.id.S32 -> imgView = v.findViewById(R.id.S32)
            R.id.S33 -> imgView = v.findViewById(R.id.S33)
            R.id.S34 -> imgView = v.findViewById(R.id.S34)
            R.id.S35 -> imgView = v.findViewById(R.id.S35)
            R.id.S36 -> imgView = v.findViewById(R.id.S36)
        }

        if (imgView.drawable.constantState == requireActivity().resources.getDrawable(R.drawable.booked_img).constantState) {
            Toast.makeText(requireContext(), "this seat is already booked", Toast.LENGTH_SHORT).show()
        } else {

            if (imgView.drawable.constantState == requireActivity().resources.getDrawable(R.drawable.your_seat_img).constantState) {
//                count--
                imgView.setImageResource(R.drawable.available_img)
            } else {
//                if (count <= 1) {
                    selectedSeat = imgView
                    imgView.setImageResource(R.drawable.your_seat_img)
//                    count++
//                } else {
//                    Toast.makeText(requireContext(), "u already selected a seat", Toast.LENGTH_SHORT)
//                        .show()
//                }
            }

        }

    }


}