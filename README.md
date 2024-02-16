# UJaCha_server

<p align="center">
  <br>
  <img src="./readme/Everyfandom.svg">
  <br>
</p>

<h3 align = "center"> 조현병 조기 진단 서비스, tune </h3>




## ⚙️ 기술 스택

|  Spring    |  AWS_EC2  |  MySQL    | 
| :--------: | :-------: | :-------: | 
|   ![sp]    |   ![ec2]  |   ![pm]   | 

<br>

## 💽 자료구조

### member
    member_id (PK) (BIGINT) (AUTO_INCREMENT)
    uid (VARCHAR(255))
    email (VARCHAR(255))
    nickname (VARCHAR(255))
    reliable_uid (VARCHAR(255))
    child_age (INT)


### test
    test_id (PK) (INT) (AUTO_INCREMENT)
    hallucination (INT)
    abnormal_behavior (INT)
    moody (INT)
    delusion (INT)
    total (INT) 
    date (DATE)
    
    member_id (BIGINT) (FK)


## 📌 주요 기능

### 🔗URL
[API SWAGGER](http://ec2-3-34-143-183.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/index.html#/member%20%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC/updateMember)

### 👨‍👩‍👦‍👦Member
 <details markdown = "1">
  <summary>로그인 : api/member/login  (POST)</summary>
    <ul>
      <li>Request body</li>
      
```json
{
  "email": "string",
  "uid": "string"
}
```

  <li>Response body </li>  

```json
{
  "token": "string",
  "exprTime": 0,
  "first": true
}    
```

  </ul>
 </details>
  
 <details markdown = "1">
  <summary>회원가입 : api/member/first (POST)</summary>
    <ul>
      <li>Request body</li>
      
```json
{
  "nickname": "string",
  "childAge": 0,
  "uid": "string"
}
```

  <li>Response body </li>  

```
string(token 값)
```

  </ul>
 </details>

 <details markdown = "1">
  <summary>업데이트 : api/member/update  (POST)</summary>
    <ul>
     <li>Request header</li>
      
```json
{
  "Authorization": "String",
  "Content-Type": "application/json"
}
```
      <li>Request body</li>
      
```json
{
  "nickname": "string",
  "reliableName": "string",
  "childAge": 0
}
```

  <li>Response body </li>  

```json
업데이트 성공  
```

  </ul>
 </details>

### 🔗 Board

 <details markdown = "1">
  <summary>보드 생성 : api/board/create (POST)</summary>
    <ul>
      <li>Header</li>
      
```json
{
	"Header" : {
		"Authoriztion" : "Bearer " + localstorge.getItem(token),
		"Content-Type" : "application/json" 
			}
}
```

  <li>Request Body </li>  

```json
{
    "boardTitle": "실험 제목",
    "boardDescription": "새롭게2 설명",
    "boardCategory": "새로운2 카테고리",
    "boardImage": "새롭게 이미지 URL",
    "boardWriterId": "1234",
    "boardWriterFanclub" : "세븐틴",
    "boardWriterNickname": "게시물 작성자 닉네임"
}
```

  <li>Response body</li>  

```json
{
    "result": true,
    "message": "Board Create Success",
    "data": null
}
```

  </ul>
 </details>


 <details markdown = "1">
  <summary >보드 전체 읽기(로그인 전) : api/board/allList(GET)</summary>
    <ul>

  <li>Response Body </li>  

```json
{
    "result": true,
    "message": "성공",
    "data": [
        {
            "boardNumber": 6,
            "boardTitle": "이미지제목",
            "boardDescription": "업데이트 설명",
            "boardCategory": "업데이트 카테고리",
            "boardContext": "url",
            "boardClick": 4,
            "boardImage": "z",
            "boardWriterId": "1234",
            "boardWriterNickname": "게시물 작성자 닉네임",
            "boardWriterFanclub": "세븐틴",
            "boardWriteDate": "2023-08-12T06:50:54.000+00:00"
        },
        {
            "boardNumber": 4,
            "boardTitle": "새로운2 제목",
            "boardDescription": "새롭게2 설명",
            "boardCategory": "새로운2 url",
            "boardContext": "새로운2 내용",
            "boardClick": 0,
            "boardImage": "z",
            "boardWriterId": "1234",
            "boardWriterNickname": "게시물 작성자 닉네임",
            "boardWriterFanclub": "세븐틴",
            "boardWriteDate": "2023-08-12T04:58:16.000+00:00"
        },
        {
            "boardNumber": 5,
            "boardTitle": "실험 제목",
            "boardDescription": "새롭게2 설명",
            "boardCategory": "새로운2 url",
            "boardContext": "새로운2 내용",
            "boardClick": 0,
            "boardImage": "새롭게 이미지 URL",
            "boardWriterId": "1234",
            "boardWriterNickname": "게시물 작성자 닉네임",
            "boardWriterFanclub": "세븐틴",
            "boardWriteDate": "2023-08-12T04:55:48.000+00:00"
        }
    ]
}
```

  </ul>
 </details>

 <details markdown = "1">
  <summary>보드 하나 읽기 :api/board/oneList/{id}(GET)</summary>
    <ul>
      <li>Header</li>
      
```json
{
	"Header" : {
		"Authoriztion" : "Bearer " + localstorge.getItem(token),
		"Content-Type" : "application/json" 
			}
}
```

  <li>PathVariable </li>  

```json
{
	"id" : "boardNumber"
}
```

  <li>Response body</li>  

```json
{
    "result": true,
    "message": "Read One List",
    "data": {
        "boardNumber": 3,
        "boardTitle": "새로운 제목",
        "boardDescription": "새롭게 설명",
        "boardCategory": "새로운 url",
        "boardContext": "새로운 내용",
        "boardClick": 6,
        "boardImage": "새롭게 이미지 URL",
        "boardWriterId": "1234",
        "boardWriterNickname": "게시물 작성자 닉네임",
        "boardWriterFanclub": "세븐틴",
        "boardWriteDate": "2023-08-12T04:40:00.000+00:00"
    }
}
```

  </ul>
 </details>
 
 <details markdown = "1">
  <summary>보드 삭제 : api/board/delete/{id}(DELETE)</summary>
    <ul>
      <li>Header</li>
      
```json
{
	"Header" : {
		"Authoriztion" : "Bearer " + localstorge.getItem(token),
		"Content-Type" : "application/json" 
			}
}
```

  <li>PathVariable </li>  

```json
{
	"id" : "dataNoticeId"
}
```

  <li>Response body</li>  

```json
{
    "result": true,
    "message": "Board Delete Success!",
    "data": null
}
```

  </ul>
 </details>


 <details markdown = "1">
  <summary>보드 전체 읽기(로그인 후) : api/board/allList(GET)</summary>
    <ul>
      <li>Header</li>
      
```json
{
	"Header" : {
		"Authoriztion" : "Bearer " + localstorge.getItem(token),
		"Content-Type" : "application/json" 
			}
}
```
  <li>Request Body </li>  

```json
{
	"userFanclub":"세븐틴"    
}
```

  <li>Response body</li>  

```json
{
    "result": true,
    "message": "조회수로 배열",
    "data": [
        {
            "boardNumber": 3,
            "boardTitle": "새로운 제목",
            "boardDescription": "새롭게 설명",
            "boardCategory": "새로운 카테고리",
            "boardContext": "새로운 url",
            "boardClick": 6,
            "boardImage": "새롭게 이미지 URL",
            "boardWriterId": "1234",
            "boardWriterNickname": "게시물 작성자 닉네임",
            "boardWriterFanclub": "세븐틴",
            "boardWriteDate": "2023-08-12T04:40:00.000+00:00"
        },
        {
            "boardNumber": 6,
            "boardTitle": "이미지제목",
            "boardDescription": "업데이트 설명",
            "boardCategory": "업데이트 카테고리",
            "boardContext": "url",
            "boardClick": 4,
            "boardImage": "z",
            "boardWriterId": "1234",
            "boardWriterNickname": "게시물 작성자 닉네임",
            "boardWriterFanclub": "세븐틴",
            "boardWriteDate": "2023-08-12T06:50:54.000+00:00"
        },
        {
            "boardNumber": 7,
            "boardTitle": "실험 제목",
            "boardDescription": "새롭게2 설명",
            "boardCategory": "새로운2 카테고리",
            "boardContext": "새로운2 내용",
            "boardClick": 1,
            "boardImage": "새롭게 이미지 URL",
            "boardWriterId": "1234",
            "boardWriterNickname": "게시물 작성자 닉네임",
            "boardWriterFanclub": "세븐틴",
            "boardWriteDate": "2023-08-12T06:03:49.000+00:00"
        },
        {
            "boardNumber": 4,
            "boardTitle": "새로운2 제목",
            "boardDescription": "새롭게2 설명",
            "boardCategory": "새로운2 카테고리",
            "boardContext": "새로운2 내용",
            "boardClick": 0,
            "boardImage": "z",
            "boardWriterId": "1234",
            "boardWriterNickname": "게시물 작성자 닉네임",
            "boardWriterFanclub": "세븐틴",
            "boardWriteDate": "2023-08-12T04:58:16.000+00:00"
        },
        {
            "boardNumber": 5,
            "boardTitle": "실험 제목",
            "boardDescription": "새롭게2 설명",
            "boardCategory": "새로운2 카테고리",
            "boardContext": "새로운2 내용",
            "boardClick": 0,
            "boardImage": "새롭게 이미지 URL",
            "boardWriterId": "1234",
            "boardWriterNickname": "게시물 작성자 닉네임",
            "boardWriterFanclub": "세틴",
            "boardWriteDate": "2023-08-12T04:55:48.000+00:00"
        }
    ]
}
```

  </ul>
 </details>


 <details markdown = "1">
  <summary>보드 생성 : api/board/create (POST)</summary>
    <ul>
      <li>Header</li>
      
```json
{
	"Header" : {
		"Authoriztion" : "Bearer " + localstorge.getItem(token),
		"Content-Type" : "application/json" 
			}
}
```

  <li>Request Body </li>  

```json
{
    "boardTitle": "실험 제목",
    "boardDescription": "새롭게2 설명",
    "boardCategory": "새로운2 카테고리",
    "boardImage": "새롭게 이미지 URL",
    "boardWriterId": "1234",
    "boardWriterFanclub" : "세븐틴",
    "boardWriterNickname": "게시물 작성자 닉네임"
}
```

  <li>Response body</li>  

```json
{
    "result": true,
    "message": "Board Create Success",
    "data": null
}
```

  </ul>
 </details>



<br>

 

<!-- Stack Icon Refernces -->

[sp]: /readme/spring.png
[ec2]: /readme/EC2.png
[pm]: /readme/postman.png
