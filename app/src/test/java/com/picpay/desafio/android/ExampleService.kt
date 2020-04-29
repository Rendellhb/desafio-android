package com.picpay.desafio.android

import com.picpay.desafio.android.model.User

class ExampleService(
    private val service: PicPayService
) {

    suspend fun example(): List<User> {
        return service.getUsers()
    }
}