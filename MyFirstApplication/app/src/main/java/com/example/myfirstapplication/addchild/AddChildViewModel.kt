package com.example.myfirstapplication.addchild

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstapplication.MyAppDataBase
import com.example.myfirstapplication.children.ChildrenData
import com.example.myfirstapplication.repository.ChildRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddChildViewModel(application: Application) : AndroidViewModel(application) {

   var id:Int = 0
   var edChildName = MutableLiveData<String>()
   var edChildAge = MutableLiveData<String>()
   private var childManageOption = MutableLiveData<String>()
   private var childImageUri = String()


   private val repository:ChildRepository

   init {
      val childDao = MyAppDataBase.getDataBase(application).ChildDao()
       repository = ChildRepository(childDao)
   }

    fun insertChildData()
   {
      viewModelScope.launch(Dispatchers.IO) {
          repository.insertChildData(ChildrenData(id,edChildName.value!!,edChildAge.value!!,childManageOption.value!!,childImageUri))
         }
    }

   fun updateChildData()
   {
      viewModelScope.launch(Dispatchers.IO) {
        // Log.i("insert"," inserted data $id ${edChildName.value!!} ${edChildAge.value!!} ${childManageOption.value!!}, $childImageUri")
         repository.updateChildData(ChildrenData(id,edChildName.value!!,edChildAge.value!!,childManageOption.value!!,childImageUri))
      }
   }


   fun setChildManageOption(manageOption:String?)
   {
      manageOption?.let {
         childManageOption.value = it
      }
   }

   fun setChildImageUri(uri:String?)
   {
      uri?.let {
         childImageUri = it
      }
//      "https://flyclipart.com/thumb2/child-boy-clipart-collection-889164.png"
   }



}