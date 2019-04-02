package com.jaydev.coroutinesample

import android.app.Application
import com.jaydev.coroutinesample.room.Data
import com.jaydev.coroutinesample.room.DataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val database = DataBase.getInstance(this).dataDao()


        val list = List(10) { index -> Data("title$index", "description$index") }

        CoroutineScope(Dispatchers.IO).launch {
            database.clear()
            database.insertAll(list)
        }
    }
}