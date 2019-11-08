package com.example.allsensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.ArrayAdapter
import android.widget.ListView
//import android.widget.Toast

class MainActivity : AppCompatActivity(){

    private lateinit var sensorManager: SensorManager
    private lateinit var lista: ArrayList<Sensor>
    private lateinit var lvSensor: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        this.lista = arrayListOf()
        this.lvSensor = findViewById(R.id.lvListSensor)
        this.lvSensor.adapter = ArrayAdapter<Sensor>(this, android.R.layout.simple_list_item_1, this.lista)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        val iterator: Iterator<Sensor> = deviceSensors.iterator()

        while (iterator.hasNext()){
            val sensor = iterator.next()
            this.lista.add(sensor)
            this.atualizaLista()
            Log.i("APP_SENSORS", "Name: ${sensor.name}")
            Log.i("APP_SENSORS", "ToString: ${sensor.toString()}")
            Log.i("APP_SENSORS", "-- OUTRO --")
//            Toast.makeText(this@MainActivity, sensor.name.toString(), Toast.LENGTH_LONG).show()
        }
    }

    fun atualizaLista(){
        (this.lvSensor.adapter as ArrayAdapter<Sensor>).notifyDataSetChanged()
    }

}
