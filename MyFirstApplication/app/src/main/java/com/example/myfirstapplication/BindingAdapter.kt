package com.example.myfirstapplication


import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load


@BindingAdapter("app:imageFromUrl")
fun loadImage(view: ImageView, url: String) {
    Log.i("imagetest","$url")
    view.load(url){
        error(R.drawable.ic_user_kid)
        listener(onError = {_,error ->
            Log.i("newerror", "Error -> ${error.throwable}")
        })
    }

}