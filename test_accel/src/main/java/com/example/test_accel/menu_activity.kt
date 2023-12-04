package com.example.test_accel

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.caverock.androidsvg.SVG
import com.example.test_accel.databinding.ActivityMenuBinding
import kotlin.math.ceil

class menu_activity : AppCompatActivity() {
    private lateinit var bind: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.mapBut.setOnClickListener {
            var int = Intent(this, MapsActivity::class.java)
            startActivity(int)
        }

        bind.aboutBut.setOnClickListener {
            var int = Intent(this, AboutActivity::class.java)
            startActivity(int)
        }

    }
}