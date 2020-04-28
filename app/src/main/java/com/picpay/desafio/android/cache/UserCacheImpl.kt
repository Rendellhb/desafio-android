package com.picpay.desafio.android.cache

import com.picpay.desafio.android.model.User
import io.reactivex.Observable
import javax.inject.Singleton

@Singleton
class UserCacheImpl: UserCache {
    private val expirationTime = 1000L

    override fun get(): List<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun put(users: List<User>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCached(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime: Long = getLastCacheUpdateTimeMillis()

        val expired: Boolean = currentTime - lastUpdateTime > expirationTime

        if (expired) {
            this.wipeCache()
        }

        return expired
    }

    private fun getLastCacheUpdateTimeMillis(): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun wipeCache() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}