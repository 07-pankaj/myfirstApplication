package com.example.myfirstapplication

import androidx.room.*
import com.example.myfirstapplication.children.ChildrenData
import kotlinx.coroutines.flow.Flow

@Dao
interface AddChildDao {

    @Insert
    suspend fun insertChildData(childData:ChildrenData)

    @Query("SELECT * FROM childrenData_table")
   fun getAllChildData(): Flow<List<ChildrenData>>

   @Delete
   suspend fun deleteChildData(childData: ChildrenData)

   @Update
   suspend fun updateChildData(childData: ChildrenData)
}