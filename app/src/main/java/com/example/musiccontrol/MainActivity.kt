package com.example.musiccontrol

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(),SensorEventListener {
    var sensor:Sensor?=null
    var sensorManager:SensorManager?=null

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }


    var isRunning=false
    var mp : MediaPlayer? = null;
    override fun onSensorChanged(event: SensorEvent?) {

        if(event!!.values[0]>30 && isRunning==false){
            isRunning=true

            var mediaPlayer = MediaPlayer.create(this, R.raw.music)
            var Screen: TextView = findViewById(R.id.Screen)
            Screen.text = "Music Playing"
            mediaPlayer.start()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor= sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }
}