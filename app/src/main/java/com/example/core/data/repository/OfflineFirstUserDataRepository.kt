package com.example.core.data.repository

import com.example.core.datastore.datastore.TiflyPreferencesDataSource
import com.example.core.model.data.DarkThemeConfig
import com.example.core.model.data.ThemeBrand
import com.example.core.model.data.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFirstUserDataRepository @Inject constructor(
    private val tiflyPreferencesDataSource: TiflyPreferencesDataSource,
): UserDataRepository {

    override val userData: Flow<UserData> =
        tiflyPreferencesDataSource.userData

    override suspend fun setThemeBrand(themeBrand: ThemeBrand) {
        tiflyPreferencesDataSource.setThemeBrand(themeBrand)
    }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        tiflyPreferencesDataSource.setDarkThemeConfig(darkThemeConfig)
    }


    override suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        tiflyPreferencesDataSource.setDynamicColorPreference(useDynamicColor)
    }

    override suspend fun setShouldHideOnboarding(shouldHideOnboarding: Boolean) {
        tiflyPreferencesDataSource.setShouldHideOnboarding(shouldHideOnboarding)
    }


}