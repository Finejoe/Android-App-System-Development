# Android-App-System-Development
Designed and developed an app login/registration module based on Android API and JavaEE  

**1. Backend Server Development**  
- We realized backend service by Servlet. The response data is encapsulated by JSON, and then parsed by the Android app.  
- We use MySQL database to connect and store the registration information,
in which username is used as the primary key of the table. Also, we applied the MD5 encryption password algorithm.  
- The project should finally be mounted to the linux server.
   
**2. Android App Development**
- Write xml code to design the app UI pages,
realize interfaces such as Welcome, Login, Sign up, Forget password and Change password.
- We use the Volley framework to implement network communication between Android client and server.
  (Including asynchronous thread processing, access to server,
  and communication between client and server using http protocol)
- Use MobTech platform to extend the user SMS verification feature.
   
