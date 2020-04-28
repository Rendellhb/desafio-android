package com.picpay.desafio.android.repository

import androidx.lifecycle.LiveData
import com.picpay.desafio.android.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}