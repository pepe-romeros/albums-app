package com.example.albumlist.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.albumlist.R
import com.example.albumlist.utils.AppIdlingResource
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(AppIdlingResource.countingIdlingResource)
    }

    @Test
    fun mainActivityTest() {
        onView(withId(R.id.albumsRv))
            .check(
                matches(
                    withViewAtPosition(
                        1,
                        hasDescendant(
                            allOf(withId(R.id.albumIdTv), isDisplayed())
                    )
                )
            )
        )
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(AppIdlingResource.countingIdlingResource)
    }

    private fun withViewAtPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView)
            }
        }
    }

}
