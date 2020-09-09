package com.cipolat.loginespresso.ui.login.auth

class AuthValidation {
    private val USERNAME = "jackbauer@gmail.com"
    private val PASSWORD = "matrix2048"

    fun checkLoginData(username: String, password: String): Boolean {
        return username == USERNAME && password == PASSWORD
    }
}