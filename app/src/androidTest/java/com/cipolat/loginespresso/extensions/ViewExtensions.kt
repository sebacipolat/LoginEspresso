package com.cipolat.loginespresso.extensions

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers

fun Int.makeClick(){
    Espresso.onView(ViewMatchers.withId(this)).perform(ViewActions.click())
}