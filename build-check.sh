#!/bin/bash

# 프로젝트 루트 디렉토리로 이동
cd "$(git rev-parse --show-toplevel)" || exit 1

echo "빌드 검증 시작..."

# Gradle 프로젝트 검증
if [ -f "./gradlew" ]; then
    echo "Gradle 빌드 실행 중..."
    ./gradlew clean build --no-daemon
    BUILD_RESULT=$?

# Maven 프로젝트 검증
elif [ -f "./mvnw" ]; then
    echo "Maven 빌드 실행 중..."
    ./mvnw clean package
    BUILD_RESULT=$?

# IntelliJ 프로젝트 검증 (기본 Java 컴파일 사용)
elif [ -d ".idea" ]; then
    echo "IntelliJ 프로젝트 검증 중..."
    # 임시 출력 디렉토리
    if [ -d "out" ]; then
        rm -rf out/
    fi
    mkdir -p out/
    find src -name "*.java" -print | xargs javac -d out/ 2>/tmp/compile_errors

    if [ $? -ne 0 ]; then
        echo "컴파일 오류:"
        cat /tmp/compile_errors
        BUILD_RESULT=1
    else
        BUILD_RESULT=0
    fi
    rm -f /tmp/compile_errors
else
    echo "지원되는 빌드 시스템을 찾을 수 없습니다."
    exit 1
fi

# 빌드 결과 확인
if [ $BUILD_RESULT -ne 0 ]; then
    echo "빌드 실패! 커밋이 중단됩니다."
    exit 1
else
    echo "빌드 성공! 커밋 계속 진행합니다."
    exit 0
fi