package com.example.myfirstapplication.login
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstapplication.R
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class LoginViewModel:ViewModel() {

    private val eventChannel = Channel<Int>()
    val dialogAlert = eventChannel.receiveAsFlow()

    private val loginEventChannel = Channel<Boolean>()
    val loginInSuccessfully= loginEventChannel.receiveAsFlow()
    val edEmail = MutableLiveData("")
    val edPassword = MutableLiveData("")



    fun onContinueButtonClick()
    {
        validation()
    }


    private fun validation()
    {
        if(edEmail.value?.isEmpty() == true || edPassword.value?.isEmpty() == true)
        {

          //  dialogAlert.value= R.string.please_enter_valid_detail_text

              viewModelScope.launch {
                  eventChannel.send(R.string.please_enter_valid_detail_text)
              }
            Log.i("Check","All Empty or Any Empty")
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(edEmail.value!!.trim()).matches())
        {
            //dialogAlert.value = R.string.please_enter_the_valid_email
            viewModelScope.launch {
                eventChannel.send(R.string.please_enter_the_valid_email)
            }
            Log.i("Check","Invalid Email")
        }
        else if(edPassword.value?.length!! < 5)
        {
            //dialogAlert.value = R.string.please_enter_password_more_than_5_character
            viewModelScope.launch {
                eventChannel.send(R.string.please_enter_password_more_than_5_character)
            }
            Log.i("Check","Password Less Than 5 char")
        }
        else
        {
            //dialogAlert.value = R.string.ok
            Log.i("Check","We can move foreword")
            viewModelScope.launch {
               loginEventChannel.send(true)
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        Log.i("RegisterViewModal", "RegisterViewModal destroyed!")
    }

}

