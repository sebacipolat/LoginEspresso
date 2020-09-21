package com.cipolat.loginespresso.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cipolat.loginespresso.ui.login.auth.AuthValidation

class LoginViewModel : ViewModel() {
    var loginData: MutableLiveData<Boolean> = MutableLiveData()

    fun makeLogin(userName:String, password:String){
        val auth= AuthValidation()
        loginData.postValue(auth.checkLoginData(userName,password))
    }
}