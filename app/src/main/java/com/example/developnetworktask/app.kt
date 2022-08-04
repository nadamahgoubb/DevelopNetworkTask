package com.example.developnetworktask

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class app : Application() {
    companion object CompanionObject {

        lateinit var dataStore: DataStore<Preferences>
    }

    override fun onCreate() {
        super.onCreate()
        dataStore = createDataStore(name = "settings")

    }
}