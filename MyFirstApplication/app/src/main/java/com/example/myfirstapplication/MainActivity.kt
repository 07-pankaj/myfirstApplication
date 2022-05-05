package com.example.myfirstapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders.of


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(1000)
        setTheme(R.style.Theme_MyFirstApplication)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        //val viewModal: RegisterViewModal  = ViewModelProvider(this).get(RegisterViewModal::class.java)

    }
}