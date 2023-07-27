package com.example.core.data.repository

import com.example.core.model.data.DarkThemeConfig
import com.example.core.model.data.ThemeBrand
import com.example.core.model.data.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    val userData: Flow<UserData>

    suspend fun setThemeBrand(themeBrand: ThemeBrand)

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)

    suspend fun setDynamicColorPreference(useDynamicColor: Boolean)

    suspend fun setShouldHideOnboarding(shouldHideOnboarding: Boolean)


}

