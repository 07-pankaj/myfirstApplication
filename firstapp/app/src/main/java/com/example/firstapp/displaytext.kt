package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.TextView

class displaytext : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_displaytext)


        val message = intent.getStringExtra(EXTRA_MESSAGE)
       findViewById<TextView>(R.id.text_dis).apply {
            text = message
        }
    }
}