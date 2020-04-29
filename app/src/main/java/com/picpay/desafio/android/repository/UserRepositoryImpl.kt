package com.picpay.desafio.android.repository

import android.content.Context
import com.picpay.desafio.android.PicPayService
import com.picpay.desafio.android.cache.UserCache
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.retrofit
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val context: Context,
    private val userCache: UserCache
): UserRepository {
    private val service: PicPayService by lazy {
        retrofit(context).create(PicPayService::class.java)
    }

    override suspend fun getUsers(): List<User> {
        return if (userCache.get(context).isEmpty() && userCache.isExpired()) {
            val users = service.getUsers()
            userCache.put(users, context)

            return users
        } else {
            userCache.get(context)
        }
    }
}