package com.picpay.desafio.android.cache

import com.picpay.desafio.android.model.User
import io.reactivex.Observable

interface UserCache {
    fun get(): List<User>
    fun put(users: List<User>)
    fun isCached(): Boolean
    fun isExpired(): Boolean
    fun wipeCache()
}