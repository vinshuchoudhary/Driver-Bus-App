package com.example.adminbusapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adminbusapp.databinding.ActivityTicketInfoScreenBinding
import com.example.adminbusapp.fragments.QR_DATA_KEY
import com.example.adminbusapp.modelClass.DatabaseModelClass
import com.google.firebase.database.*

class TicketInfoScreen : AppCompatActivity() {

    private lateinit var binding : ActivityTicketInfoScreenBinding
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketInfoScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val qrResult = intent.getStringExtra(QR_DATA_KEY)?:""
        val lines = qrResult.lines()

        dbRef = FirebaseDatabase.getInstance().reference



        binding.sourceTxt.text = lines[0]
        binding.destTxt.text = lines[1]
        binding.routeTxt.text = lines[2]
        binding.seatTxt.text = lines[3]
        binding.busNumTxt.text = lines[4]

        dbRef.child(lines[2]).child("BUSES").child(lines[4]).child("OWNERS").child(lines[3]).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val obj = snapshot.getValue(DatabaseModelClass::class.java)
                if(!obj!!.scanned){
                    binding.isScanned.text = "No"
                    dbRef.child(lines[2]).child("BUSES").child(lines[4]).child("OWNERS").child(lines[3]).child("scanned").setValue(true)
                }else{
                    binding.isScanned.text = "Yes"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}