package com.example.costaapp

import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.costaapp.model.Item
import com.example.costaapp.model.Reason
import com.example.costaapp.model.Venue
import com.example.costaapp.repository.VenueRepository
import com.nhaarman.mockitokotlin2.isNull
import com.nhaarman.mockitokotlin2.whenever
import okhttp3.internal.wait
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.java.KoinJavaComponent.inject

private val VENUE = Venue(
    1,
    "52764ec8498e83fe786a0595",
    "Tea at the Corinthia Hotel",
    true,
    null
)
private val REASON = Reason(
    emptyList()
)


@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    private val mockRepository: VenueRepository by inject(
        VenueRepository::class.java,
        null, LazyThreadSafetyMode.SYNCHRONIZED,
        null)

    private val item = MutableLiveData<List<Item>>()

    @Before
    fun setUp() {
//        declareMock<VenueRepository>()
    }

    @Test
    fun onLaunchVenueNameIsDisplayed() {
        item.postValue(listOf(VENUE, REASON) as List<Item>?)
        whenever(mockRepository.getVenue()).thenReturn(item)

        ActivityScenario.launch(MainActivity::class.java).wait()
        Espresso.onView(withId(R.id.venue_name))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun onLaunchVenueImageIsNotDisplayed() {
        item.postValue(listOf(VENUE, REASON) as List<Item>?)
        whenever(mockRepository.getVenue()).thenReturn(item)
        ActivityScenario.launch(MainActivity::class.java).wait()
        Espresso.onView(withId(R.id.venue_image_logo))
            .check(ViewAssertions.matches(isNull()))
    }

    @Test
    fun onLaunchVenueSummaryIsDisplayed() {
        item.postValue(listOf(VENUE, REASON) as List<Item>?)
        whenever(mockRepository.getVenue()).thenReturn(item)
        ActivityScenario.launch(MainActivity::class.java).wait()
        Espresso.onView(withId(R.id.venue_summary))
            .check(ViewAssertions.matches(isDisplayed()))
    }
}