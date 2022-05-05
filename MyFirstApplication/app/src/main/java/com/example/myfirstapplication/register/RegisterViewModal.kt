package com.example.myfirstapplication.register

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfirstapplication.R


class RegisterViewModal:ViewModel() {


      var dialogAlert = MutableLiveData(R.string.zero)



     val edName = MutableLiveData("")
     val edEmail = MutableLiveData("")
    val edPassword = MutableLiveData("")


    init {
        dialogAlert.value = R.string.zero
    }


    fun clickOnContinueButton(){
        validation()
    }


    private fun validation()
    {
        if(edName.value?.isEmpty() == true || edEmail.value?.isEmpty() == true || edPassword.value?.isEmpty() == true)
        {
            dialogAlert.value = R.string.please_enter_valid_detail_text
            Log.i("Check","All Empty")
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(edEmail.value!!.trim()).matches())
        {
            dialogAlert.value = R.string.please_enter_the_valid_email
            Log.i("Check","Invalid Email")
        }
        else if(edPassword.value?.length!! < 5)
        {
            dialogAlert.value = R.string.please_enter_password_more_than_5_character
            Log.i("Check","Password Less Than 5 char")
        }
        else
        {
            dialogAlert.value = R.string.ok
            Log.i("Check","We can move foreword")
        }

    }

    override fun onCleared() {
        super.onCleared()
        Log.i("RegisterViewModal", "RegisterViewModal destroyed!")
    }


}