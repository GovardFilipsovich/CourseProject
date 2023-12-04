package com.example.test_accel

import android.content.ClipData
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.caverock.androidsvg.SVG
import kotlin.math.ceil

class MapView(svg:SVG,  context: Context): FrameLayout(context) {
    private val piePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val bitmap = Bitmap.createBitmap(
        ceil(svg.documentWidth.toDouble()).toInt(), ceil(svg.documentHeight.toDouble()).toInt(),
        Bitmap.Config.ARGB_8888
    )
    private val svg = svg

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.apply {
            drawCircle(0F, 0F, 5F, piePaint)
        }
    }

}