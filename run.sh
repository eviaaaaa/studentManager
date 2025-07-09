#!/bin/bash

# 设置UTF-8编码
export LANG=zh_CN.UTF-8
export LC_ALL=zh_CN.UTF-8

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}"
echo "========================================"
echo "   学生学籍管理系统 v2.0 启动脚本"
echo "========================================"
echo -e "${NC}"

# 检查Java环境
if ! command -v java &> /dev/null; then
    echo -e "${RED}[错误] 未找到Java环境，请确保已安装Java 8或更高版本${NC}"
    echo "请访问 https://www.oracle.com/java/technologies/downloads/ 下载安装"
    exit 1
fi

echo -e "${GREEN}[信息] Java环境检查通过${NC}"

# 检查JDBC驱动
if [ ! -f "jdbc/uxdbjdbc-42.2.12.jar" ]; then
    echo -e "${RED}[错误] 未找到UXDB JDBC驱动文件${NC}"
    echo "请确保 jdbc/uxdbjdbc-42.2.12.jar 文件存在"
    exit 1
fi

echo -e "${GREEN}[信息] JDBC驱动检查通过${NC}"

# 检查源代码
if [ ! -f "src/com/artisan/view/FirstView.java" ]; then
    echo -e "${RED}[错误] 未找到源代码文件${NC}"
    echo "请确保项目结构完整"
    exit 1
fi

echo -e "${GREEN}[信息] 源代码检查通过${NC}"

# 创建输出目录
mkdir -p out

echo -e "${YELLOW}[信息] 正在编译Java源代码...${NC}"

# 编译Java源代码
javac -encoding UTF-8 -cp ".:jdbc/uxdbjdbc-42.2.12.jar" -d out \
    src/com/artisan/view/*.java \
    src/com/artisan/dao/*.java \
    src/com/artisan/model/*.java \
    src/com/artisan/util/*.java

if [ $? -ne 0 ]; then
    echo -e "${RED}[错误] 编译失败，请检查代码错误${NC}"
    exit 1
fi

echo -e "${GREEN}[信息] 编译成功！${NC}"

# 复制配置文件到输出目录
if [ -f "src/database.properties" ]; then
    cp "src/database.properties" "out/"
    echo -e "${GREEN}[信息] 配置文件已复制${NC}"
fi

# 创建日志目录
mkdir -p logs

echo -e "${YELLOW}[信息] 正在启动学生学籍管理系统...${NC}"
echo

# 运行程序
java -Dfile.encoding=UTF-8 -cp ".:jdbc/uxdbjdbc-42.2.12.jar:out" com.artisan.view.FirstView

echo
echo -e "${BLUE}程序已退出，感谢使用！${NC}"
