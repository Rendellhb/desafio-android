package com.picpay.desafio.android.view_model

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.picpay.desafio.android.NoConnectivityException
import com.picpay.desafio.android.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class ListUsersViewModel(
    private val repo: UserRepository
): ViewModel() {
    val componentsVisibility = MutableLiveData<Int>()
    val users = liveData(Dispatchers.IO) {
        try {
            val retrievedUsers = repo.getUsers()

            emit(retrievedUsers)
        } catch (e: Exception) {
            componentsVisibility.postValue(View.GONE)
        }
    }
}