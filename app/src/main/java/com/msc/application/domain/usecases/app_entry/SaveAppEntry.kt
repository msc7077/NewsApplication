package com.msc.application.domain.usecases.app_entry

import com.msc.application.domain.manager.LocalUserManager

/***
 *
 */
class SaveAppEntry(
    private val localUserManager: LocalUserManager
    // Impl 가 아닌 Interface 를 전달받는 이유가 테스트를 가능하게 만들기 때문이다.
) {

    suspend operator fun invoke() {
        // invoke : 이름 없이 간편하게 호출(=실행)될 수 있는 함수 / 반드시 operator 연산자를 붙여줘야한다!!!
        // saveAppEntry.invoke(str) 이렇게 하지않아도 saveAppEntry(str) 이렇게 함수 명을 생략할 수 있다.
        localUserManager.saveAppEntry()
    }

}