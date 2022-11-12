package com.example.moviescatalog.data.models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescatalog.data.di.ApiClient
import com.example.moviescatalog.data.authapi.registration.RegisterBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {
    val isSuccessLoading = mutableStateOf(false)
    val progressBar = mutableStateOf(false)
    private val registerRequestLiveData = MutableLiveData<Boolean>()

    fun onRegisterPressed(userName: String, name: String, password: String, email: String, birthDate: String, gender: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                progressBar.value = true
                val apiService = ApiClient.getApiService("https://react-midterm.kreosoft.space/")
                val responseService = apiService.register(RegisterBody(userName, name, password, email, birthDate, gender))

                if (responseService.isSuccessful) {
                    //if(responseService.code() == 400)
                    delay(1500L)
                    isSuccessLoading.value = true
                    responseService.body()?.let { accessToken ->
                        Log.d("Signup", "Response Token: ${accessToken.registerToken}")
                    }
                } else {
                    Log.d("Code: ", responseService.code().toString())
                    isSuccessLoading.value = false
                }
                registerRequestLiveData.postValue(responseService.isSuccessful)
                progressBar.value = false
            } catch (e: Exception) {
                Log.d("Registration", "Error Register", e)
                progressBar.value = false
            }
        }
    }
}