@echo off
REM UTF-8 코드 페이지로 변경 (한글 깨짐 방지)
chcp 65001 >nul
setlocal EnableDelayedExpansion

REM --- 스크립트 설정 ---
REM 1. 현재 브랜치 이름 가져오기
set "current_branch="
for /f "delims=" %%b in ('git branch --show-current') do (
    set "current_branch=%%b"
)

REM 2. 스테이징된 파일 목록 가져오기 (최대 2개)
set "file1="
set "file2="
set i=0
for /f "delims=" %%f in ('git diff --cached --name-only') do (
    set /a i+=1
    if !i! EQU 1 set "file1=%%f"
    if !i! EQU 2 set "file2=%%f"
)

REM 3. 파일 개수 확인 및 예외 처리
if not defined file1 (
    echo ⚠️ 변경된 파일이 없습니다. git add 를 통해 파일을 추가하세요.
    exit /b 1
)
if defined file2 (
    echo ⚠️ 변경된 파일이 2개 이상입니다. 푼 문제만 한 개씩 추가하세요.
    exit /b 1
)
set "file=%file1%"

REM 4. 문제 번호 추출 (sh의 grep/sed 와 동일하게 작동)
set "problem_id="
REM PowerShell을 사용해 파일 경로에서 'boj' 뒤의 숫자(문제 ID)를 정확히 추출
for /f "delims=" %%x in ('powershell -Command "[regex]::Match('%file%', 'boj([0-9]+)').Groups[1].Value"') do (
    set "problem_id=%%x"
)
if not defined problem_id (
    echo ⛔️ 파일 이름이나 경로에서 'boj'로 시작하는 문제 번호를 찾을 수 없습니다: %file%
    exit /b 1
)

REM 5. README.md에서 해당 문제 줄 찾기
set "line="
for /f "usebackq delims=" %%l in ("README.md") do (
    set "current_line=%%l"
    REM findstr를 사용하여 grep과 유사하게 작동
    echo !current_line! | findstr /r /c:"/problem/%problem_id%)" >nul && (
        set "line=!current_line!"
    )
)

REM 5-1. README에 문제가 없으면, main 브랜치에서 pull 후 재시도
if not defined line (
    echo ⛔️ README.md에서 해당 문제를 찾을 수 없습니다.

    if /i "!current_branch!"=="main" (
        echo ⏳ README.md 업데이트 시도중...
        git stash >nul
        git pull
        git stash pop --index >nul 2>nul
        echo ✅ main 브랜치 pull 완료!
    ) else (
        echo ⚠️ 현재 브랜치가 'main'이 아니라서 종료됨: !current_branch!
        exit /b 1
    )

    REM 5-2. 다시 한 번 README.md에서 해당 줄 찾기
    set "line="
    for /f "usebackq delims=" %%l in ("README.md") do (
        set "current_line=%%l"
        echo !current_line! | findstr /r /c:"/problem/%problem_id%)" >nul && (
            set "line=!current_line!"
        )
    )

    if not defined line (
        echo ⛔️ 업데이트된 README.md에서도 해당 문제를 찾을 수 없습니다.
        exit /b 1
    )
)

REM 6. 날짜 추출 (|로 구분된 2번째 열)
for /f "tokens=2 delims=|" %%a in ("!line!") do (
    set "rawDate=%%a"
)
set "rawDate=!rawDate: =!"
set "rawDate=!rawDate:.=!"
set "date=!rawDate:~0,4!"

if not defined date (
    echo ⛔️ 해당 문제의 날짜를 찾을 수 없습니다.
    exit /b 1
)

REM 7. 난이도 태그 추출 (3, 4, 5번째 열 확인)
set "tag="
for /f "tokens=3-5 delims=|" %%a in ("!line!") do (
    set "col3=%%a"
    set "col4=%%b"
    set "col5=%%c"

    echo !col3! | findstr /c:"/problem/%problem_id%)" >nul && set "tag=E"
    echo !col4! | findstr /c:"/problem/%problem_id%)" >nul && set "tag=N"
    echo !col5! | findstr /c:"/problem/%problem_id%)" >nul && set "tag=H"
)
if not defined tag (
    echo ⛔️ 해당 문제의 난이도를 찾을 수 없습니다.
    exit /b 1
)

REM 8. 커밋 메시지 생성
set "message=Solve: !date! !tag! BOJ !problem_id!"

REM 9. 메시지 출력 및 확인
echo.
echo 📝 커밋 메시지 미리보기:
echo !message!
echo.
set "confirm="
set /p confirm=💬 위 메시지로 커밋하고 push 하시겠습니까? (Enter = 진행, n 입력 시 취소):
if /i "!confirm!"=="n" (
    echo 🚫 커밋/푸시가 취소되었습니다.
    exit /b 0
)

REM 10. 최종 pull (커밋 충돌 방지)
if /i "!current_branch!"=="main" (
    git stash >nul
    git pull
    git stash pop --index >nul 2>nul
    echo ✅ main 브랜치 pull 완료!
) else (
    echo ⚠️ 현재 브랜치가 'main'이 아니라서 종료됨: !current_branch!
    exit /b 1
)

REM 11. commit 수행
git commit -m "!message!"
echo ✅ 커밋 완료!

REM 12. main 브랜치에 push
if /i "!current_branch!"=="main" (
    git push origin main
    echo ✅ main 브랜치에 push 완료!
) else (
    echo ⚠️ 현재 브랜치가 'main'이 아니라서 push 생략됨: !current_branch!
)

endlocal