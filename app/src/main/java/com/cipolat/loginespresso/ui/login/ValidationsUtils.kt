package com.cipolat.loginespresso.ui.login

import android.text.TextUtils
import android.util.Patterns

    fun String.isValidEmail(): Boolean {
        return !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }
