@echo off

set JAVA=%JAVA_HOME%\bin\java
set DEPLOY_DIR=classes
set LIB_DIR=..\lib

set CLASSPATH=%DEPLOY_DIR%;%LIB_DIR%\jbossall-client.jar

%JAVA% -cp %CLASSPATH% Main

pause