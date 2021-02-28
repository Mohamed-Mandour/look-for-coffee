package com.example.costaapp

import android.app.Application
import com.example.costaapp.database.VenueDatabase
import com.example.costaapp.repository.LocationRepository
import com.example.costaapp.repository.LocationRepositoryImpl

lateinit var db: VenueDatabase
lateinit var locationRepository: LocationRepository
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
        locationRepository = LocationRepositoryImpl
        locationRepository.location
        db = VenueDatabase.getInstance(this)
    }
}