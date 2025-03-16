# 바로인턴 개발자 과제

바로인턴 10기 제출용 개발자 과제입니다.

---

## [프로젝트 설명]

회원가입 / 로그인 / 유저 권환 변경 서비스 제공

## [요구사항]

### 공통 요구사항

1️⃣ 기본으로 설정된 서버의 주소와 포트는 `0.0.0.0:8080` 이고, 이를 수정하지 않는다.

2️⃣ 모든 API 응답은 적절한 HTTP 상태 코드와 함께 `application/json` 형식으로 반환한다.

3️⃣ 실제 데이터베이스나 파일 시스템을 사용하지 않으며, 모든 데이터는 메모리 내에서 처리된다.

<br>

# 과제 요구사항
## (1) 기능 개발
<details><summary>사용자 인증 시스템 구축</summary>
<br>
회원가입 기능 구현 <br><br>
로그인 기능 구현<br>
</details>
<details><summary>JWT(Json Web Token) 기반 인증 메커니즘을 구현하여 보안성을 강화</summary>
  
<br>
토큰 발급
<br>
  <br>
- 토큰은 비밀 키로 서명되어야 하며, 만료 시간을 설정해야 합니다. (예: 2시간)
  <br>
  <br>
- 토큰 구조
<br>
  <br>
    - Header: 알고리즘 및 토큰 유형 정보
  <br>
  <br>
    - Payload: 사용자 ID(sub), 역할(roles), 발급 시간(iat), 만료 시간(exp) 등의 정보
  <br>
  <br>
    - Signature: 헤더와 페이로드가 변조되지 않았음을 확인하는 서명
<br>
<br>
토큰 검증
  <br>
  <br>
- 모든 보호된 API 요청에는 HTTP 요청 헤더에 토큰을 포함해야 합니다.
  <br>
  <br>
- 헤더 형식: `Authorization: Bearer [토큰]`
  <br>
  <br>
- 서버는 아래의 검증을 수행합니다.
  <br>
  <br>
    - 토큰 존재 여부 확인
  <br>
  <br>
    - 서명 검증 (서버의 비밀 키로 서명 확인)
  <br>
  <br>
    - 토큰 만료 여부 확인
  <br>
  <br>
    - 토큰에 포함된 권한 정보 추출
  <br>
  <br>
    - SecurityContext에 인증 정보 설정
  <br>
  <br>
    - 요청한 API에 접근 권한이 있는지 확인
  <br>
<br>
에러처리

![image](https://github.com/user-attachments/assets/f4028581-db2f-463b-ae2d-3dc9dee57cdf)<br>
</details>

<details>
  <summary>
역할(Role) 기반 접근 제어를 적용하여 관리자(Admin) 권한이 필요한 API를 보호
  </summary>
  
  <br>
  관리자 권한 부여 API
  
  ![image](https://github.com/user-attachments/assets/9c9495e6-6f11-4088-9edf-7bab10001408)
</details>
<br>

## (2)테스트
### 각 기능(회원가입, 로그인 등)이 의도한 대로 동작하는지 확인 
<br>

## (3)API 명세서
### 각 엔드포인트, 요청/응답 구조, 상태 코드 등을 한눈에 파악할 수 있도록 문서화
<br>

## (4)배포
### Spring Boot 애플리케이션을 AWS EC2 인스턴스에 배포하여 실제 클라우드 환경에서 구동

<br>


# 과제 진행

## ✅ 회원가입 기능 구현

### ⏳ 회원가입 성공

![image](https://github.com/user-attachments/assets/b65826f5-80f7-458a-ba3d-f5dbc0dde3d4)
<br>
### ⏳ 회원가입 실패 (이미 가입된 사용자)

![image](https://github.com/user-attachments/assets/46f4bb12-9233-4ecc-a20c-2ad1ac76d9ae)

<br>

## ✅ 로그인 기능 구현

### ⏳ 로그인 성공

![image](https://github.com/user-attachments/assets/5d2b1017-3473-43e3-a6ed-3ec100b03587)

<br>

### ⏳ 로그인 실패

![image](https://github.com/user-attachments/assets/ada64310-d2f2-41c3-a916-7bfa839195bc)

<br>

## ✅ 관리자 권한 부여

### ⏳ 관리자 권한 부여 성공

![image](https://github.com/user-attachments/assets/c86b47b2-da4f-47d7-a8fb-d4573be11f07)

### ⏳ 관리자 권한 부여 실패

![image](https://github.com/user-attachments/assets/a384f481-b788-4aac-975f-f7a3e3ccca98)

<br>

## ✅ 테스트 코드 작성


<br>

## ✅ API 명세서

![image](https://github.com/user-attachments/assets/05e65686-1e08-4922-a432-df7fe332a1a1)

### ⏳ Swagger UI 등록

![스크린샷 2025-03-16 052009](https://github.com/user-attachments/assets/ee68f30a-8edb-4144-aac1-f71ed1eaf33d)

![스크린샷 2025-03-16 052017](https://github.com/user-attachments/assets/42cf002d-7c30-4f20-9120-6a9b4ebc68e7)

<br>

## ✅ 배포










