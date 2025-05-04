#!/bin/bash

# 커밋 메시지 가져오기
commit_msg_file=$1
commit_msg=$(cat "$commit_msg_file")

# 정규식: 커밋타입 ( #이슈번호 ) : 커밋메시지
regex="^(feat|fix|chore|docs|style|refactor|test|perf|ci|build|revert)[[:space:]]*\([[:space:]]*#[0-9]+[[:space:]]*\)[[:space:]]*:[[:space:]]+.+"

if [[ "$commit_msg" =~ $regex ]]; then
    echo "✅ 커밋 메시지 형식이 올바릅니다."
    exit 0
else
    echo "❌ 커밋 메시지 형식이 잘못되었습니다."
    echo "형식: 커밋타입 ( #이슈번호 ) : 커밋메시지"
    echo "예시: fix ( #123 ) : 로그인 실패 시 예외 처리 추가"
    exit 1
fi
