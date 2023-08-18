
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
  
-------------------
## 앱 스크롤 이슈 수정

## Gson 라이브러리 
  리플렉션 (Reflection) 사용
  Gson은 자바의 리플렉션을 사용하여 클래스의 필드와 메서드에 접근
  클래스의 필드와 메서드 이름, 타입 등의 정보를 분석하고 이를 기반으로 JSON 데이터와 자바 객체 간의 변환 작업을 수행

## 리사이클러뷰 ViewHolder 패턴
  ViewHolder 패턴을 구현 
  ViewHolder 하나 정의 하고 재사용하여 같은 아이템 형식일 경우 사용함

## 메모리 킬 당했을 시 
 bundle 이 아닌 savestateHandle 을 사용 하여 복구 가능
  
 


  