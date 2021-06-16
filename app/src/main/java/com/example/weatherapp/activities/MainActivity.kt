package com.example.weatherapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search_location.setOnClickListener {
            val intent = Intent(this, SearchLocation::class.java)
            startActivity(intent)
        }

        btn_my_location.setOnClickListener {
            val intent = Intent(this, MyLocation::class.java)
            startActivity(intent)
        }
    }
}