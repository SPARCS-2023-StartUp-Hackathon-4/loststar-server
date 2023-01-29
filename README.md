# [TEAM J] 어디갔지? Server
어디갔지?는 지역 기반의 온라인 분실물 센터입니다. <br>
사용자들은 분실물과 습득물을 서로 공유하여 주인 잃은 물건의 주인을 빠르게 찾도록 도와줍니다. <br>

다음과 같은 기능이 포함되어 있습니다.
- 분실물 신고 접수 기능
- 분실물, 습득물 전체 조회
- 앱 내 포인트(화폐) 기능 (닻별, 별조각)
- 새로운 분실물 등록 시, 지역 기반의 푸시 알림 기능

<br>


## 프로젝트에서 사용한 기술
- Spring Boot (Kotlin)
- Spring Data JPA
- Spring Security
- MySQL
- QueryDSL
- FirebaseCloudMessaging
- FirebaseRealtimeDatabase
- AWS EC2, RDS, S3
- 닉네임 자동 생성 3rd 파티 솔루션 (https://nickname.hwanmoo.kr/)

<br>

### ERD
![image](https://user-images.githubusercontent.com/85692623/215286168-b44d8acd-d919-405f-b8b9-428064a467ba.png)

<br>

## Dev Server 실행 방법
1. 본 Repository를 로컬 환경에 Clone 받습니다.
2. InteliJ IDEA로 build.gradle을 Project로 오픈합니다.
3. application-secret.properties 파일을 resource 하위 디렉토리에 application.properties와 함께 위치시킵니다. secret 파일은 DB 연결정보, cloud 자원 연결 정보 등을 입력합니다.
4. firebase JSON 서비스 키를 위치시킵니다. <br>
![image](https://user-images.githubusercontent.com/85692623/215300592-dda586b6-aa8b-40a2-b512-4fa2a27a3166.png)
5. LoststarApplication main 함수를 실행시킵니다. <br>
![image](https://user-images.githubusercontent.com/85692623/215300687-55fc4ff8-f721-41e1-be38-02de5a81b835.png)

## Production 배포 방법
1. gradle을 활용하여 jar파일로 패키징합니다. (loststar-0.0.1-SNAPSHOT.jar 생성 됨)
2. AWS EC2 인스턴스에 파일을 이동시킵니다. (FTP 프로그램 사용 권장)
3. ```nohup java -jar loststar-0.0.1-SNAPSHOT &``` 을 이용하여 실행합니다.
4. 서버가 정상적으로 구동되었는지 확인하려면 /test 엔드포인트로 접근해 봅니다. (확성기 아이콘이 나오면 서버가 정상 작동 된 것 입니다.)

## 환경 변수 및 시크릿
1. JDK 17 버전 이상의 Java가 필요합니다.
2. application-secret.properties 작성 포맷
```
spring.datasource.url=jdbc:mysql://DB연결정보/loststar
spring.datasource.username=사용자 계정
spring.datasource.password=패스워드

jwt.secret = 임의 비밀키 값

cloud.aws.credentials.accessKey = S3 액세스 키
cloud.aws.credentials.secretKey = S3 시크릿 키
cloud.aws.region.static = 지역
cloud.aws.region.auto= false

cloud.aws.s3.bucket = 버킷명
cloud.aws.s3.dir = 디렉토리명
cloud.aws.stack.auto = false
```
3. firebase JSON 서비스 키 설정 방법 https://mopil.tistory.com/129

