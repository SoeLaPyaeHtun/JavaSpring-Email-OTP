# JavaSpring Email-OTP

This is login system with one time password which is send by email.

## create a java spring starter project with following setting:

- Type : Marven
- JavaVersion: 17
- Language: Java
- Spring Version: 2.7.6


#### dependencies
- spring web
- Thymeleaf
- Spring Boot Devtool
- JPA
- Spring Security
- Mail
- Mysql Connector
- spring boot session
  

#### features
- login , logout with spring boot security
- users password encoding and decoding with bcrypt object
- spring security redirect users page after login based on roles
- need to login after failed password five times
- send one time password via email (six digits )
- spring boot security session timeout
- custom error page
- google recaptcha v3


# Reference
- https://spring.io/projects/spring-security
- https://spring.io/projects/spring-session
- https://www.codejava.net/frameworks/spring-boot/spring-security-otp-email-tutorial
- https://www.youtube.com/watch?v=PHKE0_anFLg&t=1408s
- https://www.geeksforgeeks.org/spring-boot-sending-email-via-smtp/
- https://www.baeldung.com/spring-boot-custom-error-page
