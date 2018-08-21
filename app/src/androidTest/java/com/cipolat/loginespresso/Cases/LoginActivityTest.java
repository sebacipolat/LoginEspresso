package com.cipolat.loginespresso.Cases;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.cipolat.loginespresso.Data.CaseValues;
import com.cipolat.loginespresso.Login.MainActivity;
import com.cipolat.loginespresso.Matcher.TextInputLayoutMatchers;
import com.cipolat.loginespresso.Matcher.UiHelper;
import com.cipolat.loginespresso.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkEmptyField() {
        clickLoginBtn();
        String emailStr = mActivityRule.getActivity().getString(R.string.empty_email_error);
        onView(withId(R.id.inputUserInputLayout)).check(matches(TextInputLayoutMatchers.hasTextInputLayoutErrorText(emailStr)));

        String passStr = mActivityRule.getActivity().getString(R.string.empty_pass_error);
        onView(withId(R.id.inputPasswInputLayout)).check(matches(TextInputLayoutMatchers.hasTextInputLayoutErrorText(passStr)));
    }

    @Test
    public void checkInvalidEmail() {
        onView(withId(R.id.inputUser)).perform(ViewActions.typeText(CaseValues.INVALID__EMAIL), pressImeActionButton());
        String emailStr = mActivityRule.getActivity().getString(R.string.invalid_email_error);
        onView(withId(R.id.inputUserInputLayout)).check(matches(TextInputLayoutMatchers.hasTextInputLayoutErrorText(emailStr)));
    }

    @Test
    public void checkWrongCredentials() {
        fillLoginData(CaseValues.INVALID__USERNAME, CaseValues.INVALID__PASSWORD);
        clickLoginBtn();
        UiHelper.isDialogSingleMessageDisplayed(mActivityRule.getActivity().getString(R.string.invalidCredentials_title));
        UiHelper.checkifInputEmpty(R.id.inputUser);
        UiHelper.checkifInputEmpty(R.id.inputPassw);
    }


    private void clickLoginBtn() {
        UiHelper.clickBtn(R.id.loginBtn);
    }

    private void fillLoginData(String username, String passw) {
        UiHelper.fillInputText(R.id.inputUser, username);
        UiHelper.fillInputText(R.id.inputPassw, passw);
    }


}
