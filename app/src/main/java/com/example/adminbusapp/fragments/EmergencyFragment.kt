package com.example.adminbusapp.fragments

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import com.example.adminbusapp.BUS_NUM_KEY
import com.example.adminbusapp.R
import com.example.adminbusapp.ROUTE_NUM_KEY
import com.example.adminbusapp.animation.startAnimation
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EmergencyFragment : Fragment() ,OnMapReadyCallback, LocationListener {


    private lateinit var mMap: GoogleMap
    private lateinit var routeNum: String
    private lateinit var busNum: String
    private lateinit var dbRef: DatabaseReference
    private var manager: LocationManager? = null
    private val MIN_TIME : Long = 1000
    private val MIN_DIST :Float = 1F
    var STOP_TEXT = "Stop sharing live location"
    var START_TEXT = "Start sharing live location"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


            return inflater.inflate(R.layout.fragment_emergency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val emBtn = view.findViewById<ImageView>(R.id.emergency_button)
        emBtn.isEnabled = false
        val animation = AnimationUtils.loadAnimation(context,R.anim.circle_explosion).apply {
            duration = 800
            interpolator = AccelerateDecelerateInterpolator()
        }
        val txt = view.findViewById<TextView>(R.id.locationTxt)
        val circle = view.findViewById<View>(R.id.circle)

        val intent = requireActivity().intent
        routeNum = intent.getStringExtra(ROUTE_NUM_KEY)?:""
        busNum = intent.getStringExtra(BUS_NUM_KEY)?:""

        val emLytBack = view.findViewById<LinearLayout>(R.id.emergency_layout_background)

        dbRef =FirebaseDatabase.getInstance().reference

        val emSwitch = view.findViewById<Switch>(R.id.emSwitch)
        emSwitch.setOnCheckedChangeListener { p0, isChecked ->
            emBtn.isEnabled = isChecked
            if(isChecked) {
                circle.isVisible = true
                circle.startAnimation(animation){
                    emLytBack.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
                    circle.visibility = View.INVISIBLE
                }
            }else{
                emLytBack.setBackgroundColor(resources.getColor(android.R.color.white))
            }
        }

        emBtn.setOnClickListener {
            if(txt.text == START_TEXT){
                manager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
                txt.text = STOP_TEXT
                getLocationUpdate()
            }else{
                txt.text = START_TEXT
                manager=null
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode ==101){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLocationUpdate()
            }else{
                Toast.makeText(requireContext(), "permission required", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getLocationUpdate() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            if (manager != null) {
                if (manager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    manager!!.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME,
                        MIN_DIST,this
                    )

                } else if (manager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    manager!!.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME,
                        MIN_DIST,this)
                } else {
                    Toast.makeText(requireContext(), "no provider enabled", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),101)
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }

    override fun onLocationChanged(location: Location) {
        if (location != null && manager!=null) {
            saveLocation(location)
        } else {
            Toast.makeText(requireContext(), "No location", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveLocation(location: Location) {
        dbRef.child(routeNum).child("BUSES").child(busNum).child("LOCATION").setValue(location)
    }

}