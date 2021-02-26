package com.example.costaapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.costaapp.model.Item

interface VenueRepository {

   fun getVenue(): MutableLiveData<List<Item>>

    fun getVenueLocation(): LiveData<String>

   fun searchVenue(query: String): LiveData<List<Item>>
}