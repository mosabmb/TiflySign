package com.example.core.datastore.datastore

import androidx.datastore.core.DataMigration
import com.example.UserPreferences


object IntToStringIdsMigration : DataMigration<UserPreferences> {

    override suspend fun cleanUp() {
        TODO("Not yet implemented")
    }

    override suspend fun shouldMigrate(currentData: UserPreferences): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun migrate(currentData: UserPreferences): UserPreferences {
        TODO("Not yet implemented")
    }
}