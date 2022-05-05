package com.example.myfirstapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myfirstapplication.children.ChildrenData

@Database(entities = [ChildrenData::class],version = 1 ,exportSchema =false)
abstract class MyAppDataBase : RoomDatabase() {

    abstract fun ChildDao() :AddChildDao

    companion object{

        @Volatile
        private var INSTANCE :MyAppDataBase? = null

        fun getDataBase(context: Context):MyAppDataBase{

            if(INSTANCE == null)
            {
                synchronized(this){
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!

        }

        private fun buildDatabase(context: Context): MyAppDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                MyAppDataBase::class.java,
                "word_database"
            ).build()
        }
    }
}