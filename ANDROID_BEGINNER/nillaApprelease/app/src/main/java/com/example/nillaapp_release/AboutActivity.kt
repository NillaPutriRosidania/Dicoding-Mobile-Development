package com.example.nillaapp_release

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout

class AboutActivity : AppCompatActivity() {
    private lateinit var layoutExpand: LinearLayout
    private lateinit var imageButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        layoutExpand = findViewById(R.id.layout_expand)
        imageButton = findViewById(R.id.image_button)
        imageButton.setOnClickListener {
            if (layoutExpand.visibility == View.GONE) {
                layoutExpand.visibility = View.VISIBLE
            } else {
                layoutExpand.visibility = View.GONE
            }
        }
    }
}