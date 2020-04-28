package com.picpay.desafio.android.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.picpay.desafio.android.repository.UserRepository
import kotlinx.coroutines.Dispatchers

class ListUsersViewModel(private val repo: UserRepository): ViewModel() {
    val users = liveData(Dispatchers.IO) {
        val retrievedUsers = repo.getUsers()

        emit(retrievedUsers)
    }
}