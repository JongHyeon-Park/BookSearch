
# SearchBookProject

## 기술 스펙
- 언어 : Kotlin
- 아키텍처: MVVM
- View : Compose
- 비동기 : coroutine
- 라이브러리 : coil, retrofit2, hilt, paging3, secret, kotlinSerialization
- kotlinDSL + versionCatalog 사용

## 모듈 설명

* app (Application, MainActivity, NavigationGraph)
* core 
  * data (데이터 관련 처리)
  * designsystem (UI Component 관련 모음)
  * domain (비즈니스 로직 캡슐화)
  * network (네트워크 정의)
  * testing (테스트 관련 HiltApplication)
  * ui (UI Util 관련 모음)
* feature (화면)
  * search (책 검색 & 리스트 화면)
  * detail (책 상세 화면)

## 요구 사항
  전체 찾기에서 "요구 사항" 검색 시 요구 사항 관련된 기능을 확인 할 수 있습니다.
  ex : 요구 사항 정상판매 데이터만 보여지도록 설정 

  
 


  