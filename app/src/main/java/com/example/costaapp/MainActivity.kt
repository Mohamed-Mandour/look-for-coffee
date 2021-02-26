package com.example.costaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.example.costaapp.repository.VenueRepository
import com.example.costaapp.repository.VenueRepositoryImpl
import com.example.costaapp.viewModel.MainViewModel
import com.example.costaapp.viewModel.MainViewModelFactory

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

            }
        })
    }
}