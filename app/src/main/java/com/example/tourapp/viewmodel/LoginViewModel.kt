package com.example.tourapp.viewmodel

import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {
    enum class AuthenticationStatus {
        AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATION
    }
    private val auth = FirebaseAuth.getInstance()
    var user = auth.currentUser
    var errMsgLiveData : MutableLiveData<String> = MutableLiveData()
    val authenticationStatus : MutableLiveData<AuthenticationStatus> = MutableLiveData<AuthenticationStatus>()

    init {
        if(user != null){
            authenticationStatus.value = AuthenticationStatus.AUTHENTICATED
        }
        else{
            authenticationStatus.value = AuthenticationStatus.UNAUTHENTICATED
        }
    }

    fun login(email : String, password : String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                authenticationStatus.value = AuthenticationStatus.AUTHENTICATED
                user = auth.currentUser
            }else{
                authenticationStatus.value = AuthenticationStatus.INVALID_AUTHENTICATION
                errMsgLiveData.value = task.exception?.localizedMessage
            }
            println(task.exception?.localizedMessage)
        }

    }



    fun registration(email : String, password : String){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                authenticationStatus.postValue(AuthenticationStatus.AUTHENTICATED)
                user = auth.currentUser
            }else{
                authenticationStatus.postValue(AuthenticationStatus.INVALID_AUTHENTICATION)
                errMsgLiveData.value = task.exception?.localizedMessage
            }
        }.addOnFailureListener(){
            authenticationStatus.postValue(AuthenticationStatus.INVALID_AUTHENTICATION)
            errMsgLiveData.value = it.localizedMessage
        }
    }
}