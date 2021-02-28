package com.example.costaapp.repository

import com.example.costaapp.location.DeviceLocation

interface LocationRepository {

    val location: DeviceLocation?

    fun requestLocationUpdate()
}