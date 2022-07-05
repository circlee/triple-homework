# triple-homework


### 개발환경
- JDK14
- spring boot version : 2.6.2

### 실행
git clone 

command >
```
     cd ../app
     ../gradlew bootjar && docker-compose up -d
```

### 설명

- 포인트 조회 API endpoint : /users/{{userId}}/activePoint

- intellij .http :
https://github.com/circlee/triple-homework/blob/a0565631f1d960e36ea72e861e5c43f416c6d107/app/src/main/resources/http

- event의 순서가 보장되어 전달되는 것을 전제로 하였습니다.
- 요구사항의 장소의 최초 리뷰시 1점 부여 관련 문의 및 의견입니다. 
    ```
    해당 구현 도메인을 리뷰 이벤트를 컨슘하는 포인트 도메인으로 이해했습니다.
  
    장소의 최초 리뷰를 구분하는 flag는 리뷰 도메인으로 부터 받아야 한다고 생각했습니다.
    해당 장소의 최초 리뷰 여부를 확인 하기 위해 event 메시지에 포함 되거나 또는 리뷰 도메인을 호출 하는 방법이 있다고 생각합니다.
  
    - 일단은 ReviewFirstReviewInPlacePointGrantPolicy 에서 포인트 이력으로 장소 최초 리뷰 여부를 판단하도록 구현하였습니다.
      하지만, 개인적으로 위와 같은 의견 드립니다.
    ```  
  
- Controller Advice 등의 공통 에러 핸들링은 생략 하였습니다.

감사합니다.



