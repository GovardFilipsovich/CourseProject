package com.example.test_accel

import android.content.Intent
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.caverock.androidsvg.SVG
import com.example.test_accel.databinding.MapItemBinding
import com.squareup.picasso.Picasso
import kotlin.math.ceil


class MapsAdapter (private val maps: List<MapModel>, listener: OnMapClickListener): RecyclerView.Adapter<MapsAdapter.MyViewHolder>(){

    // При помощи биндинга передаем необходимые view в viewHolder
    inner class MyViewHolder(val bind: MapItemBinding) : RecyclerView.ViewHolder(bind.root)
    private lateinit var context: android.content.Context
    private var listener: OnMapClickListener = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = MapItemBinding.inflate(LayoutInflater.from(parent.context))
        context = parent.context
        return MyViewHolder(binding)
    }

    // Возращаем размер переданного списка пользователей
    override fun getItemCount() = maps.size

    override fun onBindViewHolder(hold: MyViewHolder, pos: Int) {
        hold.bind.card.setOnClickListener {
            listener.OnMapClick(maps[pos])
        }
        var svg = SVG.getFromString(maps[pos].preview)
        val newBM: Bitmap = Bitmap.createBitmap(
            ceil(svg.documentWidth.toDouble()).toInt(), ceil(svg.documentHeight.toDouble()).toInt(),
            Bitmap.Config.ARGB_8888
        )
        val bmcanvas = Canvas(newBM)
        bmcanvas.drawRGB(255, 255, 255)
        var p = Paint()
        p.style = Paint.Style.STROKE
        p.strokeWidth = 3f
        bmcanvas.drawRect(0f, newBM.height.toFloat()-1, newBM.width.toFloat()-2, 0f, p)
        svg.renderToCanvas(bmcanvas)
        hold.bind.carImage.setImageDrawable(BitmapDrawable(context.resources, newBM))
        hold.bind.mapName.text = maps[pos].name

    }

}