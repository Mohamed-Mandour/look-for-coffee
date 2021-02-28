package com.example.costaapp.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import com.example.costaapp.utils.DeviceSdk

private const val TAG = "PermissionCheckerImpl"
class PermissionCheckerImpl(private val context: Context?, private val sdk: DeviceSdk): PermissionChecker {

    override val hasAnyLocationPermissions: Boolean
        @SuppressLint("NewApi")
        get() {

            val coarsePermission = if (sdk.isApi23AndAbove) {
                context?.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
            } else {
                Log.d(TAG, "android Sdk < 23")
            }

            val finePermission = if (sdk.isApi23AndAbove) {
                context?.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            } else {
                Log.d(TAG, "android Sdk < 23")
            }
            return coarsePermission == PackageManager.PERMISSION_GRANTED || finePermission == PackageManager.PERMISSION_GRANTED
        }

    override val hasFineLocationPermission: Boolean
        @SuppressLint("NewApi")
        get() {
            val finePermission = if (sdk.isApi23AndAbove) {
                context?.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            } else {
                Log.d(TAG, "android Sdk < 23")
            }
            return finePermission == PackageManager.PERMISSION_GRANTED
        }
}