# Spring Boot + Hibernate + MySql


Used technologies:

* Spring Boot - 1.4.2.RELEASE
* Spring Boot Data JPA - 1.4.2.RELEASE
* Hibernate - 5.0.1.Final
* MySql Connection Java 6.0.5
* com.fasterxml.jackson.core - 2.8.4


**Headers**
1. [Get All Users](#get-all-users)
2. [Get User for Name](#get-user-for-name)
3. [Get User for ID](#get-user-for-id)
4. [User add](#user-add)
   1. [Sucess sending](#sucess-sending)

# API
## Get all users

GET<br>
http://localhost:8080/users

Response<br>
```json
[
  {
    "id": 1,
    "name": "kanan",
    "pass": "123456"
  },
  {
    "id": 2,
    "name": "username",
    "pass": "qwerty"
  },
  {
    "id": 3,
    "name": "test",
    "pass": "12345"
  }
]
```

## Get User for Name

GET<br>
http://localhost:8080/users?name=kanan

Response<br>
```json
{
  "id": 1,
  "name": "kanan",
  "pass": "123456"
}
```

## Get user for id

GET<br>
http://localhost:8080/users?id=3

Response<br>
```json
{
  "id": 3,
  "name": "test",
  "pass": "12345"
}
```

## User add

POST<br>
http://localhost:8080/users/add

###Success sending###
Sending body<br>
```json
{
	"name":"test3",
	"pass":"12345"
}
```

Response<br>
```json
{
  "message": "Data added",
  "success": true
}
```

**Incorrect sending**<br>
Sending body<br>
```json
{
	"pass":"12345"
}
```

Response<br>
```json
{
  "message": "Sending data is inconrrect",
  "success": false
}
```

## Update User

POST<br>
http://localhost:8080/users/update?id=4

**Correct sending**<br>
Sending body<br>
```json
{
	"name":"test5",
	"pass":"test5"
}
```

Response<br>
```json
{
  "message": "Data updated",
  "success": true
}
```

**Incorrect sending**<br>
Sending with incorrect id<br>
http://localhost:8080/users/update?id=6

Sending body<br>
```json
{
  "name": "test5",
  "pass": "test5"
}
```

Response<br>
```json
{
  "message": "User with this id cannot find",
  "success": false
}
```

Sending with empty body<br>

Sending body<br>
```json
{
  
}
```

Response<br>
```json
{
  "message": "in body has problem",
  "success": false
}
```

## Delete user

GET<br>
http://localhost:8080/users/delete/4

**Correct sending**<br>
Response<br>
```json
{
  "message": "Data deleted",
  "success": true
}
```

**Incorrect sending**<br>
http://localhost:8080/users/delete/11<br>
Response<br>
```json
{
  "message": "This id cannot find",
  "success": false
}
```
