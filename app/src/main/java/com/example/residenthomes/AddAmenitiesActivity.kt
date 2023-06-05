package com.example.residenthomes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast

class AddAmenitiesActivity : AppCompatActivity() {
    lateinit var breakfast:RadioButton
    lateinit var lunch:RadioButton
    lateinit var supper:RadioButton
    lateinit var serviceOne:EditText
    lateinit var serviceTwo:EditText
    lateinit var btnSubmit:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_amenities)
        breakfast = findViewById(R.id.rBtnBreak)
        lunch = findViewById(R.id.rBtnLunch)
        supper = findViewById(R.id.rBtnSupper)
        serviceOne = findViewById(R.id.mEditSer1)
        serviceTwo = findViewById(R.id.mEditSer2)
        btnSubmit = findViewById(R.id.mBtnSubmit)


        btnSubmit.setOnClickListener {
            val selectedMeals = StringBuilder()
            if (breakfast.isChecked){
                selectedMeals.append("Breakfast")
            }
            if (lunch.isChecked){
                selectedMeals.append("Lunch")
            }
            if (supper.isChecked){
                selectedMeals.append("Supper")
            }
            if (selectedMeals.isEmpty()){
                Toast.makeText(this,"Please select at least one meal", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Meals selected successfully",Toast.LENGTH_SHORT).show()
            }

            val service = serviceOne.text.toString().trim()
            val service1 = serviceOne.text.toString().trim()
            if (service.isEmpty() || service1.isEmpty()) {
                Toast.makeText(this,"Please fill all the fields", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Services added successfully",Toast.LENGTH_SHORT).show()
            }
        }
    }
}