package com.cipolat.loginespresso.cases

import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.cipolat.loginespresso.R
import com.cipolat.loginespresso.data.CaseValues
import com.cipolat.loginespresso.extensions.hasTextInputLayoutHintError
import com.cipolat.loginespresso.extensions.hasTextInputLayoutHintText
import com.cipolat.loginespresso.extensions.makeClick
import com.cipolat.loginespresso.matcher.TextInputLayoutMatchers
import com.cipolat.loginespresso.matcher.UiHelper
import com.cipolat.loginespresso.ui.login.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityTest {


    @get:Rule
    var activityScenarioRule = activityScenarioRule<LoginActivity>()


    @Test
    fun checkEmptyField() {
        R.id.loginBtn.makeClick()

        val context = InstrumentationRegistry.getInstrumentation().targetContext;

        val emailStr=context.getString(R.string.empty_email_error)
        onView(withId(R.id.inputUserLayout)).check(matches(hasTextInputLayoutHintError(emailStr)))



        /*  //val emailStr: String = it.getString(R.string.empty_email_error)
          val emailStr: String = "it.getString(R.string.empty_email_error)"
          onView(withId(R.id.inputUserLayout)).check(matches(TextInputLayoutMatchers.hasTextInputLayoutErrorText(emailStr)))
          //val passStr: String = it.getString(R.string.empty_pass_error)
          val passStr: String = "it.getString(R.string.empty_pass_error)"
          onView(withId(R.id.inputPasswInputLayout)).check(matches(TextInputLayoutMatchers.hasTextInputLayoutErrorText(passStr)))
*/
    }

    @Test
    fun checkInvalidEmail() {
        activityScenarioRule.scenario.onActivity {
            onView(withId(R.id.inputUser)).perform(ViewActions.typeText(CaseValues.INVALID__EMAIL), pressImeActionButton())
            val emailStr: String = it.getString(R.string.invalid_email_error)
            onView(withId(R.id.inputUserLayout)).check(matches(TextInputLayoutMatchers.hasTextInputLayoutErrorText(emailStr)))
        }
    }

    @Test
    fun checkWrongCredentials() {
        activityScenarioRule.scenario.onActivity {
            fillLoginData(CaseValues.INVALID__USERNAME, CaseValues.INVALID__PASSWORD)
            clickLoginBtn()
            UiHelper.isDialogSingleMessageDisplayed(it.getString(R.string.invalidCredentials_title))
            UiHelper.checkifInputEmpty(R.id.inputUser)
            UiHelper.checkifInputEmpty(R.id.inputPassw)
        }
    }

    private fun clickLoginBtn() {
        UiHelper.clickBtn(R.id.loginBtn)
    }

    private fun fillLoginData(username: String, passw: String) {
        UiHelper.fillInputText(R.id.inputUser, username)
        UiHelper.fillInputText(R.id.inputPassw, passw)
    }
}