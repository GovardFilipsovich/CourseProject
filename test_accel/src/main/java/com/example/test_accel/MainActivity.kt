package com.example.test_accel

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.drawable.BitmapDrawable
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.caverock.androidsvg.SVG
import com.example.test_accel.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var map: MapModel
    private lateinit var bind: ActivityMainBinding
    private var start = true
    private var start_steps = 0
    private val REQUEST_CODE = 239
    private var angle = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        // Запрос разрешения на снятия показаний с датчиков активности
        if(ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
            requestPermissions(arrayOf(android.Manifest.permission.ACTIVITY_RECOGNITION), REQUEST_CODE)
        }
        map = intent.getSerializableExtra("map") as MapModel

        //map = MapModel("test", "", Info(), svg_str)
        //Log.i("tag", "MainActivity")
        Log.i("tag", map.toString())
        bind.mapName.text = map.name
        bind.mapView.setSvg(map.image)
        //bind.mapView.setSvg(resources.getString(R.string.test_square))

        // Привязка масштабирования к кнопкам
        bind.addButton.setOnClickListener{
            bind.mapView.dec()
        }

        bind.subButton.setOnClickListener{
            bind.mapView.inc()
        }

        // Кнопка Установить свое местоположение
        bind.placeButton.setOnClickListener {
            bind.mapView.setOnPlacement()
        }
        init_sensors()
    }

    fun init_sensors(){
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        Log.i("testing", sensorManager.toString())
        val stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        Log.i("testing", stepSensor.toString())
        val accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, accelSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        //Log.i("testing", event.toString())
        //Log.i("testing", event!!.values[0].toString())
        Log.i("testing", event!!.sensor.stringType)
        when(event!!.sensor.type){
            Sensor.TYPE_STEP_COUNTER -> {
                if(start){
                    start_steps = event.values[0].toInt()
                    start = false
                }
                Log.i("testing", "Steps: " + (event.values[0] - start_steps) )
            }
            Sensor.TYPE_ACCELEROMETER -> {
//                Log.i("testing", "A x: " + event.values[0].toString())
//                Log.i("testing", "A y: " + event.values[1].toString())
//                Log.i("testing", "A z: " + event.values[2].toString())
            }
        }


        // todo: выделить обновление интерфейса в отдельный поток


    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.i("testing", sensor.toString() + " " + accuracy)
    }
}