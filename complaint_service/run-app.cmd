@echo off
set JAVA_HOME=C:\Program Files\Java\jdk-21
set PATH=%JAVA_HOME%\bin;%PATH%

echo Starting Complaint Service with Java 21...
echo.
java -version
echo.
echo Connecting to Neon PostgreSQL Database...
echo Application will be available at http://localhost:8080
echo.

java -jar target\complaint_service-0.0.1-SNAPSHOT.jar

pause
