package com.cipolat.loginespresso.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var loginData: MutableLiveData<Boolean> = MutableLiveData()

    fun makeLogin(userName:String, password:String){

    }

}