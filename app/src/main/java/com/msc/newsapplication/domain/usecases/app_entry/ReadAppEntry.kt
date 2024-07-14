package com.msc.newsapplication.domain.usecases.app_entry

import com.msc.newsapplication.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
    // Impl 가 아닌 Interface 를 전달받는 이유가 테스트를 가능하게 만들기 때문이다.
) {

    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }

}