@echo off
REM ============================================================
REM Run script for Complaint Service - Uses Java 21
REM ============================================================
REM Prerequisites: Docker containers must be running
REM   docker compose up -d postgres kafka zookeeper
REM ============================================================

REM Force Java 21
set JAVA_HOME=C:\Program Files\Java\jdk-21
set PATH=%JAVA_HOME%\bin;%PATH%

echo ============================================================
echo Starting Complaint Service with Java 21
echo ============================================================
echo.
java -version
echo.

REM Check if JAR exists
if not exist "target\complaint_service-0.0.1-SNAPSHOT.jar" (
    echo JAR not found. Building first...
    call mvnw.cmd clean package -DskipTests
    if %ERRORLEVEL% NEQ 0 (
        echo Build failed!
        pause
        exit /b 1
    )
)

echo.
echo Application will be available at http://localhost:8080
echo Press Ctrl+C to stop the service
echo.

REM Run with UTC timezone to avoid PostgreSQL timezone issues
java -Duser.timezone=UTC -jar target\complaint_service-0.0.1-SNAPSHOT.jar

pause
