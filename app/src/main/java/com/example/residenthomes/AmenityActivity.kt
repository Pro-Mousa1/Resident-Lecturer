package com.example.residenthomes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AmenityActivity : AppCompatActivity() {
    lateinit var breakfast:TextView
    lateinit var lunch:TextView
    lateinit var supper:TextView
    lateinit var service:TextView
    lateinit var services:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amenity)
        breakfast = findViewById(R.id.mTvBreakfast)
        lunch = findViewById(R.id.mTvLunch)
        supper = findViewById(R.id.mTvSupper)
        service = findViewById(R.id.mTvService)
        services = findViewById(R.id.mTvservices)

        var receivedBreakfast = intent.getStringExtra("breakfast")
        var receivedLunch = intent.getStringExtra("lunch")
        var receivedSupper = intent.getStringExtra("supper")
        var receivedService = intent.getStringExtra("service")
        var receivedServices = intent.getStringExtra("services")

    }
}