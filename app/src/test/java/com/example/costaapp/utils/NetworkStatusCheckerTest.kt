package com.example.costaapp.utils

import android.net.ConnectivityManager
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
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
        val callback = mock<() -> Unit>()
        networkChangeReceiver.checkConnectionToInternet { callback }
        // Then
        verifyZeroInteractions(callback)
    }

    @Test
    fun `Given there is internet, When checking to see if there connection,  action taken`() {
        // Given
        given(networkChangeReceiver.hasInternetConnection()).willReturn(true)
        // When
        val callback = mock<() -> Unit>()
        networkChangeReceiver.checkConnectionToInternet { callback }
        // Then
        verify(networkChangeReceiver, times(1)).checkConnectionToInternet { callback }
    }

}