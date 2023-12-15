package com.example.test_accel

import android.content.ClipData
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.DrawableContainer
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import com.caverock.androidsvg.SVG
import kotlin.math.ceil
import kotlin.math.min
import kotlin.math.pow

enum class Sides(val num: Int) {
    LEFT(0),
    TOP(1),
    RIGHT(2),
    BOTTOM(3)
}
class MapView: View{
    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
    }
    private lateinit var svg: SVG
    private lateinit var pic: Picture
    private  var scale_koeffs = floatArrayOf(0.0f, 0.0f,1f,0.5f)
    //private var scale_koeffs_land = floatArrayOf(0f,0.0f,1f,0.5f)
    private var rect: RectF = RectF(0f,0f,0f,0f)
    private var is_rect_reset = true
    private var max_width = 25000
    private var c_width: Int = 0

    fun setSvg(str: String){
        svg = SVG.getFromString(str)
        pic = svg.renderToPicture()

    }

    fun moveHor(k: Int){
        //TODO:функция перемещения по горизонтали
        scale_koeffs[Sides.LEFT.num] = ((k-1)*0.1).toFloat()
        scale_koeffs[Sides.RIGHT.num] = 1 + ((k-1)*0.1).toFloat()
    }

    fun setScale(k: Int){
        Log.i("tag", "not scaling")
        // Масштабирование по горизонтали
        if(k > 11){
            throw ArithmeticException("Максимум достигнут")
        }
        //scale_koeffs[Sides.LEFT.num] = ((k-1)*0.1).toFloat()
        scale_koeffs[Sides.LEFT.num] = 1f

        // mid + (b1*q**(k-1))
        //scale_koeffs[Sides.RIGHT.num] = (c_width/2 * 0.7.pow((k - 1).toDouble())).toFloat()
        rect.left = c_width / 2 -(c_width/2 * 0.7.pow((k - 1).toDouble())).toFloat()
        rect.right = c_width / 2 + (c_width/2 * 0.7.pow((k - 1).toDouble())).toFloat()
        Log.i("testing", rect.right.toString() + " " + rect.left)
        //TODO: масштабирование по вертикали
        is_rect_reset = true
        invalidate()
    }

    constructor(svg_par: String, context: Context, attributeSet: AttributeSet, defStyle: Int): super(context, attributeSet, defStyle){
        //Log.i("tag", "Before svg_str setter")
        setSvg(svg_par)

    }
    constructor(svg_str: String, context: Context, attributeSet: AttributeSet): this(svg_str, context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet): this(
        context.getString(R.string.defalut_map), context, attributeSet
    )

    override fun invalidate() {
        super.invalidate()
        Log.i("tag", "i")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.i("tag", "before drawing " + strokePaint.toString())
        // Задание размеров виджета
        if(c_width==0){
            c_width = width
        }

        if (is_rect_reset){
            // Деление на 2 нужно для того, чтобы ограничить правую и левую стороны
            //rect.left = width / 2 * scale_koeffs[Sides.LEFT.num]
            //rect.right = width/2 + width / 2 * scale_koeffs[Sides.RIGHT.num]

            rect.top = height * scale_koeffs[Sides.TOP.num]
            rect.bottom = height * scale_koeffs[Sides.BOTTOM.num]

        }

        canvas.apply {
            strokePaint.strokeWidth = 10F
            //drawRect(5F, height.toFloat()/20, width.toFloat() - 5, height.toFloat() / 2, strokePaint)
            //Log.i("tag", svg_str)
            drawPicture(pic, rect)

        }
        Log.i("tag", "after drawing")
    }
}
