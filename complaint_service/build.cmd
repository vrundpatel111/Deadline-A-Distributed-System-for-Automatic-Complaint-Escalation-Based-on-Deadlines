@echo off
REM ============================================================
REM Build script for Complaint Service - Uses Java 21
REM ============================================================

REM Force Java 21
set JAVA_HOME=C:\Program Files\Java\jdk-21
set PATH=%JAVA_HOME%\bin;%PATH%

echo ============================================================
echo Building Complaint Service with Java 21
echo ============================================================
echo.
java -version
echo.

call mvnw.cmd clean package -DskipTests

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo Build failed!
    pause
    exit /b 1
)

echo.
echo Build successful! JAR created in target folder.
echo.
echo To run: run-app.cmd
echo Or via Docker: docker compose up -d
pause
