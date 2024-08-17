package com.msc.application.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.msc.application.domain.manager.LocalUserManager
import com.msc.application.util.Constants
import com.msc.application.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/***
 *  Domain Layer ( LocalUserManager ) 에서 추상화한 코드를 구현한다.
 *  -> 이렇게 구현된 기능에 대한 UseCase 를 Domain Layer 에서 만든다.
 */
class LocalUserManagerImpl(
    private val context: Context
) : LocalUserManager {

    override suspend fun saveAppEntry() {
        // context 를 사용하여 dataStore 에 접근이 가능해짐
        // edit 를 사용하여 USER_SETTINGS 의 값을 얻을 수 있다.
        context.dataStore.edit { settings ->
            // APP_ENTRY 값을 true 로 한다. = 앱을 최초 설치 후 온보딩에서 시작하기를 클릭했다!
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.APP_ENTRY] ?: false
        }
    }

}

// Context 를 확장함으로써 dataStore 라는 이름으로 객체에 접근이 가능해짐
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

// DataStored 에 키값을 저장을 하려면 기본 설정 키가 필요하다.
private object PreferencesKeys {
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}