# Innovation Camp Spring Assignment
### 기본 CRUD 구현한 Spring Boot 프로그램(8/12~8/18)
### + 회원 기능 추가 (8/19~8/25)
<br/>


__1. 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body)__
- @requestbody로 http body에 담긴 데이터를 받고, @PathVariable로 id를 받았다
<br/>

__2. 어떤 상황에 어떤 방식의 request를 써야하나요?__
- 조회(GET), 등록(POST), 수정(PUT), 삭제(DELETE)
<br/>

__3. RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?__
- 리소스(posts)가 명확히 식별되고, http 메소드를 통해 역할을 구분지었다
<br/>

__4. 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)__
- controller: 요청 사항을 받고, 다른 계층에서 처리한 내용을 dto에 담아 응답
- service: controller와 repository를 연결지으면서 메인 로직이 담겨있다
- repository: 실제 db와 통신하며 리소스 추가, 수정, 조회, 삭제 작업을 한다
<br/>

__5. 작성한 코드에서 빈(Bean)을 모두 찾아보세요!__
- ExceptionController, BoardController, BoardService, BoardRepository
<br/>

__6. API 명세서 작성 가이드라인을 검색하여 직접 작성한 명세서와 비교해보세요! (8/25 수정)__   

| 기능                        | Method  | URL                                    | Request           | Response                                |
|-----------------------------|-----|---------------------------------------|------------------------------------------------------------|------|
| 회원가입                     | POST  | /users/signup | 아이디, 비밀번호, 비밀번호 확인   | 성공메세지 + 가입 정보 |
| 로그인                       | POST  | /users/login | 아이디, 비밀번호   |성공메세지 + 로그인 정보|
| 게시글 목록 조회            | GET  | /posts | -   | 성공메세지 + 게시글 목록 정보|
| 게시글 조회                 | GET  | /posts/{id}   | -           | 성공메세지 + 게시글 정보 |
| 게시글 등록                  | POST  | /posts/auth    |  헤더: JWT, 바디: 제목, 내용 | 성공메세지 + 게시글 정보 |
| 게시글 수정                | PUT  | /posts/auth/{id}  |  헤더: JWT, 바디: 제목, 내용 | 성공메세지 + 게시글 정보 |
| 게시글 삭제                | DELETE  | /posts/auth/{id}  | 헤더: JWT  | 성공메세지 |
| 댓글 목록 조회                | GET  | /comments/{id}  |   | 성공메세지 + 댓글 목록 정보 |
| 댓글 등록                | POST  | /comments/auth/{id}  |  헤더: JWT, 바디: 게시글 id, 내용  | 성공메세지 + 댓글 정보 |
| 댓글 수정                | PUT  | /comments/auth/{id}  |  헤더: JWT, 바디: 게시글 id, 내용  | 성공메세지 +  |
| 댓글 삭제                | DELETE  | /comments/auth/{id}  |  헤더: JWT  | 성공메세지 |
