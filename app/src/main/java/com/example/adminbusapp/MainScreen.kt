package com.example.adminbusapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adminbusapp.adapters.ViewPagerAdapter
import com.example.adminbusapp.databinding.ActivityMainScreenBinding
import com.example.adminbusapp.fragments.SeatFragment
import com.example.adminbusapp.objects.FindingStandObject
import com.google.android.material.tabs.TabLayoutMediator

class MainScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMainScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val intent = intent
//        val routeNum = intent.getStringExtra(ROUTE_NUM_KEY)
//        val busNum = intent.getStringExtra(BUS_NUM_KEY)
//        val arr = FindingStandObject.findStand(routeNum!!)



        val adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position->
            when(position){
                0->{
                    tab.setText("Stands")
                    tab.icon = resources.getDrawable(R.drawable.ic_bus)
                }
                1->{
                    tab.text = "Scan"
                    tab.icon = resources.getDrawable(R.drawable.ic_qr)
                }
                2->{
                    tab.text = "Emergency Button"
                    tab.icon = resources.getDrawable(R.drawable.ic_alert)
                }
            }
        }.attach()

    }

    fun seatClickedFun(view: android.view.View) {

        var fragment : SeatFragment = SeatFragment()
        fragment.seatClick(view)

    }
}