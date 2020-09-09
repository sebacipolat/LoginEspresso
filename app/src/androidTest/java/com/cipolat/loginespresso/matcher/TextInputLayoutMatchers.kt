package com.cipolat.loginespresso.matcher

import android.view.View
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.internal.matchers.TypeSafeMatcher

object TextInputLayoutMatchers {
    fun hasTextInputLayoutHintText(expectedErrorText: String): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun matchesSafely(view: View): Boolean {
                if (view !is TextInputLayout) {
                    return false
                }
                val error: CharSequence = (view as TextInputLayout).hint ?: return false
                val hint = error.toString()
                return expectedErrorText == hint
            }

            override fun describeTo(description: Description) {}
        }
    }

    fun hasTextInputLayoutErrorText(expectedErrorText: String): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun matchesSafely(view: View): Boolean {
                if (view !is TextInputLayout) {
                    return false
                }
                val error: CharSequence = (view as TextInputLayout).error ?: return false
                val hint = error.toString()
                return expectedErrorText == hint
            }

            override fun describeTo(description: Description) {}
        }
    }
}