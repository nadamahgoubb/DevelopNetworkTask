package com.example.developnetworktask.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import com.example.developnetworktask.app.CompanionObject.dataStore
import kotlinx.coroutines.flow.first

class DataStoreManger() {

     suspend fun save(key: String, value: String) {
        val dataStoreKey = preferencesKey<String>(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

     suspend fun read(key: String): String? {
        val dataStoreKey = preferencesKey<String>(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }
    suspend fun saveBoolean(key: String, value: Boolean) {
        val dataStoreKey = preferencesKey<Boolean>(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }suspend fun readBoolean(key: String): Boolean? {
        val dataStoreKey = preferencesKey<Boolean>(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }
}