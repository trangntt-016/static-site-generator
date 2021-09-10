@echo off

setlocal enableextensions

set input=%2
set input=%input: =-%


set output=%4
set output=%output: =-%

if "%4"=="" java -jar D:\SchoolWork\OSD600\temp\static-site-generator\target\jssg-0.0.1-SNAPSHOT.jar %1 %input%

if not "%4"=="" java -jar D:\SchoolWork\OSD600\temp\static-site-generator\target\jssg-0.0.1-SNAPSHOT.jar %1 %input% %3 %output%
