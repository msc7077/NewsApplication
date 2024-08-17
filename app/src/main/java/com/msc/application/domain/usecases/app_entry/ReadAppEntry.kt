package com.msc.application.domain.usecases.app_entry

import com.msc.application.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAppEntry @Inject constructor(
    private val localUserManager: LocalUserManager
    // Impl 가 아닌 Interface 를 전달받는 이유가 테스트를 가능하게 만들기 때문이다.
) {

    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }

}