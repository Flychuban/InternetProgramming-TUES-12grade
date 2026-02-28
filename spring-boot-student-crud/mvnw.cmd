@REM Maven Wrapper script for Windows
@REM This script downloads and runs Apache Maven if not already available

@echo off
setlocal

set "MAVEN_PROJECTBASEDIR=%~dp0"
set "WRAPPER_PROPERTIES=%MAVEN_PROJECTBASEDIR%.mvn\wrapper\maven-wrapper.properties"
set "MAVEN_HOME=%USERPROFILE%\.m2\wrapper"

for /f "tokens=2 delims==" %%a in ('findstr "distributionUrl" "%WRAPPER_PROPERTIES%"') do set "DIST_URL=%%a"

if "%DIST_URL%"=="" set "DIST_URL=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.6/apache-maven-3.9.6-bin.zip"

for %%i in ("%DIST_URL%") do set "DIST_FILENAME=%%~nxi"
set "DIST_NAME=%DIST_FILENAME:-bin.zip=%"
set "MAVEN_INSTALL=%MAVEN_HOME%\dists\%DIST_NAME%"

if not exist "%MAVEN_INSTALL%" (
    echo Downloading Maven from %DIST_URL%
    mkdir "%MAVEN_HOME%\dists" 2>nul
    powershell -Command "Invoke-WebRequest -Uri '%DIST_URL%' -OutFile '%TEMP%\maven-wrapper.zip'"
    echo Installing Maven
    powershell -Command "Expand-Archive -Path '%TEMP%\maven-wrapper.zip' -DestinationPath '%MAVEN_HOME%\dists' -Force"
    del "%TEMP%\maven-wrapper.zip"
)

set "MAVEN_CMD=%MAVEN_INSTALL%\bin\mvn.cmd"

"%MAVEN_CMD%" -Dmaven.multiModuleProjectDirectory="%MAVEN_PROJECTBASEDIR%" %*

endlocal
