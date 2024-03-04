package com.example.nillaapp_release

import android.app.Person
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi

class DetailActivity : AppCompatActivity() {

    private lateinit var subdistrictDescription: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val subdistrictName = intent.getStringExtra("SUBDISTRICT_NAME")
        subdistrictDescription = intent.getStringExtra("SUBDISTRICT_DESCRIPTION") ?: ""
        val subdistrictPhoto = intent.getIntExtra("SUBDISTRICT_PHOTO", 0)

        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)
        val ivDetailPhoto: ImageView = findViewById(R.id.iv_detail_photo)

        tvDetailName.text = subdistrictName
        tvDetailDescription.text = subdistrictDescription
        ivDetailPhoto.setImageResource(subdistrictPhoto)
        }

    fun shareOnClick(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, subdistrictDescription)
        startActivity(Intent.createChooser(intent, "Bagikan via"))
    }
    }