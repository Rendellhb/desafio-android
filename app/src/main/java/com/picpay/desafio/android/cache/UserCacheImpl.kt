package com.picpay.desafio.android.cache

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.picpay.desafio.android.model.User
import java.io.File
import java.io.FileReader
import java.lang.reflect.Type
import javax.inject.Singleton


@Singleton
class UserCacheImpl: UserCache {
    private val EXPIRATION_TIME = 60 * 10 * 1000;
    private val DEFAULT_FILE_NAME = "users.json"

    override fun get(context: Context): List<User> {
        val usersFile = File(context.cacheDir.absolutePath + File.separator +
                DEFAULT_FILE_NAME)
        if (usersFile.exists()) {
            val listType: Type = object : TypeToken<ArrayList<User?>?>() {}.type
            return Gson().fromJson(FileReader(usersFile), listType)
        }
        return emptyList()
    }

    override fun put(users: List<User>, context: Context) {
        val sandboxFolder = File("${context.cacheDir}")
        val usersFile = buildFile(sandboxFolder)

        usersFile?.writeText(Gson().toJson(users))
    }

    override fun isCached(directory: File): Boolean {
        val usersFile = buildFile(directory)
        return usersFile?.exists() ?: false
    }

    private fun buildFile(directory: File): File? {
        if (!directory.exists()) directory.mkdir()
        val file = File(directory.absolutePath + File.separator + DEFAULT_FILE_NAME)
        if (!file.exists()) file.createNewFile()

        return file
    }

    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime: Long = getLastCacheUpdateTimeMillis()

        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    private fun getLastCacheUpdateTimeMillis(): Long {
        return EXPIRATION_TIME.toLong()
    }

    override fun wipeCache(directory: File) {
        if (directory.exists()) {
            for (file in directory.listFiles()!!) {
                file.delete()
            }
        }
    }
}