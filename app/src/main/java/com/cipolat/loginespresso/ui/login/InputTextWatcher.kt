package com.cipolat.loginespresso.ui.login

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout

class InputTextWatcher(var inputLayout: TextInputLayout) : TextWatcher {
    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        inputLayout.let {
            inputLayout.error.let { inputLayout.error = null }
        }
    }
}