package com.example.costaapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.costaapp.location.DeviceLocation
import com.example.costaapp.location.PermissionChecker
import com.example.costaapp.location.PermissionCheckerImpl
import com.example.costaapp.model.Reason
import com.example.costaapp.model.Venue
import com.example.costaapp.networkcall.RetrofitClient
import com.example.costaapp.repository.LocationRepositoryImpl
import com.example.costaapp.repository.VenueRepository
import com.example.costaapp.repository.VenueRepositoryImpl
import com.example.costaapp.viewAdpater.VenueAdapter
import com.example.costaapp.viewModel.MainViewModel
import com.example.costaapp.viewModel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

private const val LOCATION_REQUEST_CODE = 99
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var venueRepository: VenueRepository
    private lateinit var retrofitClient: RetrofitClient
    private val locationRepository = LocationRepositoryImpl
    private val adapter = VenueAdapter(mutableListOf(), mutableListOf())
    private var location: DeviceLocation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setVenueAdapter()
        requestLocationPermission()
    }

    private fun setVenueAdapter() {
        venueRecyclerView.adapter = adapter
        venueRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun requestCallToApi() {
        locationRepository.initialize(applicationContext)
        location = locationRepository.location
        retrofitClient = RetrofitClient(location)
        getDataFromRepo()
    }

    private fun getDataFromRepo() {
        val venueList = mutableListOf<Venue>()
        val reasonList = mutableListOf<Reason>()
        venueRepository = VenueRepositoryImpl(location)
        val mainViewModelFactory = MainViewModelFactory(venueRepository)
        viewModel = ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel::class.java)
        viewModel.getAllVenue().observe(this, { item ->
            for (venue in item) {
                venue.venue?.let { venueList.add(it) }
                venue.reasons?.let { reasonList.add(it) }
            }
            adapter.setVenue(venueList, reasonList)
        })
    }

    private fun requestLocationPermission() {
        val permissionChecker: PermissionChecker = PermissionCheckerImpl(application)
        val hasAnyLocationPermissions = permissionChecker.hasAnyLocationPermissions
        Log.d(TAG, "hasAnyLocationPermissions: $hasAnyLocationPermissions")
        if (!hasAnyLocationPermissions) {
            makeLocationPermissionRequest()
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            val dialog = createLocationRequestDialog()
            dialog?.show()
        } else {
            makeLocationPermissionRequest()
        }
    }

    private fun makeLocationPermissionRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            LOCATION_REQUEST_CODE
        )
    }

    private fun createLocationRequestDialog(): AlertDialog? {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Permission to access the Location is required for this app to look for the coffee shops.")
            .setTitle("Permission required")

        builder.setPositiveButton(
            "OK"
        ) { _, _ ->
            Log.d(TAG, "Clicked")
            makeLocationPermissionRequest()
        }

        return builder.create()
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    makeText(
                        applicationContext,
                        "",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    requestCallToApi()
                    locationRepository.requestLocationUpdate()
                }
            }
        }
    }
}