package com.example.costaapp

import androidx.multidex.MultiDexApplication
import com.example.costaapp.database.VenueDatabase
import com.example.costaapp.repository.LocationRepository
import com.example.costaapp.repository.LocationRepositoryImpl
import com.example.costaapp.utils.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

lateinit var db: VenueDatabase
lateinit var locationRepository: LocationRepository
class App : MultiDexApplication() {

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
        startKoin {
            // declare Android context
            androidContext(this@App)
            // declare used modules
            modules(appModule)
        }
    }
}