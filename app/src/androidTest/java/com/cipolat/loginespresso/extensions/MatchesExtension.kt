package com.cipolat.loginespresso.extensions

import android.view.View
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.TypeSafeMatcher

fun hasTextInputLayoutHintError(expectedErrorText: String) = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: org.hamcrest.Description?) {}

    override fun matchesSafely(item: View?): Boolean {
        if (item !is TextInputLayout) return false
        val error = item.error ?: return false
        val hint = error.toString()
        return expectedErrorText == hint
    }
}


fun hasTextInputLayoutHintText(expectedErrorText: String) = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: org.hamcrest.Description?) {}

    override fun matchesSafely(item: View?): Boolean {
        if (item !is TextInputLayout) return false
        val error = item.editText?.text ?: return false
        val hint = error.toString()
        return expectedErrorText == hint
    }
}