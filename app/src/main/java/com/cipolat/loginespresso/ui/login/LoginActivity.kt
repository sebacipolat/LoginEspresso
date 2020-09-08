package com.cipolat.loginespresso.ui.login

import android.os.Bundle
import android.view.View.OnFocusChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cipolat.loginespresso.R
import com.cipolat.loginespresso.ui.dialog.DialogFactory
import com.cipolat.loginespresso.ui.extension.hideKeyboard
import com.cipolat.loginespresso.ui.login.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        inputUser.addTextChangedListener(InputTextWatcher(inputUserLayout))
        inputUser.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                validateEmailInput(inputUser.text.toString())
            }
        }
        loginBtn.setOnClickListener { signIn() }
    }


    private fun signIn() {
        this.hideKeyboard()
        val validEmail = validateEmailInput(inputUser.text.toString())
        val validPassword = validatePasswInput(inputPassw.text.toString())
        if (validEmail && validPassword) {
            if (!checkLoginData(inputUser!!.text.toString(), inputPassw.text.toString())) {
                showErrorMessage()
                cleanInputs()
            } else showOkMessage()
        }
    }

    private fun getEmojiByUnicode(unicode: Int): String {
        return String(Character.toChars(unicode))
    }

    private fun showErrorMessage() {
        val unicode = 0x270B
        val message = """
            ${getString(R.string.invalidCredentials_message)}
            ${getEmojiByUnicode(unicode)}
            """.trimIndent()
        DialogFactory.showInfoDialog(getString(R.string.invalidCredentials_title), message, this)
    }

    private fun showOkMessage() {
        val unicode = 0x1F44D
        val message = """
            ${getString(R.string.validCredentials_message)}
            ${getEmojiByUnicode(unicode)}
            """.trimIndent()
        DialogFactory.showInfoDialog(getString(R.string.validCredentials_title), message, this)
    }

    private fun cleanInputs() {
        inputUser.setText("")
        inputPassw.setText("")
    }

    private fun validateEmailInput(email: String): Boolean {
        var status = false
        if (email.isNotEmpty()) {
            if (!email.isValidEmail()) inputUserLayout.error = getString(R.string.invalid_email_error) else status = true
        } else inputUserLayout.error = getString(R.string.empty_email_error)
        return status
    }

    private fun validatePasswInput(password: String): Boolean {
        var status = false
        if (password.isNotEmpty()) status = true else inputPasswInputLayout.error = getString(R.string.empty_pass_error)
        return status
    }

    private fun checkLoginData(username: String, passw: String): Boolean {
        return username == "sebastiancipolat@gmail.com" && passw == "android2018"
    }
}