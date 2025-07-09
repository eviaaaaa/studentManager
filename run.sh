#!/bin/bash

# ����UTF-8����
export LANG=zh_CN.UTF-8
export LC_ALL=zh_CN.UTF-8

# ��ɫ����
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}"
echo "========================================"
echo "   ѧ��ѧ������ϵͳ v2.0 �����ű�"
echo "========================================"
echo -e "${NC}"

# ���Java����
if ! command -v java &> /dev/null; then
    echo -e "${RED}[����] δ�ҵ�Java��������ȷ���Ѱ�װJava 8����߰汾${NC}"
    echo "����� https://www.oracle.com/java/technologies/downloads/ ���ذ�װ"
    exit 1
fi

echo -e "${GREEN}[��Ϣ] Java�������ͨ��${NC}"

# ���JDBC����
if [ ! -f "jdbc/uxdbjdbc-42.2.12.jar" ]; then
    echo -e "${RED}[����] δ�ҵ�UXDB JDBC�����ļ�${NC}"
    echo "��ȷ�� jdbc/uxdbjdbc-42.2.12.jar �ļ�����"
    exit 1
fi

echo -e "${GREEN}[��Ϣ] JDBC�������ͨ��${NC}"

# ���Դ����
if [ ! -f "src/com/artisan/view/FirstView.java" ]; then
    echo -e "${RED}[����] δ�ҵ�Դ�����ļ�${NC}"
    echo "��ȷ����Ŀ�ṹ����"
    exit 1
fi

echo -e "${GREEN}[��Ϣ] Դ������ͨ��${NC}"

# �������Ŀ¼
mkdir -p out

echo -e "${YELLOW}[��Ϣ] ���ڱ���JavaԴ����...${NC}"

# ����JavaԴ����
javac -encoding UTF-8 -cp ".:jdbc/uxdbjdbc-42.2.12.jar" -d out \
    src/com/artisan/view/*.java \
    src/com/artisan/dao/*.java \
    src/com/artisan/model/*.java \
    src/com/artisan/util/*.java

if [ $? -ne 0 ]; then
    echo -e "${RED}[����] ����ʧ�ܣ�����������${NC}"
    exit 1
fi

echo -e "${GREEN}[��Ϣ] ����ɹ���${NC}"

# ���������ļ������Ŀ¼
if [ -f "src/database.properties" ]; then
    cp "src/database.properties" "out/"
    echo -e "${GREEN}[��Ϣ] �����ļ��Ѹ���${NC}"
fi

# ������־Ŀ¼
mkdir -p logs

echo -e "${YELLOW}[��Ϣ] ��������ѧ��ѧ������ϵͳ...${NC}"
echo

# ���г���
java -Dfile.encoding=UTF-8 -cp ".:jdbc/uxdbjdbc-42.2.12.jar:out" com.artisan.view.FirstView

echo
echo -e "${BLUE}�������˳�����лʹ�ã�${NC}"
