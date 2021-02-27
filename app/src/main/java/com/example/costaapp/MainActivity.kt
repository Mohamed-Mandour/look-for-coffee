package com.example.costaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.costaapp.model.Venue
import com.example.costaapp.repository.VenueRepository
import com.example.costaapp.repository.VenueRepositoryImpl
import com.example.costaapp.viewAdpater.VenueAdapter
import com.example.costaapp.viewModel.MainViewModel
import com.example.costaapp.viewModel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var venueRepository: VenueRepository
    private val adapter = VenueAdapter(mutableListOf())
    private var venueList = mutableListOf<Venue>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        venueRepository = VenueRepositoryImpl()
        val mainViewModelFactory = MainViewModelFactory(venueRepository)
        venueRecyclerView.adapter = adapter
        venueRecyclerView.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel::class.java)
        viewModel.getAllVenue().observe(this, { item ->
            for(venue in item){
                venue.venue?.let { venueList.add(it) }
            }
            adapter.setVenue(venueList)
        })

        lifecycleScope.launch {
            venueRepository.getSavedVenue().observe(this@MainActivity, Observer {

            })}
        }
}