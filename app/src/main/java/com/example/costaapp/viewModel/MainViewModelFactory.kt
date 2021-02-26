package com.example.costaapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.costaapp.repository.VenueRepository

class MainViewModelFactory(private val venueRepository: VenueRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(VenueRepository::class.java).newInstance(venueRepository)
    }
}