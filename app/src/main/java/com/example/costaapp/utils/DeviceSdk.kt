package com.example.costaapp.utils

import android.os.Build

class DeviceSdk(private val sdkInt: Int = Build.VERSION.SDK_INT) {

    val releaseName: String
        get() = Build.VERSION.RELEASE

    val isApi17AndAbove: Boolean
        get() = sdkInt >= 17

    val isApi18AndAbove: Boolean
        get() = sdkInt >= 18

    val isApi19AndAbove: Boolean
        get() = sdkInt >= 19

    val isApi20AndAbove: Boolean
        get() = sdkInt >= 20

    val isApi21AndAbove: Boolean
        get() = sdkInt >= 21

    val isApi22AndAbove: Boolean
        get() = sdkInt >= 22

    val isApi23AndAbove: Boolean
        get() = sdkInt >= 23

    val isApi24AndAbove: Boolean
        get() = sdkInt >= 24

    val isApi26AndAbove: Boolean
        get() = sdkInt >= 26

    val isApi28AndAbove: Boolean
        get() = sdkInt >= 28

    val isApi29AndAbove: Boolean
        get() = sdkInt >= 29

    val isApi30AndAbove: Boolean
        get() = sdkInt >= 30
}
