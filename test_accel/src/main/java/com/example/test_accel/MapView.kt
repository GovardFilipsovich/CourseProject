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
    private var svg_str = ""
    private lateinit var svg: SVG
    private lateinit var pic: Picture

    private var rect: RectF = RectF(0f,0f,0f,0f)

    private var c_width: Int = 0
    private var c_height: Int = 0

    private var k: Int = 1

    private var marker_coords = PointF(50f, 15f)

    // Пример добавления круга план в svg
    // "<ellipse cx=\"50\" cy=\"15\" rx=\"1.8\" ry=\"0.85\" stroke=\"red\" fill=\"red\" />\n"+
    // Log.i("tag", "Hello " + svg.documentWidth / rect.right + " " + svg.documentHeight / rect.bottom)
    fun setMarker(p: PointF){
        marker_coords = p
        // Расчет размера маркера
        // Домножение на 0.6 связано с тем, что несмотря на то, что виджету выделяется некоторое пространство на холсте,
        // он занимает всю его ширину, и примерно 0.4 от высоты
        var rx = 10.0 / c_width * svg.documentWidth * 0.6
        Log.i("testing", "" + rx)
        var ry = 10.0 / c_height * svg.documentHeight
        Log.i("testing", "" + ry)

        // Сохранение плана без маркера
        val pr_svg_str = svg_str

        val ellipse = "<ellipse cx=\"${p.x}\" cy=\"${p.y}\" rx=\"${rx}\" ry=\"${ry}\" stroke=\"#ff4f00\" fill=\"#ff4f00\" />"
        svg_str = svg_str.replace("</svg>", ellipse + " </svg>")
        setSvg(svg_str)
        // Возращаем план к состоянию, до добавленимя маркера
        svg_str = pr_svg_str
        invalidate()
    }

    fun setSvg(str: String){
        svg_str = str
        svg = SVG.getFromString(str)
        pic = svg.renderToPicture()
    }

    fun moveHor(k: Int){
        //TODO:функция перемещения по горизонтали
    }

    fun inc(){
        if(k < 10)
            k++
        setScale(k)
    }
    fun dec(){
        if(k > -9)
            k--
        setScale(k)
    }

    fun setScale(k: Int){
        Log.i("tag", "not scaling")
        if(k > 11){
            throw ArithmeticException("Максимум достигнут")
        }

        // Масштабирование по горизонтали
        // mid + (b1*q**(k-1))
        rect.left = c_width / 2 -(c_width/2 * 0.7.pow(k - 1)).toFloat()
        rect.right = c_width / 2 + (c_width/2 * 0.7.pow(k - 1)).toFloat()

        //масштабирование по вертикали
        rect.top = (c_height * 0.1).toFloat()
        rect.bottom = ((c_height * 0.1) + c_height * 0.5 * 0.7.pow(k-1)).toFloat()
        //Log.i("testing", "" + rect.top + " " + rect.bottom)

        invalidate()
    }

    constructor(svg_par: String, context: Context, attributeSet: AttributeSet, defStyle: Int): super(context, attributeSet, defStyle){
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
        // При изменении размера виджета, данные параметры должны быть
        // установлены в ноль, чтобы они обновились при следующей отрисовке
        c_width = 0
        c_height = 0
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.i("tag", "before drawing " + strokePaint.toString())
        // Задание размеров виджета
        if(c_width==0){
            c_width = width
            c_height = height
            setScale(k)
        }

        canvas.apply {
            strokePaint.strokeWidth = 10F
            //Log.i("tag", "Hello " + svg.documentWidth / rect.right + " " + svg.documentHeight / rect.bottom)
            drawPicture(pic, rect)

        }
        Log.i("tag", "after drawing")
    }
}
