 
## Basic information about the project

### How to build:
1. Go to project directory
2. Project build : ./gradlew clean build
3. Starting the Application server: ./gradlew tomcatRun
4. Open a new window, go to the project directory, execute commands to delete and create tables
 - Deleting tables: ./gradlew dropSql
 - Creating tables: ./gradlew createSql
7. Link to go to Application UI: http://localhost:8080/
10. To stop the Application:
  - [Ctrl] + [C] 
  - Y
  - ./gradlew --stop

### Technologies
- Java 11
- Servlet
- Gradle
- H2
- Apache Tomcat








