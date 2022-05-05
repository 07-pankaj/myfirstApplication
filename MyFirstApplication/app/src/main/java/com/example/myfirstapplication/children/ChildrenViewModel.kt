package com.example.myfirstapplication.children



import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstapplication.MyAppDataBase
import com.example.myfirstapplication.children.ChildrenData
import com.example.myfirstapplication.repository.ChildRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ChildrenViewModel(application: Application):AndroidViewModel(application) {

    private val repository: ChildRepository


    init {
        val childDao = MyAppDataBase.getDataBase(application).ChildDao()
        repository = ChildRepository(childDao)

    }

    fun getAllData(): Flow<List<ChildrenData>> = repository.getAllChildData()

    fun deleteChildData(data:ChildrenData)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteChildData(data)
        }
    }



}

