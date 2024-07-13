package com.msc.newsapplication.domain.manager

import android.content.Context
import kotlinx.coroutines.flow.Flow

/***
 * Domain Layer 에서는 추상화만 함 (= interface)
 * -> Data Layer 에서 구현함 ( LocalUserManagerImpl )
 */
interface LocalUserManager {

    // OnBoarding 화면에서 시작하기를 클릭했을때 호출되는 함수
    // 다음번에 앱을 실행했을때는 OnBoarding이 보이지 않게 하기 위한 플래그
    suspend fun saveAppEntry()

    fun readAppEntry() : Flow<Boolean>

}