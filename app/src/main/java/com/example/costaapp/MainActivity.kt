package com.example.costaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.costaapp.repository.VenueRepository
import com.example.costaapp.repository.VenueRepositoryImpl
import com.example.costaapp.viewModel.MainViewModel
import com.example.costaapp.viewModel.MainViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var viewModel: MainViewModel
    private lateinit var venueRepository: VenueRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        venueRepository = VenueRepositoryImpl()
        val mainViewModelFactory = MainViewModelFactory(venueRepository)
        viewModel = ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel::class.java)

        viewModel.getVenueLocation().observe(this, { location ->
            Log.d("MANDO", "location: $location")
        })

        viewModel.getAllVenue().observe(this, { item ->
            for(venue in item){
                Log.d("MANDO", "venue: ${venue.venue?.name}")
                val categories = venue.venue?.categories
                if (categories != null) {
                    for (icon in categories){
                        Log.d("MANDO", "prefix: ${icon.icon?.prefix+"64"+icon.icon?.suffix}")
                    }
                }
            }
        })

        lifecycleScope.launch {
            venueRepository.getSavedVenue().observe(this@MainActivity, Observer {
                venue ->
                Log.d("MANDO", "getSavedVenue: ${venue[0]}")
            })}
        }
}