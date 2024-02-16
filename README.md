# UJaCha_server

<p align="center">
  <br>
  <img src="./readme/Tune.png">
  <br>
</p>

<h3 align = "center"> 조현병 조기 진단 서비스, tune </h3>


<br/>

## ⚙️ 기술 스택

|  Spring    |  AWS_EC2  |  MySQL    | 
| :--------: | :-------: | :-------: | 
|   ![sp]    |   ![ec2]  |   ![pm]   | 

<br>

## 💽 자료구조
<br/>

### member
    member_id (PK) (BIGINT) (AUTO_INCREMENT)
    uid (VARCHAR(255))
    email (VARCHAR(255))
    nickname (VARCHAR(255))
    reliable_uid (VARCHAR(255))
    child_age (INT)

<br/>

### test
    test_id (PK) (INT) (AUTO_INCREMENT)
    hallucination (INT)
    abnormal_behavior (INT)
    moody (INT)
    delusion (INT)
    total (INT) 
    date (DATE)
    
    member_id (BIGINT) (FK)

<br/>

## 📌 주요 기능
<br/>

### 🔗URL
[API SWAGGER](http://ec2-3-34-143-183.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/index.html#/member%20%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC/updateMember)
<br/>

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
   "업데이트 성공"  
```

  </ul>
 </details>

 <details markdown = "1">
  <summary>회원 조회 : api/member  (GET)</summary>
    <ul>
     <li>Request header</li>
      
```json
{
  "Authorization": "String",
  "Content-Type": "application/json"
}
```

  <li>Response body </li>  

```json
{
  "uid": "string",
  "email": "string",
  "nickname": "string",
  "reliableName": "string",
  "childAge": 0,
  "test": [
    {
      "testId": 0,
      "hallucination": 0,
      "abnormalBehavior": 0,
      "moody": 0,
      "delusion": 0,
      "total": 0,
      "date": "2024-02-16"
    }
  ]
} 
```

  </ul>
 </details>


 <details markdown = "1">
  <summary>남의 정보 조회 : api/member/reliable  (GET)</summary>
    <ul>
     <li>Request header</li>
      
```json
{
  "Authorization": "String",
  "Content-Type": "application/json"
}
```

  <li>Response body </li>  

```json
{
  "uid": "string",
  "email": "string",
  "nickname": "string",
  "reliableName": "string",
  "childAge": 0,
  "test": [
    {
      "testId": 0,
      "hallucination": 0,
      "abnormalBehavior": 0,
      "moody": 0,
      "delusion": 0,
      "total": 0,
      "date": "2024-02-16"
    }
  ]
} 
```

  </ul>
 </details>

 <details markdown = "1">
  <summary>닉네임 중복확인 : api/member/duplicate  (GET)</summary>
    <ul>
     <li>RequestParam</li>
      
```
?name="string
```

  <li>Response body </li>  

```
boolean
```

  </ul>
 </details>

<details markdown = "1">
  <summary>멤버 삭제 : api/member/delete  (DELETE)</summary>
    <ul>
     <li>Request header</li>
      
```json
{
  "Authorization": "String",
  "Content-Type": "application/json"
}
```

  <li>Response body </li>  

```
"삭제 되었습니다."
```

  </ul>
 </details>

<br/>

### ✍️ Test

 <details markdown = "1">
  <summary>TEST(로그인 후) : api/test (POST)</summary>
    <ul>
      <li>Request Header</li>
      
```json
{
  "Authorization": "String",
  "Content-Type": "application/json"
}
```

  <li>Request Body </li>  

```json
{
  "question1": 0,
  "question2": 0,
  "question3": 0,
  "question4": 0,
  "question5": 0,
  "question6": 0,
  "question7": 0,
  "question8": 0,
  "question9": 0,
  "question10": 0,
  "question11": 0,
  "question12": 0
}
```

  <li>Response body</li>  

```json
{
  "testId": 0,
  "hallucination": 0,
  "abnormalBehavior": 0,
  "moody": 0,
  "delusion": 0,
  "total": 0,
  "date": "2024-02-16"
}
```

  </ul>
 </details>


 <details markdown = "1">
  <summary >TEST(로그인 전)(DB에 저장안됨): api/test/first(POST)</summary>
    <ul>
  <li>Request Body </li>  

```json
{
  "question1": 0,
  "question2": 0,
  "question3": 0,
  "question4": 0,
  "question5": 0,
  "question6": 0,
  "question7": 0,
  "question8": 0,
  "question9": 0,
  "question10": 0,
  "question11": 0,
  "question12": 0
}
```

  <li>Response Body </li>  

```json
{
  "hallucination": 0,
  "abnormalBehavior": 0,
  "moody": 0,
  "delusion": 0,
  "total": 0
}
```

  </ul>
  </details>

 <details markdown = "1">
  <summary>오늘 테스트 했는지 안 했는지 확인 :api/test/exist-today (GET)</summary>
    <ul>
      <li>Request Header</li>
      
```json
{
  "Authorization": "String",
  "Content-Type": "application/json"
}
```


  <li>Response body</li>  

```
boolean
```

  </ul>
 </details>


<!-- Stack Icon Refernces -->

[sp]: /readme/spring.png
[ec2]: /readme/EC2.png
[pm]: /readme/mysql.png
