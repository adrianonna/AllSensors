package com.example.allsensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity(){

    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        val iterator: Iterator<Sensor> = deviceSensors.iterator()

        while (iterator.hasNext()){
            val sensor = iterator.next()
            Log.i("APP_SENSORS", "Name: ${sensor.name}")
            Log.i("APP_SENSORS", "ToString: ${sensor.toString()}")
            Log.i("APP_SENSORS", "-- OUTRO --")
            Toast.makeText(this@MainActivity, sensor.name.toString(), Toast.LENGTH_LONG).show()
        }
    }

}
