package com.cipolat.loginespresso;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.cipolat.loginespresso.Login.MainActivity;
import com.cipolat.loginespresso.Matcher.TextInputLayoutMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkEmptyField() {
        onView(withId(R.id.loginBtn))
                .perform(ViewActions.click());

        String emailStr = mActivityRule.getActivity().getString(R.string.empty_email_error);
        onView(withId(R.id.inputUserInputLayout)).check(matches(TextInputLayoutMatchers
                .hasTextInputLayoutErrorText(emailStr)));

        String passStr = mActivityRule.getActivity().getString(R.string.empty_pass_error);
        onView(withId(R.id.inputPasswInputLayout)).check(matches(TextInputLayoutMatchers
                .hasTextInputLayoutErrorText(passStr)));

    }


}
