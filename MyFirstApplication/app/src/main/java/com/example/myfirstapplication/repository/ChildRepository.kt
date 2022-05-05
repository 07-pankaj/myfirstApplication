package com.example.myfirstapplication.repository

import android.util.Log
import com.example.myfirstapplication.AddChildDao
import com.example.myfirstapplication.children.ChildrenData
import kotlinx.coroutines.flow.Flow

class ChildRepository(private val childDao: AddChildDao) {

    suspend fun insertChildData(data: ChildrenData){
        Log.i("insert","repository")
        childDao.insertChildData(data)
        Log.i("insert","inserted in dao")
    }

    suspend fun deleteChildData(data: ChildrenData) = childDao.deleteChildData(data)

    suspend fun updateChildData(data: ChildrenData) = childDao.updateChildData(data)

    fun getAllChildData(): Flow<List<ChildrenData>> = childDao.getAllChildData()



}