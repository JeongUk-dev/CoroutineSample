package com.jaydev.coroutinesample

import android.app.Application
import com.jaydev.coroutinesample.room.DataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        DataBase.getInstance(this).dataDao()
    }

}