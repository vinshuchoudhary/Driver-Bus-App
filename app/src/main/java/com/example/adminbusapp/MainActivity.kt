package com.example.adminbusapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.adminbusapp.databinding.ActivityMainBinding

const val ROUTE_NUM_KEY = "route num key"
const val BUS_NUM_KEY = "bus num key"

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {

            val routeNum = binding.routeEt.text.toString()
            val busNum = binding.busEt.text.toString()
            val intent = Intent(this,MainScreen::class.java)
            intent.putExtra(ROUTE_NUM_KEY,routeNum)
            intent.putExtra(BUS_NUM_KEY,busNum)
            startActivity(intent)
            finish()

        }

    }
}