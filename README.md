This is a simple authentication project built using Spring Boot and Java 17, implementing JWT (JSON Web Token) for secure authentication and authorization. 
It includes basic registration and login functionalities with input validation and a global exception handler for better error management.

✨ Features
✅ User Registration

🔐 JWT-based Login Authentication

🧾 Input Validation (using annotations like @Email, @NotBlank, etc.)

🧰 Global Exception Handling (using @ControllerAdvice)

📦 Built with Spring Boot and Java 17

📚 Technologies Used :

Java 17
Spring Boot
Spring Security
JWT (io.jsonwebtoken)
Hibernate Validator
Spring DevTools
Maven or Gradle (choose the one you used)

🧪 How to Run
1/Clone the repository: 
git clone https://github.com/samerben/Simple-JWT-SpringBoot-Auth.git
2/Run the application

⚙️ Configuration :

Before running the project, make sure to configure your application properties to match your local environment. 
This can be done in the application.properties file located in src/main/resources.

Update the following properties to point to your MySQL database:
  spring.datasource.url=jdbc:mysql://localhost:3306/authjwt?useSSL=false
  spring.datasource.username=root
  spring.datasource.password=




