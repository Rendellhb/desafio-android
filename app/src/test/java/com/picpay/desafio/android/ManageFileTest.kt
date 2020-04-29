package com.picpay.desafio.android

import android.content.Context
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.cache.UserCacheImpl
import org.junit.Test
import org.mockito.Mock
import java.io.File

class ManageFileTest {
    private val cacheDir = "/data/user/0/com.picpay.desafio.android/cache/"

    @Mock
    private lateinit var mockContext: Context

    private val userCache = UserCacheImpl()

    @Test
    fun `list is empty when file don't exists`() {
        whenever(mockContext.cacheDir).thenReturn(File(cacheDir))

        val usersList = userCache.get(mockContext)
        assert(usersList.isEmpty())
    }
}