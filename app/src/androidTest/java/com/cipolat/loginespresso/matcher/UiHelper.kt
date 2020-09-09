package com.cipolat.loginespresso.matcher

import android.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*

object UiHelper {
    /**
     * Fill EditText
     * @param viewID
     * @param text
     */
    fun fillInputText(viewID: Int, text: String?) {
        onView(withId(viewID)).perform(ViewActions.typeText(text), pressImeActionButton())
    }

    /***
     * Click button
     * @param viewID
     */
    fun clickBtn(viewID: Int) {
        onView(ViewMatchers.withId(viewID)).perform(click())
    }

    /***
     * Check if an EditText has Empty Value
     * @param inputId
     */
    fun checkifInputEmpty(inputId: Int) {
        onView(withId(inputId)).check(matches(withText("")))
    }

    /**
     * Check if an a AlertDialog with a text value is displayed
     * @param text
     */
    fun isDialogSingleMessageDisplayed(text: String?) {
        onView(withText(text)).check(matches(isDisplayed()))
        onView(withId(R.id.button1)).perform(click())
    }
}