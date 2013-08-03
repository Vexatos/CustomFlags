@ECHO off
set message=Auto Commit %DATE%-%TIME%%CD%

git add .
git commit -m "Custom Flags Auto Commit"
git push

START C:\growlnotify.exe "%message%"