# OTP-Authentication-Demo-in-Spring-using-Redis

Demo Spring boot project to show implementation of OTP (One Time Password) Authentication. Redis is used for storing and retrieving OTP.
This is a well structured Spring application with Controller, Service and Config class. It is fully tested using Junit and Mocktio.

## APIs

There are only 2 endpoints for this application. 
- Generate OTP: http://localhost:8080/auth/generate?phoneNumber={{phoneNumber}}
- Validate OTP: http://localhost:8080/auth/validate?phoneNumber={{phoneNumber}}&otp={{otp}}

## Requirements

To be able to run this application, You need to have Redis running locally.
You can download Dockerised Redis --> https://hub.docker.com/_/redis and run using "docker run -d --name redis-server -p 6379:6379 redis"

## Instructions

- Clone this Repo
- Startup Redis and make sure it is running on port 6379
- Now call the apis, call generate first then validate.

## Result

When the generateOtp endpoint is called with any random phoneNumber string, it generates an OTP and sends a 200 response to the client.

![otp_console](https://github.com/user-attachments/assets/06638d80-1f63-420b-9d91-4a87cb3751ad)

![generate_postman](https://github.com/user-attachments/assets/237f8922-08d5-4a45-8e62-7efa3fc34d36)

When the validate endpoint is called, with the correct phoneNumber and generate OTP, you get a message saying OTP is verified Succesfully.

![validate_postman](https://github.com/user-attachments/assets/be4d8a78-36b7-46bf-8908-c706b8ee1d63)

If the client tries to use the generated OTP after 60 seconds, or uses an invalid OTP. An "OTP expired or invalid!" message is sent to the client

![expired_otp_postman](https://github.com/user-attachments/assets/4395c50f-c4fd-43ce-a165-36aa25ac86f2)




