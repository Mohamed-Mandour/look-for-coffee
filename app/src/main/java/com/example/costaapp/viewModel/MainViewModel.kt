package com.example.costaapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.costaapp.model.Item
import com.example.costaapp.repository.VenueRepository

class MainViewModel(private val repository: VenueRepository): ViewModel() {

    fun getAllVenue(): LiveData<List<Item>> {
        return repository.getVenue()
    }

    fun getVenueLocation(): LiveData<String> {
        return repository.getVenueLocation()
    }

    fun searchForVenue(query: String): LiveData<List<Item>> {
        return repository.searchVenue(query)
    }
}