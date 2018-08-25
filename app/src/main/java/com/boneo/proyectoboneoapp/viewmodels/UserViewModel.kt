package com.boneo.proyectoboneoapp.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.boneo.proyectoboneoapp.model.User
import com.boneo.proyectoboneoapp.model.UserRepository


class UserViewModel : ViewModel() {
    val user = MutableLiveData<Pair<User?, Error?>>()

    init {
        UserRepository.getUser{ userResult, error ->
            user.value = Pair(userResult, error)
        }
    }
}