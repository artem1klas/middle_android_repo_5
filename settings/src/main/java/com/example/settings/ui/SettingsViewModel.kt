package com.example.settings.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.settings.data_store.SettingContainer
import com.example.settings.ui.contract.DataStoreService
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val dataStoreService: DataStoreService
) : ViewModel() {

    fun saveSetting(periodic: Long, delayed: Long) {
        viewModelScope.launch {
            dataStoreService.saveSetting(periodic = periodic, delayed = delayed)
        }
    }

    fun getCurrentSetting(): SettingContainer {
        return dataStoreService.settingData.value
    }
}