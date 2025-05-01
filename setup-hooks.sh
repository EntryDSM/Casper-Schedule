#!/bin/bash

# 색상 정의
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

echo -e "${YELLOW}Spring 프로젝트 빌드 검사 훅 설정을 시작합니다...${NC}"

# OS 감지
if [[ "$OSTYPE" == "darwin"* ]]; then
    OS="macOS"
elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
    OS="Linux"
elif [[ "$OSTYPE" == "msys" || "$OSTYPE" == "win32" ]]; then
    OS="Windows"
else
    OS="Unknown"
fi

echo -e "${YELLOW}감지된 운영체제: $OS${NC}"

# pre-commit 설치 확인
if ! command -v pre-commit &> /dev/null; then
    echo -e "${YELLOW}pre-commit이 설치되어 있지 않습니다. 설치를 시작합니다...${NC}"

    # 운영체제별 설치 방법
    if [[ "$OS" == "macOS" ]]; then
        if command -v brew &> /dev/null; then
            brew install pre-commit
        else
            pip install pre-commit
        fi
    elif [[ "$OS" == "Linux" ]]; then
        pip install pre-commit
    elif [[ "$OS" == "Windows" ]]; then
        pip install pre-commit
    else
        echo -e "${RED}지원되지 않는 운영체제입니다. 수동으로 pre-commit을 설치해주세요.${NC}"
        exit 1
    fi

    if ! command -v pre-commit &> /dev/null; then
        echo -e "${RED}pre-commit 설치에 실패했습니다. 수동으로 설치해주세요:${NC}"
        echo "pip install pre-commit"
        exit 1
    fi
fi

echo -e "${GREEN}pre-commit이 설치되어 있습니다.${NC}"

# pre-commit 설정 파일이 없으면 생성
if [ ! -f .pre-commit-config.yaml ]; then
    echo -e "${YELLOW}pre-commit 설정 파일을 생성합니다...${NC}"

    # 빌드 시스템 감지
    if [ -f "build.gradle" ] || [ -f "build.gradle.kts" ]; then
        BUILD_SYSTEM="gradle"
        BUILD_COMMAND="./gradlew"
    elif [ -f "pom.xml" ]; then
        BUILD_SYSTEM="maven"
        BUILD_COMMAND="./mvnw"
    else
        echo -e "${RED}지원되는 빌드 시스템을 찾을 수 없습니다.${NC}"
        exit 1
    fi

    # 빌드 명령 실행 가능한지 확인
    if [ "$BUILD_SYSTEM" == "gradle" ]; then
        if [ ! -x "./gradlew" ]; then
            echo -e "${YELLOW}gradlew에 실행 권한을 부여합니다...${NC}"
            chmod +x ./gradlew
        fi
    elif [ "$BUILD_SYSTEM" == "maven" ]; then
        if [ ! -x "./mvnw" ]; then
            echo -e "${YELLOW}mvnw에 실행 권한을 부여합니다...${NC}"
            chmod +x ./mvnw
        fi
    fi

    if [ "$BUILD_SYSTEM" == "gradle" ]; then
        cat > .pre-commit-config.yaml << 'EOL'
repos:
  - repo: local
    hooks:
      - id: gradle-build-check
        name: Gradle Build Check
        entry: ./gradlew
        args: [clean, build, -x, test]
        language: system
        pass_filenames: false
        files: '(\.gradle|gradle\.properties|settings\.gradle(\.kts)?|build\.gradle(\.kts)?|src/.*\.(java|kt|xml|properties))$'
        verbose: true
EOL
    elif [ "$BUILD_SYSTEM" == "maven" ]; then
        cat > .pre-commit-config.yaml << 'EOL'
repos:
  - repo: local
    hooks:
      - id: maven-build-check
        name: Maven Build Check
        entry: ./mvnw
        args: [clean, compile]
        language: system
        pass_filenames: false
        files: '(pom\.xml|src/.*\.(java|kt|xml|properties))$'
        verbose: true
EOL
    fi

    echo -e "${GREEN}pre-commit 설정 파일이 생성되었습니다.${NC}"
fi

# pre-commit 훅 설치
echo -e "${YELLOW}Git 훅을 설치합니다...${NC}"
pre-commit install

echo -e "${GREEN}설정이 완료되었습니다!${NC}"
echo -e "${GREEN}이제부터 커밋 시 Spring 프로젝트 빌드 검사가 자동으로 실행됩니다.${NC}"
echo -e "${YELLOW}참고: 긴급한 상황에서 빌드 검사를 건너뛰려면 'git commit --no-verify' 옵션을 사용하세요.${NC}"