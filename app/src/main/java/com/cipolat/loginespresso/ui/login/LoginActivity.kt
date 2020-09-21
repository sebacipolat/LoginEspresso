package com.cipolat.loginespresso.ui.login

import android.os.Bundle
import android.view.View.OnFocusChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cipolat.loginespresso.R
import com.cipolat.loginespresso.ui.dialog.DialogFactory
import com.cipolat.loginespresso.ui.extension.clear
import com.cipolat.loginespresso.ui.extension.getEmojiByUnicode
import com.cipolat.loginespresso.ui.extension.hideKeyboard
import com.cipolat.loginespresso.ui.login.Emojis.ERROR_EMOJI
import com.cipolat.loginespresso.ui.login.Emojis.PULGAR_EMOJI
import com.cipolat.loginespresso.ui.login.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {
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
        listeLoginStatus()
        loginBtn.setOnClickListener { signIn() }
    }

    private fun listeLoginStatus() {
        val loginObserver = Observer<Boolean> {
            if (it) {
                showOkMessage()
            } else {
                showErrorMessage()
            }
        }
        loginViewModel.loginData.observe(this, loginObserver)
    }

    private fun signIn() {
        this.hideKeyboard()
        val validEmail = validateEmailInput(inputUser.text.toString())
        val validPassword = validatePasswInput(inputPassw.text.toString())
        if (validEmail && validPassword) {
            loginViewModel.makeLogin(inputUser!!.text.toString(), inputPassw.text.toString())
        }
    }

    private fun showErrorMessage() {
        val message = """
            ${getString(R.string.invalidCredentials_message)}
            ${ERROR_EMOJI.getEmojiByUnicode()}
            """.trimIndent()
        DialogFactory.showInfoDialog(getString(R.string.invalidCredentials_title), message, this)
        inputUser.clear()
        inputPassw.clear()
    }

    private fun showOkMessage() {
        val message = """
            ${getString(R.string.validCredentials_message)}
            ${PULGAR_EMOJI.getEmojiByUnicode()}
            """.trimIndent()
        DialogFactory.showInfoDialog(getString(R.string.validCredentials_title), message, this)
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

}
