@echo off
chcp 65001 > nul
title 学生学籍管理系统 v2.0

echo.
echo ========================================
echo    学生学籍管理系统 v2.0 启动脚本
echo ========================================
echo.

REM 检查Java环境
java -version > nul 2>&1
if %errorlevel% neq 0 (
    echo [错误] 未找到Java环境，请确保已安装Java 8或更高版本
    echo 请访问 https://www.oracle.com/java/technologies/downloads/ 下载安装
    pause
    exit /b 1
)

REM 检查JDBC驱动
if not exist "jdbc\uxdbjdbc-42.2.12.jar" (
    echo [错误] 未找到UXDB JDBC驱动文件
    echo 请确保 jdbc\uxdbjdbc-42.2.12.jar 文件存在
    pause
    exit /b 1
)

REM 检查源代码
if not exist "src\com\artisan\view\FirstView.java" (
    echo [错误] 未找到源代码文件
    echo 请确保项目结构完整
    pause
    exit /b 1
)

echo [信息] 正在编译Java源代码...
javac -encoding UTF-8 -cp ".;jdbc\uxdbjdbc-42.2.12.jar" -d out src\com\artisan\view\*.java src\com\artisan\dao\*.java src\com\artisan\model\*.java src\com\artisan\util\*.java

if %errorlevel% neq 0 (
    echo [错误] 编译失败，请检查代码错误
    pause
    exit /b 1
)

echo [信息] 编译成功！
echo [信息] 正在启动学生学籍管理系统...
echo.

REM 复制配置文件到输出目录
if exist "src\database.properties" (
    copy "src\database.properties" "out\" > nul
)

REM 创建日志目录
if not exist "logs" mkdir logs

REM 运行程序
java -Dfile.encoding=UTF-8 -cp ".;jdbc\uxdbjdbc-42.2.12.jar;out" com.artisan.view.FirstView

echo.
echo 程序已退出，感谢使用！
pause
