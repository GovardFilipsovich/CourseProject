package com.example.test_accel

import android.graphics.PointF
import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.`ViewMatchers$WithIdMatcher-IA`
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    var activityActivityTestRule: ActivityTestRule<MainActivity?>? = ActivityTestRule(
        MainActivity::class.java
    )
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.test_accel", appContext.packageName)
    }

    // Данный тест нужен для проверки масштабирования плана
    @Test
    fun ScaleTesting(){
        //onView(withId(R.id.map_name)).check(matches(withText("test")))
        //onView(withId(R.id.map_name)).perform(click())
        repeat(20){
            onView(withId(R.id.mapView)).perform(MapViewScaleAction(-10+it))
            Log.i("testing", "Test num " + it + " scale: " + (-10+it))
            Thread.sleep(400)
        }
    }

    @Test
    fun MarkerTesting(){
        var p_arr = arrayListOf<PointF>(PointF(0f,0f),
            PointF(0f, 30f), PointF(10f, 30f), PointF(20f, 30f),
            PointF(30f, 30f)
        )
        p_arr.forEach{
            Log.i("testing", it.toString())
            onView(withId(R.id.mapView)).perform(MapViewSetMarkerAction(it))
            Thread.sleep(500)
        }


    }





}