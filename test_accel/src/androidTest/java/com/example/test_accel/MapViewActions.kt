package com.example.test_accel

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import org.hamcrest.Matcher

class MapViewScaleAction(k_: Int) : ViewAction {
    val k = k_
    override fun getDescription(): String {
        return "Данный класс нужен для обертки масштабирования в ViewAction"
    }

    override fun getConstraints(): Matcher<View> {
        return isAssignableFrom(MapView::class.java)
    }

    override fun perform(uiController: UiController?, view: View?) {
        val map = view!! as MapView
        map.setScale(k)
    }

}