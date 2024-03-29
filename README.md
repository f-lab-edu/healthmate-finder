# 💪 healthmate finder 'Helparty' 

"나의 헬스 친구 Helparty"

운동을 하는 사람들은 모두 헬스장을 가야겠다는 생각을 하지만 발이 떨어지지 않는 경험을 한 적이 있을 겁니다. 
그리고 이럴 때 나랑 같이 다닐 친구가 있다면 좋을 텐데라고 혼자 생각하고 말았을 겁니다. 
그래서 만들었습니다. 
헬스 같이 할 동네 친구 매칭 시켜주는 서비스 'Helparty' 입니다!   

# 프로젝트의 전체적인 구조

![프로젝트 구조도 (2)](https://user-images.githubusercontent.com/25305130/126603634-65653d56-3908-4214-9af8-37a95d6d12bb.png)


# DB ERD
![helparty erd](https://user-images.githubusercontent.com/25305130/121939907-7e1ce000-cd88-11eb-8997-f84f2f6e4e8f.png)



# 프로젝트 목표
- 성능을 생각하면서 효율적인 코드를 작성하도록 노력하였습니다.
- 객체지향 원칙을 따르며 확장성 있는 코드를 작성하고자 하였습니다.
- 대용량 트래픽을 감당할 수 있는 인프라를 구축하고 안정적인 서비스를 만들고자 노력했습니다.
- 협업을 한다는 가정하에 다른 사람들이 쉽게 알아볼 수 있도록 코드 작성하는 것에 유의하였습니다.
- 고립된 테스트 코드 작성으로 다른 코드에 의존적이지 않은 테스트를 진행하였습니다. 

# 사용 기술
1. Java 11
2. Spring Boot
3. JUnit
4. MySQL
5. MyBatis
6. Redis
7. Jenkins
8. Naver Cloud 

# 프로젝트 중점 사항
- GitFlow를 이용한 병렬적 개발 방식
- 코드의 목적을 쉽게 알 수 있는 메서드 네이밍
- 객체지향 코드 작성법으로 확장성 유지
- 작성된 Layer에 고립시켜 의존적이지 않은 단위 테스트 작성
- 반복되는 로직을 핵심 로직으로부터 분리 (feat. AOP, ArgumentHandlerResolver, Interceptor)
- 젠킨스를 사용한 CI/CD 환경 구축
- 하나의 클라우드 서버에 하나의 어플리케이션을 사용하여 높은 확장성 유지
- 많은 사람들에 의해 중복될 페이지 조회에 Redis cache를 사용하여 성능 개선
- Redis의 Session Server를 사용하여 Session 정합성 유지
- Log4J2를 로그로 사용하여 서버의 부담 최소화
- NginX의 Reverse-Proxy를 이용한 로드밸런싱 구현
- DB Replication을 구현하여 DB 성능 향상
- MySql쿼리의 실행계획 분석 후 쿼리튜닝을 통한 성능 향상


# 이슈 해결 과정
- [#9] 부하 분산을 위한 데이터베이스 이중화 작업 - MySQL Replication   
https://hamryt.tistory.com/12
- [#8] Ngrinder를 이용하여 성능테스트하기      
https://hamryt.tistory.com/11
- [#7] 젠킨스를 이용한 CI/CD 환경 구축      
  
- [#6] 쿼리의 성능 튜닝   
https://hamryt.tistory.com/10?category=961846   
- [#5] 프로젝트에는 어떤 Log 라이브러리를 사용할까? LogBack vs Log4J2   
https://hamryt.tistory.com/9?category=961846   
- [#4] 세션 정보를 받아오는 로직을 HandleMethodArgumentResolver를 통해 효율적으로 처리하는 과정   
https://hamryt.tistory.com/8?category=961846   
- [#3] 로그인을 검사하는 부가 로직은 어떻게 핵심로직에서 분리할까?    
https://hamryt.tistory.com/7?category=961846   
- [#2] Scale Out을 통한 서버 증설 과정에서 발생하는 세션 정합성 문제는 어떻게 해결할까? - Sticky Session, Session Clustering, Session Storage   
https://hamryt.tistory.com/4?category=961846        
https://hamryt.tistory.com/3?category=961846   
- [#1] 서버 증설은 어떤 방식으로 구현해야 할까? - Scale out vs Scale Up       
https://hamryt.tistory.com/1?category=961846   

# 화면설계
kakao oven -https://ovenapp.io/view/ZmMg4lnHw2iVSxfO0UwY1NzTOkWoNsiZ/liSyR

![Helparty 프로토타입](https://user-images.githubusercontent.com/25305130/117656221-0bda3e00-b1d3-11eb-8bd4-5db2c9d44879.png)   
   
# 기능 정의
- [위키를 통해 확인하기](https://github.com/f-lab-edu/healthmate-finder/wiki/UseCase)

# 깃헙 플로우
- [위키를 통해 확인하기](https://github.com/f-lab-edu/healthmate-finder/wiki/Git-Flow)


