package com.example.costaapp.repository

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.util.Log
import com.example.costaapp.location.DeviceLocation
import com.example.costaapp.location.PermissionChecker
import com.example.costaapp.location.PermissionCheckerImpl
import com.example.costaapp.networkcall.RetrofitClient
import com.example.costaapp.utils.DeviceSdk
import com.google.android.gms.location.*

private const val TAG = "LocationRepositoryImpl"

object LocationRepositoryImpl : LocationRepository {

    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var locationRequest: LocationRequest? = null
    private var mLocation: DeviceLocation? = null
    private var permissionChecker: PermissionChecker? = null
    private var retrofitClient: RetrofitClient? = null

    fun initialize(context: Context, deviceSdk: DeviceSdk) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        permissionChecker = PermissionCheckerImpl(context, deviceSdk)
        retrofitClient = RetrofitClient(mLocation)
        createLocationRequest()
    }

    private val locationCallback = object : LocationCallback() {

        override fun onLocationResult(locationResult: LocationResult?) {

            if (locationResult == null) {
                return
            }
            for (location in locationResult.locations) {
                if (location != null) {
                    mLocation = DeviceLocation(
                        locationResult.lastLocation.latitude,
                        locationResult.lastLocation.longitude,
                        locationResult.lastLocation.provider
                    )
                }
            }
        }
    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest.create()
        locationRequest?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest?.interval = 10 * 100000
        locationRequest?.fastestInterval = 5 * 1000

    }

    @SuppressLint("MissingPermission")
    override fun requestLocationUpdate() {
        if (permissionChecker?.hasAnyLocationPermissions == false) {
            Log.d(TAG,"Cannot request a new location as we don't have permission.")
            return
        }

        fusedLocationClient?.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    override val location: DeviceLocation? = null
        get() {
            if (field != null) {
                requestLocationUpdate()
            }else {
                getLastLocation()
            }
            return mLocation
        }

    @SuppressLint("MissingPermission", "BinaryOperationInTimber")
    fun getLastLocation() {
        if (permissionChecker?.hasAnyLocationPermissions == false) {
            Log.d(TAG,"No Location permission granted")
            return
        }
        try {
            val lastLocationTask = fusedLocationClient?.lastLocation
            lastLocationTask?.addOnCompleteListener {
                if (lastLocationTask.isComplete && lastLocationTask.isSuccessful) {
                    val location = lastLocationTask.result

                    Log.d(TAG,"lastLocationTask.result ${location?.latitude}")
                    mLocation = location?.latitude?.let { it ->
                        DeviceLocation(
                            it,
                            location.longitude,
                            location.provider
                        )
                    }
                }
            }

        } catch (e: Exception) {
            Log.d(TAG,e.stackTraceToString())
        }
    }
}

