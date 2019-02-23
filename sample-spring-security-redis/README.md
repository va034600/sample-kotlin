GET localhost:8080/pub/test

###

POST localhost:8080/auth/login
Content-Type: application/x-www-form-urlencoded

email=aaa2&password=password

###

GET localhost:8080/prv/test
Cookie: SESSION=ZDZlYTVhOTgtZWMxYS00MDIzLWIzMjUtN2VkODk1NWUyZTAy

###

POST localhost:8080/auth/logout
Cookie: SESSION=ZDZlYTVhOTgtZWMxYS00MDIzLWIzMjUtN2VkODk1NWUyZTAy

###
