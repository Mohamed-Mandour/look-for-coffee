package com.example.costaapp

import android.app.Application
import com.example.costaapp.database.VenueDatabase

lateinit var db: VenueDatabase

class App : Application() {

    companion object {
        lateinit var INSTANCE: App
    }

    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        db = VenueDatabase.getInstance(this)
    }
}