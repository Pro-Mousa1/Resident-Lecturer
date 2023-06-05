package com.example.residenthomes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class AdminActivity : AppCompatActivity() {
    lateinit var hostel:ImageView
    lateinit var viewHostel:ImageView
    lateinit var hostelBooked:ImageView
    lateinit var location:ImageView
    lateinit var amenities:ImageView
    lateinit var viewAmenities:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        hostel = findViewById(R.id.ImgAddhostel)
        viewHostel = findViewById(R.id.ImgViewHostel)
        hostelBooked = findViewById(R.id.ImgHostelBooked)
        location = findViewById(R.id.ImgAddLocation)
        amenities = findViewById(R.id.ImgAddAmenities)
        viewAmenities = findViewById(R.id.ImgViewAmenities)


        hostel.setOnClickListener {
            val intent = Intent(this,AddHostelActivity::class.java)
            startActivity(intent)
        }
        viewHostel.setOnClickListener {
            val intent = Intent(this,ViewHostelActivity::class.java)
            startActivity(intent)
        }
        hostelBooked.setOnClickListener {
            val intent = Intent(this,HostelBookingActivity::class.java)
            startActivity(intent)
        }
        location.setOnClickListener {
            val intent = Intent()
            startActivity(intent)
        }
        amenities.setOnClickListener {
            val intent = Intent(this,AddAmenitiesActivity::class.java)
            startActivity(intent)
        }
        viewAmenities.setOnClickListener {
            val intent = Intent(this,ViewAmenitiesActivity::class.java)
            startActivity(intent)
        }
    }
}