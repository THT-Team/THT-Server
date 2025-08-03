### 회원가입
POST http://localhost:8080/users/join/signup

### 유저 상세 조회
GET http://localhost:8080/user

### 유저 상세 조회 (다른 유저)
GET http://localhost:8080/user/another/{{user-uuid}}

### 채팅방 조회
GET http://localhost:8080/chat/rooms

### 채팅방 상세 조회
GET http://localhost:8080/chat/room/{chat-room-idx}

### 채팅 내역 조회
GET http://localhost:8080/chat/history
