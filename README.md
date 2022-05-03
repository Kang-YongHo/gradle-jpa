#Boiler Plate
---
##환경

- framework: SpringBoot 2.6.7
- JDK: JAVA 11
- DB handling: JPA, QueryDsl
- build: Gradle

###H2 Memory DB
- localhost:8080/h2-console로 접속
- JDBC URL을 jdbc:h2:mem:test로 맞추고 접속

###BUILD
- ./gradlew clean build

###도메인
- Account - 유저 계정
- Education - 교육 정보
- 유저N : 교육N의 ManyToMany관계