package com.example.costaapp.location

import android.Manifest
import android.content.Context
import com.example.costaapp.utils.DeviceSdk
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.spy
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

private const val PERMISSION_GRANTED = 0
private const val PERMISSION_DENIED = 1

@RunWith(MockitoJUnitRunner::class)
class PermissionCheckerImplTest{

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockDeviceSdk: DeviceSdk

    private lateinit var permissionChecker: PermissionChecker

    @Before
    fun setUp() {
        permissionChecker = spy(PermissionCheckerImpl(mockContext, mockDeviceSdk))
    }

    @Test
    fun `Given device sdk is below 23, When checking the permission with true, it return true`() {
        // Given
        given(mockDeviceSdk.isApi23AndAbove).willReturn(true)
        // When
        val hasAnyLocationPermissions = permissionChecker.hasAnyLocationPermissions
        // Then
        assert(hasAnyLocationPermissions)
    }

    @Test
    fun `Given device sdk is below 23, When checking the permission with false, it return true`() {
        // Given
        given(mockDeviceSdk.isApi23AndAbove).willReturn(false)
        // When
        val hasAnyLocationPermissions = permissionChecker.hasAnyLocationPermissions
        // Then
        assert(hasAnyLocationPermissions)
    }

    @Test
    fun `Given device sdk is above 23, When only permission coarse location granted, it return true`() {
        // Given
        given(mockDeviceSdk.isApi23AndAbove).willReturn(true)
        given(mockContext.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)).willReturn(PERMISSION_GRANTED)
        given(mockContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)).willReturn(PERMISSION_DENIED)
        // When
        val hasAnyLocationPermissions = permissionChecker.hasAnyLocationPermissions
        // Then
        assert(hasAnyLocationPermissions)
    }

    @Test
    fun `Given device sdk is above 23, When only permission fine location granted, it return true`() {
        // Given
        given(mockDeviceSdk.isApi23AndAbove).willReturn(true)
        given(mockContext.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)).willReturn(PERMISSION_DENIED)
        given(mockContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)).willReturn(PERMISSION_GRANTED)
        // When
        val hasAnyLocationPermissions = permissionChecker.hasAnyLocationPermissions
        // Then
        assert(hasAnyLocationPermissions)
    }

    @Test
    fun `Given device sdk is above 23, When both permissions granted, it return true`() {
        // Given
        given(mockDeviceSdk.isApi23AndAbove).willReturn(true)
        given(mockContext.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)).willReturn(PERMISSION_GRANTED)
        given(mockContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)).willReturn(PERMISSION_GRANTED)
        // When
        val hasAnyLocationPermissions = permissionChecker.hasAnyLocationPermissions
        // Then
        assert(hasAnyLocationPermissions)
    }

    @Test
    fun `Given device sdk is above 23, When both permissions denied, it return false`() {
        // Given
        given(mockDeviceSdk.isApi23AndAbove).willReturn(true)
        given(mockContext.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)).willReturn(PERMISSION_DENIED)
        given(mockContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)).willReturn(PERMISSION_DENIED)
        // When
        val hasAnyLocationPermissions = permissionChecker.hasAnyLocationPermissions
        // Then
        assert(!hasAnyLocationPermissions)
    }


    @Test
    fun `Given device sdk is above 23, When fine permission granted, it return true`() {
        // Given
        given(mockDeviceSdk.isApi23AndAbove).willReturn(true)
        given(mockContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)).willReturn(PERMISSION_GRANTED)
        // When
        val hasFineLocationPermission = permissionChecker.hasFineLocationPermission
        // Then
        assert(hasFineLocationPermission)
    }

    @Test
    fun `Given device sdk is above 23, When fine permission denied, it return false`() {
        // Given
        given(mockDeviceSdk.isApi23AndAbove).willReturn(true)
        given(mockContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)).willReturn(PERMISSION_DENIED)
        // When
        val hasFineLocationPermission = permissionChecker.hasFineLocationPermission
        // Then
        assert(!hasFineLocationPermission)
    }

}