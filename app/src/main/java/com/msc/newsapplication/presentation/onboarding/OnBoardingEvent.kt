package com.msc.newsapplication.presentation.onboarding

sealed class OnBoardingEvent {
    /***
     *
     * OnBoardingViewModel 에 대한 이벤트
     * sealed class (추상클래스-abstract class) 로써 상속받는 자식 클래스들의 종류를 제한한다. = 제한으로 인해 어떤것들이 있는지 명확하게 알 수 있어 오류를 방지할 수 있다.
     *
     * MVI
     * M : Model - 일반적으로 불변 객체
     * V : View - UI 로써 Model 의 변경 사항을 구독하고 UI 를 업데이트한다. UI 에서 수행한 작업(=클릭)에서 Intent 를 생성한다.이렇게 생성된 Intent 는 ViewModel 에 전달되어 처리된다.
     * I : Intent - 이벤트가 발생했음을 나타내는 객체이다.
     *
     * 1. 사용자가 UI 와 상호작용하여 Intent 를 생성합니다.
     * 2. View 는 Intent 를 ViewModel 에 전달합니다.
     * 3. ViewModel 은 Intent 를 처리하고 필요한 경우 Model 을 업데이트합니다.
     * 4. View 는 새로운 상태를 구독하고 UI 를 업데이트 합니다.
     *
     * 따라서 해당 class 는 MVI 패턴의 I 인 Intent 를 정의하는 객체이다.
     *
     * */

    object SaveAppEntry: OnBoardingEvent()

}