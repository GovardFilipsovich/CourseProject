package com.example.test_accel

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.caverock.androidsvg.SVG
import com.example.test_accel.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var map: MapModel
    private lateinit var bind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        map = intent.getSerializableExtra("map") as MapModel
        Log.i("tag", "MainActivity")
        Log.i("tag", map.toString())
        bind.mapName.text = map.name
        val svg = SVG.getFromString(map.image)
        val newBM: Bitmap = Bitmap.createBitmap(
            ceil(svg.documentWidth.toDouble()).toInt(), ceil(svg.documentHeight.toDouble()).toInt(),
            Bitmap.Config.ARGB_8888
        )
//        val bmcanvas = Canvas(newBM)
//        bmcanvas.drawRGB(255, 255, 255)
//        val p = Paint()
//        p.style = Paint.Style.STROKE
//        p.strokeWidth = 3f
//        bmcanvas.drawRect(0f, newBM.height.toFloat()-1, newBM.width.toFloat()-2, 0f, p)
//        svg.renderToCanvas(bmcanvas)
        var map_view = MapView(svg, baseContext)
        map
        bind.root.addView(map_view)

    }
}