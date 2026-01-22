# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 빌드 및 개발 명령어

```bash
./gradlew build          # 프로젝트 빌드
./gradlew bootRun        # Spring Boot 애플리케이션 실행
./gradlew test           # 전체 테스트 실행
./gradlew clean          # 빌드 결과물 삭제
```

### 단일 테스트 실행

```bash
./gradlew test --tests "com.back.domain.post.post.controller.ApiV1PostControllerTest.t1"
```

### 환경 설정

실행 전 `.env.default`를 복사하여 OAuth 인증 정보와 JWT 시크릿 키 설정 필요:

- `CUSTOM__JWT__SECRET_KEY`
- Kakao, Google, Naver OAuth 클라이언트 ID/시크릿

## 기술 스택

- **Kotlin 2.2.21** / **Java 24** / **Spring Boot 4.0.1**
- **Gradle 9.2.1** (Kotlin DSL)
- **H2** 데이터베이스 (개발/테스트용, MySQL 모드)
- **Spring Security** - JWT + OAuth2 (Kakao, Google, Naver)
- **SpringDoc OpenAPI 3.0.0** - API 문서화

## 아키텍처

### 패키지 구조

```
com.back/
├── domain/              # 기능 모듈 (비즈니스 로직)
│   ├── member/member/   # 회원 관리 및 인증
│   └── post/            # 게시글 및 댓글 관리
│       ├── post/
│       └── postComment/
├── global/              # 공통 관심사
│   ├── exception/       # ServiceException
│   ├── globalExceptionHandler/
│   ├── jpa/entity/      # BaseEntity (감사 필드)
│   ├── rq/              # Rq - 요청 컨텍스트 (현재 사용자, 쿠키, 헤더)
│   ├── rsData/          # RsData<T> - 표준 응답 래퍼
│   └── security/        # 보안 설정, JWT 필터, OAuth2 핸들러
└── standard/            # 유틸리티 (Ut.kt) 및 Kotlin 확장 함수
```

### 각 도메인 모듈 구성

- `controller/` - REST 엔드포인트 (ApiV1*Controller, ApiV1Adm*Controller)
- `service/` - 비즈니스 로직
- `repository/` - Spring Data JPA 리포지토리
- `entity/` - BaseEntity를 상속하는 JPA 엔티티
- `dto/` - 데이터 전송 객체

### 주요 패턴

**응답 래퍼**: 모든 API 응답은 `RsData<T>` 사용:

```kotlin
RsData("201-1", "글이 작성되었습니다.", PostDto(post))
// resultCode 형식: "{상태코드}-{순번}"
```

**예외 처리**: `ServiceException(resultCode, message)` throw - 자동으로 RsData 응답으로 변환됨.

**요청 컨텍스트**: `Rq` 주입하여 현재 인증된 사용자(`rq.actor`), 쿠키, 헤더 접근.

**권한 검사**: `post.checkActorCanModify(actor)` 같은 엔티티 메서드가 권한 없으면 ServiceException throw.

### 결과 코드

- `200-1`: 성공
- `201-1`: 생성됨
- `400-1`: 잘못된 요청
- `401-1`: 로그인 필요
- `401-3`: 잘못된 API 키
- `403-1`: 권한 없음
- `404-1`: 찾을 수 없음
- `409-1`: 충돌/중복

## API 엔드포인트

- 공개: `GET /api/v1/posts`, `GET /api/v1/posts/{id}`, 로그인/로그아웃, 회원가입
- 인증 필요: `POST/PUT/DELETE /api/v1/posts/*`, 댓글
- 관리자 전용: `/api/v1/adm/**`

JWT 토큰은 `Authorization: Bearer {accessToken}` 헤더 또는 `accessToken` 쿠키로 전달.

## 테스트

JUnit 5와 Spring MockMvc 사용:

- `@SpringBootTest` + `@AutoConfigureMockMvc`
- `@ActiveProfiles("test")` - 테스트 설정 사용
- `@WithUserDetails("user1")` - 인증된 요청 테스트
- `@Transactional` - 자동 롤백