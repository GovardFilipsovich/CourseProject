package com.example.test_accel

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.example.test_accel.databinding.ActivityMapsBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.carousel.CarouselLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
interface OnMapClickListener{
    fun OnMapClick(map: MapModel);
}

class MapsActivity : AppCompatActivity(), OnMapClickListener {
    private lateinit var bind: ActivityMapsBinding
    private lateinit var std_bottom_sheet: View
    private lateinit var cur_map: MapModel
    private lateinit var user_api: api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val maps_list = bind.carRec

        var source = arrayListOf<MapModel>()
        //var BASE_URL = "http://10.0.2.2:9000/"
        //var BASE_URL = "http://192.168.73.70:9000/"
        val BASE_URL = "http://192.168.0.132:9000/"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        user_api = retrofit.create(api::class.java)

        val call = user_api.get_maps()
        call.enqueue(
            object : Callback<ServerResponse>{
                override fun onResponse(
                    call: Call<ServerResponse>,
                    response: Response<ServerResponse>
                ) {
                    Log.i("tag", response.toString())
                    Log.i("tag", response.body().toString())
                    Log.i("tag", response.body()?.results.toString())
                    source = (response.body()!!.results as ArrayList<MapModel>?)!!

                    maps_list.adapter = MapsAdapter(source, this@MapsActivity
                    )
                    maps_list.layoutManager = CarouselLayoutManager()
                }

                override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                    Log.e("tag", "ERRORRRRRRR:  " + t.toString())
                }
            }
        )
        cur_map = MapModel("", "", Info(), "")
        std_bottom_sheet = bind.bottomSheet
        val behavior = BottomSheetBehavior.from(std_bottom_sheet)
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        behavior.peekHeight = 80
        behavior.isDraggable = true

        bind.openMapBut.setOnClickListener {
            transfer(cur_map)
        }
    }

    override fun OnMapClick(map: MapModel) {
        if (cur_map == map){
            transfer(map)
        }
        //Log.i("tag", map.toString())
        var behavior = BottomSheetBehavior.from(std_bottom_sheet)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        bind.nameMap.text = map.name
        bind.authorMap.text = map.info.author
        bind.addressMap.text = map.info.address
        bind.descrMap.text = "\t\t" + map.info.description
        cur_map = map

    }

    fun transfer(map: MapModel) {
        var int = Intent(this, MainActivity::class.java)
        int.putExtra("map", cur_map)
        startActivity(int)
    }


}