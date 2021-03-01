package com.example.costaapp.utils

import android.net.ConnectivityManager
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NetworkStatusCheckerTest {

    @Mock
    private lateinit var mockConnectivityManager: ConnectivityManager

    private lateinit var networkChangeReceiver: NetworkStatusChecker

    @Before
    fun setUp() {
        networkChangeReceiver = spy(NetworkStatusChecker(mockConnectivityManager))
    }

    @Test
    fun `Given there is no internet, When checking to see if there connection, no action taken`() {
        // Given
        given(networkChangeReceiver.hasInternetConnection()).willReturn(false)
        // When
        networkChangeReceiver.checkConnectionToInternet {  calls(0) }
        // Then
        verify(networkChangeReceiver, times(1)).checkConnectionToInternet {  calls(1) }
    }

    @Test
    fun `Given there is internet, When checking to see if there connection,  action taken`() {
        // Given
        given(networkChangeReceiver.hasInternetConnection()).willReturn(true)
        // When

        networkChangeReceiver.checkConnectionToInternet { calls(1) }
        // Then
        verify(networkChangeReceiver, times(1)).checkConnectionToInternet {  calls(1) }
    }

}