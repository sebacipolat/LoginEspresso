package com.cipolat.loginespresso.Matcher;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class UiHelper {

    /**
     * Fill EditText
     * @param viewID
     * @param text
     */
    public static void fillInputText(int viewID, String text) {
        onView(withId(viewID)).perform(ViewActions.typeText(text), pressImeActionButton());
    }

    /***
     * Click button
     * @param viewID
     */
    public static void clickBtn(int viewID) {
        onView(ViewMatchers.withId(viewID)).perform(click());
    }

    /***
     * Check if an EditText has Empty Value
     * @param inputId
     */
    public static void checkifInputEmpty(int inputId) {
        onView(withId(inputId)).check(matches(withText("")));
    }

    /**
     * Check if an a AlertDialog with a text value is displayed
     * @param text
     */
    public static void isDialogSingleMessageDisplayed(String text) {
        onView(withText(text)).check(matches(isDisplayed()));
        onView(withId(android.R.id.button1)).perform(click());
    }
}
