@echo off
chcp 65001 > nul
title ѧ��ѧ������ϵͳ v2.0

echo.
echo ========================================
echo    ѧ��ѧ������ϵͳ v2.0 �����ű�
echo ========================================
echo.

REM ���Java����
java -version > nul 2>&1
if %errorlevel% neq 0 (
    echo [����] δ�ҵ�Java��������ȷ���Ѱ�װJava 8����߰汾
    echo ����� https://www.oracle.com/java/technologies/downloads/ ���ذ�װ
    pause
    exit /b 1
)

REM ���JDBC����
if not exist "jdbc\uxdbjdbc-42.2.12.jar" (
    echo [����] δ�ҵ�UXDB JDBC�����ļ�
    echo ��ȷ�� jdbc\uxdbjdbc-42.2.12.jar �ļ�����
    pause
    exit /b 1
)

REM ���Դ����
if not exist "src\com\artisan\view\FirstView.java" (
    echo [����] δ�ҵ�Դ�����ļ�
    echo ��ȷ����Ŀ�ṹ����
    pause
    exit /b 1
)

echo [��Ϣ] ���ڱ���JavaԴ����...
javac -encoding UTF-8 -cp ".;jdbc\uxdbjdbc-42.2.12.jar" -d out src\com\artisan\view\*.java src\com\artisan\dao\*.java src\com\artisan\model\*.java src\com\artisan\util\*.java

if %errorlevel% neq 0 (
    echo [����] ����ʧ�ܣ�����������
    pause
    exit /b 1
)

echo [��Ϣ] ����ɹ���
echo [��Ϣ] ��������ѧ��ѧ������ϵͳ...
echo.

REM ���������ļ������Ŀ¼
if exist "src\database.properties" (
    copy "src\database.properties" "out\" > nul
)

REM ������־Ŀ¼
if not exist "logs" mkdir logs

REM ���г���
java -Dfile.encoding=UTF-8 -cp ".;jdbc\uxdbjdbc-42.2.12.jar;out" com.artisan.view.FirstView

echo.
echo �������˳�����лʹ�ã�
pause
