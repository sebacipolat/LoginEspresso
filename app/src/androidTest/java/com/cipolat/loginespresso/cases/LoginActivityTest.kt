package com.cipolat.loginespresso.cases

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.cipolat.loginespresso.data.CaseValues
import com.cipolat.loginespresso.matcher.TextInputLayoutMatchers
import com.cipolat.loginespresso.matcher.UiHelper
import com.cipolat.loginespresso.R
import com.cipolat.loginespresso.ui.login.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @Rule
    var mActivityRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun checkEmptyField() {
        clickLoginBtn()
        val emailStr: String = mActivityRule.activity.getString(R.string.empty_email_error)
        onView(withId(R.id.inputUserLayout)).check(matches(TextInputLayoutMatchers.hasTextInputLayoutErrorText(emailStr)))
        val passStr: String = mActivityRule.activity.getString(R.string.empty_pass_error)
        onView(withId(R.id.inputPasswInputLayout)).check(matches(TextInputLayoutMatchers.hasTextInputLayoutErrorText(passStr)))
    }

    @Test
    fun checkInvalidEmail() {
        onView(withId(R.id.inputUser)).perform(ViewActions.typeText(CaseValues.INVALID__EMAIL), pressImeActionButton())
        val emailStr: String = mActivityRule.activity.getString(R.string.invalid_email_error)
        onView(withId(R.id.inputUserLayout)).check(matches(TextInputLayoutMatchers.hasTextInputLayoutErrorText(emailStr)))
    }

    @Test
    fun checkWrongCredentials() {
        fillLoginData(CaseValues.INVALID__USERNAME, CaseValues.INVALID__PASSWORD)
        clickLoginBtn()
        UiHelper.isDialogSingleMessageDisplayed(mActivityRule.getActivity().getString(R.string.invalidCredentials_title))
        UiHelper.checkifInputEmpty(R.id.inputUser)
        UiHelper.checkifInputEmpty(R.id.inputPassw)
    }

    private fun clickLoginBtn() {
        UiHelper.clickBtn(R.id.loginBtn)
    }

    private fun fillLoginData(username: String, passw: String) {
        UiHelper.fillInputText(R.id.inputUser, username)
        UiHelper.fillInputText(R.id.inputPassw, passw)
    }
}