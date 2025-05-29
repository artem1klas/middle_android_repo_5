package com.example.settings.di

import com.example.settings.data_store.DataStoreServiceImpl
import com.example.settings.ui.SettingsViewModel
import com.example.settings.ui.contract.DataStoreService
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    single<DataStoreService> { DataStoreServiceImpl(androidApplication()) }
    viewModel { SettingsViewModel(get()) }
}