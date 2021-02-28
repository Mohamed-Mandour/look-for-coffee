package com.example.costaapp.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.costaapp.model.Venue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.nhaarman.mockitokotlin2.mock
import junit.framework.Assert.assertTrue
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.verify

private val VENUE_ONE = Venue(
    1,
    "52764ec8498e83fe786a0595",
    "Tea at the Corinthia Hotel",
    true,
    null
)

private val VENUE_TWO = Venue(
    2,
    "4cdadd2dc409b60cac66d11a",
    "Notes Music & Coffee",
    false,
    null
)

private const val firstVenueName = "Tea at the Corinthia Hotel"
private const val secondVenueName = "Notes Music & Coffee"

class VenueDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var venueDatabase: VenueDatabase
    private lateinit var venueDao: VenueDao

    @Before
    fun initDb() {
        venueDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            VenueDatabase::class.java
        ).build()

        venueDao = venueDatabase.venueDao()
    }

    @After
    fun closeDb() {
        venueDatabase.close()
    }


    @Test
    fun getAllReturnsEmptyList() {
        val testObserver: Observer<List<Venue>> = mock()
        venueDao.getAll().observeForever(testObserver)
        verify(testObserver).onChanged(emptyList())
    }

    @Test
     fun insertVenueOperationSavesDate() {
        venueDao.insert(VENUE_ONE, VENUE_TWO)
        val testObserver: Observer<List<Venue>> = mock()
        venueDao.getAll().observeForever(testObserver)

        val listClass = ArrayList::class.java as Class<ArrayList<Venue>>
        val argumentCaptor = ArgumentCaptor.forClass(listClass)
        verify(testObserver).onChanged(argumentCaptor.capture())
        assertTrue(argumentCaptor.value.size > 0)
        assertTrue(argumentCaptor.value.size == 2)
    }


    @Test
    fun getAllRetrievesData() {
        venueDao.insert(VENUE_ONE, VENUE_TWO)

        val testObserver: Observer<List<Venue>> = mock()
        venueDao.getAll().observeForever(testObserver)

        val listClass = ArrayList::class.java as Class<ArrayList<Venue>>
        val argumentCaptor = ArgumentCaptor.forClass(listClass)
        verify(testObserver).onChanged(argumentCaptor.capture())
        val capturedArgument = argumentCaptor.value
        assertTrue(capturedArgument.isNotEmpty())
        assertTrue(capturedArgument[0].name == firstVenueName)
        assertTrue(capturedArgument[1].name == secondVenueName)
    }

}