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
import kotlin.math.abs
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
        //map = intent.getSerializableExtra("map") as MapModel

        map = MapModel("test", "", Info(), "")
        //Log.i("tag", "MainActivity")
        Log.i("tag", map.toString())
        bind.mapName.text = map.name
        //bind.mapView.setSvg(map.image)
        bind.mapView.setSvg(resources.getString(R.string.test_square))
//
//        // Привязка масштабирования к кнопкам
//        bind.addButton.setOnClickListener{
//            bind.mapView.dec()
//        }
//
//        bind.subButton.setOnClickListener{
//            bind.mapView.inc()
//        }
//
//        // Кнопка Установить свое местоположение
//        bind.placeButton.setOnClickListener {
//            bind.mapView.setOnPlacement()
//        }
//        init_sensors()
    }

    fun init_sensors(){
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        //Log.i("testing", sensorManager.toString())
        val stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        //Log.i("testing", stepSensor.toString())
        val accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val geoSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, accelSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, geoSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }


    // Массивы для записи данных
    private val accelerometerReading = FloatArray(3)
    private val magnetometerReading = FloatArray(3)

    private val rotationMatrix = FloatArray(9)
    private val orientationAngles = FloatArray(3)
    override fun onSensorChanged(event: SensorEvent?) {
        if(event!!.sensor.type == Sensor.TYPE_STEP_COUNTER){
            if(start){
                start_steps = event.values[0].toInt()
                start = false
            }
            Log.i("testing", "Steps: " + (event.values[0] - start_steps) )
            updateOrientationAngles()
        } else if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, accelerometerReading, 0, accelerometerReading.size)
        } else if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
            //Log.i("testing", "Geo: " + event.values[0] + )
            System.arraycopy(event.values, 0, magnetometerReading, 0, magnetometerReading.size)
        }
    }

    fun updateOrientationAngles() {
        SensorManager.getRotationMatrix(
            rotationMatrix,
            null,
            accelerometerReading,
            magnetometerReading
        )
        SensorManager.getOrientation(rotationMatrix, orientationAngles)
        Log.i("testing", "Orientation: " + orientationAngles[0] + " " + orientationAngles[1] + " " + orientationAngles[2])
        var p = bind.mapView.getMarkerCoords()
        bind.mapView.setMarker(PointF((p.x+0.3).toFloat(), p.y))
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.i("testing", sensor.toString() + " " + accuracy)
    }
}