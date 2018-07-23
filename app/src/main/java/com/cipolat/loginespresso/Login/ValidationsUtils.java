package com.cipolat.loginespresso.Login;

import android.text.TextUtils;
import android.util.Patterns;

public class ValidationsUtils {

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
