package com.example.costaapp.utils

import com.example.costaapp.networkcall.FoursquareApi
import com.example.costaapp.repository.VenueRepository
import com.example.costaapp.repository.VenueRepositoryImpl
import com.example.costaapp.viewModel.MainViewModel
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {

    single<VenueRepository> { VenueRepositoryImpl(get()) }

    single<FoursquareApi> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.foursquare.com/v2/venues/explore?client_id=ULEEKFKJW1NOK3B4SRCHKSJI5D2DL1EUHNGW3OHFWI4OOXXD&client_secret=LZE1IHQO05BX05OSYZMONQFMJR4PWSGLWTREIK4SGCA4EE5R&v=20180323&limit=10&ll=51.510288,-0.056160&query=coffee")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        retrofit.create(FoursquareApi::class.java)
    }

    viewModel { MainViewModel(get()) }
}