Task manager
A Task manager application built with Spring Boot for the backend, PostgreSQL for the database, Next.js for the frontend and using Spring Security JWT
Prerequisites

    Java 17 or higher
    Node.js and npm
    PostgreSQL (local installation or container)

Backend Setup (Spring Boot)

    Clone the repository:

    git clone https://github.com/abdelmottalib/springboot-react-JWT-taskManager

Configure Database:

    Create a PostgreSQL database named chat.

    Update database configuration in backend/src/main/resources/application.properties:

    spring.datasource.url=jdbc:postgresql://localhost:5432/chat
    spring.datasource.username=<your_db_username>
    spring.datasource.password=<your_db_password>

Run the Spring Boot Application:

./mvnw spring-boot:run

    The backend will run on http://localhost:8080.

Frontend Setup (Next.js)

    Navigate to the frontend folder:

    cd frontend

Install Dependencies:

npm install

Run the Next.js Application:

npm run dev

    The frontend will be available at http://localhost:3000.

Usage

    Access the frontend application at http://localhost:3000.
    register
    manage tasks

Notes

    This project can easily be refactored for use with React.
    Make sure to have a PostgreSQL server running either locally or in a container.
