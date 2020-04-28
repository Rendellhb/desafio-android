package com.picpay.desafio.android

import com.picpay.desafio.android.cache.UserCacheImpl
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.repository.UserRepositoryImpl
import com.picpay.desafio.android.view_model.ListUsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val picPayModules = module {
    single<UserRepository> { UserRepositoryImpl(get(), UserCacheImpl()) }
    viewModel { ListUsersViewModel(get()) }
}