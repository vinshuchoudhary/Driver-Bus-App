package com.example.adminbusapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.adminbusapp.databinding.ActivitySeatStatusBinding
import com.example.adminbusapp.fragments.SeatFragment
import com.example.adminbusapp.objects.busStandData
import com.google.firebase.database.*

class SeatStatusActivity : AppCompatActivity() {

    lateinit var binding: ActivitySeatStatusBinding
    lateinit var dbRef: DatabaseReference
    lateinit var imgView: ImageView
    lateinit var selectedSeat: ImageView
    lateinit var routeNum: String
    lateinit var busNum: String
    var seatList: MutableMap<String, Int> = mutableMapOf()
    var count = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeatStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbRef = FirebaseDatabase.getInstance().reference
        routeNum = SeatFragment.routeNumber
        busNum = SeatFragment.busNumber
        val map = busStandData.arr
        val arr = map[routeNum]!!
        binding.sourceTxt.text = arr[1]
        binding.destTxt.text = arr[arr.size-1]

        updateUI()

//        dbRef.child(routeNum).child("BUSES").child(busNum).child("SEATS")
//            .addChildEventListener(object : ChildEventListener {
//                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
//                    val idArr = arrayOf(
//                        R.id.S1,
//                        R.id.S2,
//                        R.id.S3,
//                        R.id.S4,
//                        R.id.S5,
//                        R.id.S6,
//                        R.id.S7,
//                        R.id.S8,
//                        R.id.S9,
//                        R.id.S10,
//                        R.id.S11,
//                        R.id.S12,
//                        R.id.S13,
//                        R.id.S14,
//                        R.id.S15,
//                        R.id.S16,
//                        R.id.S17,
//                        R.id.S18
//                    )
//
//                    for (i in idArr) {
//                        val img: ImageView = findViewById(i)
//                        if (img.tag == snapshot.key.toString()) {
//                            if (snapshot.value.toString() == "TRUE") {
//                                img.setImageResource(R.drawable.booked_img)
//                            } else {
//                                img.setImageResource(R.drawable.available_img)
//                            }
//                        }
//                    }
//                }
//
//                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
//                    if (snapshot.value.toString() == "TRUE") {
//                        imgView.setImageResource(R.drawable.booked_img)
//                    } else {
//                        imgView.setImageResource(R.drawable.available_img)
//                    }
//                }
//
//                override fun onChildRemoved(snapshot: DataSnapshot) {
//                    TODO("Not yet implemented")
//                }
//
//                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
//                    TODO("Not yet implemented")
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    TODO("Not yet implemented")
//                }
//            })

        binding.bookSeatBtn.setOnClickListener {

            if (seatList.isNotEmpty()) {

                for ((k, v) in seatList) {
                    dbRef.child(routeNum).child("BUSES").child(busNum).child("SEATS").child(k).setValue("TRUE")
                }
                updateUI()

            } else {
                Toast.makeText(this, "no seat is selected", Toast.LENGTH_SHORT).show()
            }

        }

        binding.freeSeatBtn.setOnClickListener {
            if (seatList.isNotEmpty()) {

                for ((k, v) in seatList) {
                    dbRef.child(routeNum).child("BUSES").child(busNum).child("SEATS").child(k).setValue("EMPTY")
                }

                updateUI()
            } else {
                Toast.makeText(this, "no seat is selected", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun seatClickedFun(view: android.view.View) {

        when (view.id) {

            R.id.S1 -> imgView = findViewById(R.id.S1)
            R.id.S2 -> imgView = findViewById(R.id.S2)
            R.id.S3 -> imgView = findViewById(R.id.S3)
            R.id.S4 -> imgView = findViewById(R.id.S4)
            R.id.S5 -> imgView = findViewById(R.id.S5)
            R.id.S6 -> imgView = findViewById(R.id.S6)
            R.id.S7 -> imgView = findViewById(R.id.S7)
            R.id.S8 -> imgView = findViewById(R.id.S8)
            R.id.S9 -> imgView = findViewById(R.id.S9)
            R.id.S10 -> imgView = findViewById(R.id.S10)
            R.id.S11 -> imgView = findViewById(R.id.S11)
            R.id.S12 -> imgView = findViewById(R.id.S12)
            R.id.S13 -> imgView = findViewById(R.id.S13)
            R.id.S14 -> imgView = findViewById(R.id.S14)
            R.id.S15 -> imgView = findViewById(R.id.S15)
            R.id.S16 -> imgView = findViewById(R.id.S16)
            R.id.S17 -> imgView = findViewById(R.id.S17)
            R.id.S18 -> imgView = findViewById(R.id.S18)
        }
//
//        if (imgView.drawable.constantState == this.resources.getDrawable(R.drawable.booked_img).constantState) {
//            Toast.makeText(this, "this seat is already booked", Toast.LENGTH_SHORT).show()
//        } else {

            if (imgView.drawable.constantState == this.resources.getDrawable(R.drawable.your_seat_img).constantState) {
                seatList.remove(imgView.tag.toString())
                imgView.setImageResource(R.drawable.available_img)
            } else {
                imgView.setImageResource(R.drawable.your_seat_img)
                seatList.put(imgView.tag.toString(), 1)

            }

//        }

    }

    fun updateUI(){
        dbRef.child(routeNum).child("BUSES").child(busNum).child("SEATS")
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val idArr = arrayOf(
                        R.id.S1,
                        R.id.S2,
                        R.id.S3,
                        R.id.S4,
                        R.id.S5,
                        R.id.S6,
                        R.id.S7,
                        R.id.S8,
                        R.id.S9,
                        R.id.S10,
                        R.id.S11,
                        R.id.S12,
                        R.id.S13,
                        R.id.S14,
                        R.id.S15,
                        R.id.S16,
                        R.id.S17,
                        R.id.S18
                    )

                    for (i in idArr) {
                        val img: ImageView = findViewById(i)
                        if (img.tag == snapshot.key.toString()) {
                            if (snapshot.value.toString() == "TRUE") {
                                img.setImageResource(R.drawable.booked_img)
                            } else {
                                img.setImageResource(R.drawable.available_img)
                            }
                        }
                    }
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                    if (snapshot.value.toString() == "TRUE") {
                        imgView.setImageResource(R.drawable.booked_img)
                    } else {
                        imgView.setImageResource(R.drawable.available_img)
                    }
                }

                override fun onChildRemoved(snapshot: DataSnapshot) {
                    TODO("Not yet implemented")
                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                    TODO("Not yet implemented")
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }

}