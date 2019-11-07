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

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        val iterator: Iterator<Sensor> = deviceSensors.iterator()

        display_img.visibility = View.INVISIBLE
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        while (iterator.hasNext()){
            val sensor = iterator.next()
            Log.i("APP_SENSORS", "Name: ${sensor.name}")
            Log.i("APP_SENSORS", "ToString: ${sensor.toString()}")
            Log.i("APP_SENSORS", "-- OUTRO --")
            Toast.makeText(this@MainActivity, sensor.name.toString(), Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
    var isRunning = false
    override fun onSensorChanged(event: SensorEvent?) {
        try {
            if (event!!.values[0] < 40 && isRunning == false) {
                var value = event.values[0]
                isRunning = true
                display_img.visibility = View.VISIBLE
            } else {
                isRunning = false
                display_img.visibility = View.INVISIBLE
            }
        } catch (e: IOException) { }
    }
}
