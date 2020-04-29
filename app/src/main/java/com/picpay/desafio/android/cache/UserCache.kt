package com.picpay.desafio.android.cache

import android.content.Context
import com.picpay.desafio.android.model.User
import io.reactivex.Observable
import java.io.File

interface UserCache {
    fun get(context: Context): List<User>
    fun put(users: List<User>, context: Context)
    fun isCached(directory: File): Boolean
    fun isExpired(): Boolean
    fun wipeCache(directory: File)
}