package com.example.costaapp.database

import com.example.costaapp.model.Category
import com.nhaarman.mockito_kotlin.spy
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VenueConverterTest {


    private lateinit var venueConverter: VenueConverter

    @Before
    fun setUp() {
        venueConverter = spy(VenueConverter())
    }

    @Test
    fun `Given string category is null, When convert string to category list, it return empty list`() {
        val emptyList = emptyList<Category>()
        // When
        val stringToVenueList = VenueConverter.stringToVenueList(null)
        // Then
        assertThat(stringToVenueList).isEqualTo(emptyList)
    }


    @Test
    fun `Given list category is null, When convert list category to string, it return empty list`() {
        val emptyList = emptyList<Category>()
        // When
        val venueListToString = VenueConverter.venueListToString(null)
        // Then
        assertThat(venueListToString).isEqualTo("$emptyList")
    }
}