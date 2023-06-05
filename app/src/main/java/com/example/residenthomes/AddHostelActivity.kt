package com.example.residenthomes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.text.SimpleDateFormat
import java.util.*


class AddHostelActivity : AppCompatActivity() {
    lateinit var imgUpload: ImageView
    lateinit var btnUpload: Button
    lateinit var imageReference:FirebaseStorage
    private var currentFile: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_hostel)
        imgUpload = findViewById(R.id.mImgUpload)
        btnUpload = findViewById(R.id.mBtnUpload)
        imageReference = FirebaseStorage.getInstance()

        imgUpload.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also {
                it.type = "image/*"
                imageLauncher.launch(it)
            }
        }

        btnUpload.setOnClickListener {
            val filename = generateFilename()
            uploadImageToStorage(filename)
        }
    }

    private val imageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            result?.data?.data?.let {
                currentFile = it
                imgUpload.setImageURI(it)
            }
        } else {
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateFilename(): String {
        val currentTime = Calendar.getInstance().timeInMillis
        val dateFormat = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault())
        return dateFormat.format(currentTime)
    }

    private fun uploadImageToStorage(filename: String) {
        try {
            currentFile?.let {
                imageReference.getReference().child("images/$filename").putFile(it).
                addOnCompleteListener {
                    var url = it.result.storage.downloadUrl.addOnSuccessListener {
                        Toast.makeText(this, "Upload success!!", Toast.LENGTH_SHORT).show()
                        var id = System.currentTimeMillis().toString()
                        var hostelData = Hostelclass(it.toString(),id)
                        var ref = FirebaseDatabase.getInstance().getReference().child("Hostels/$id")
                        ref.setValue(hostelData)
                    }

                }.addOnFailureListener {
                    Toast.makeText(this, "Upload failed", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

}