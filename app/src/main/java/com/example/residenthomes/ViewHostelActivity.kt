package com.example.residenthomes

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class ViewHostelActivity : AppCompatActivity() {
    lateinit var listHostels:ListView
    lateinit var hostels:ArrayList<Hostelclass>
    lateinit var adapter: HostelAdapter
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_hostel)
        // Initialize Firebase Storage
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference

        listHostels = findViewById(R.id.mListHostel)
        hostels = ArrayList()
        adapter = HostelAdapter(this,hostels)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait...")
        // Create a reference to load data from the database
        var ref = FirebaseDatabase.getInstance().getReference().
        child("Hostels")
        // Start showing the progress as you load data
        progressDialog.show()
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                hostels.clear()
                for (data in snapshot.children){
                    val hostel = data.getValue(Hostelclass::class.java)
                    hostels.add(hostel!!)
                }
                adapter.notifyDataSetChanged()
                progressDialog.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ViewHostelActivity,
                    "Database server is inaccessible",
                    Toast.LENGTH_LONG).show()
            }
        })
        listHostels.adapter = adapter
        listHostels.setOnItemClickListener { parent, view, position, id ->
            var intent = Intent(this,HostelActivity::class.java)
            intent.putExtra("image",hostels.get(position).image)
            startActivity(intent)
        }
    }
}