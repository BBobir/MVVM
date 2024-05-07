package com.humbur.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(private val application: Application
) : AndroidViewModel(application) {

    private val _username = MutableLiveData("")
    val username: LiveData<String> = _username

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _usernameErrorMessage = MutableLiveData<String>()
    val usernameErrorMessage = _usernameErrorMessage

    private val _passwordErrorMessage = MutableLiveData<String>()
    val passwordErrorMessage = _passwordErrorMessage

    private val _usernameCounter = MutableLiveData(0)
    val usernameCounter = _usernameCounter

    fun setUsername(username: String) {
        _usernameErrorMessage.value = ""
        _username.value = username
        _usernameCounter.value = username.length
    }

    fun setPassword(password: String) {
        _passwordErrorMessage.value = ""
        _password.value = password
    }

    fun onLoginCliked() {
        if (username.value.toString().isEmpty()) {
            _usernameErrorMessage.value = application.getString(R.string.username_is_empty)
        }else{
            if (username.value.toString().length < 3){
                _usernameErrorMessage.value = application.getString(R.string.username_min_length_error)
            }
            else if (username.value.toString().length > 16){
                _usernameErrorMessage.value = application.getString(R.string.username_max_length_error)
            }
        }
        if (password.value.toString().isEmpty()){
            _passwordErrorMessage.value = application.getString(R.string.password_is_empty)
        }
    }
}