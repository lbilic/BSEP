@ECHO OFF

SET IDENTITY=%userdomain%\%username%

SET /P USER="Which user is the script for(type pki, siem or user): "

2>NUL CALL :CASE_%USER% # jump to :CASE_pki, :CASE_siem, etc.
IF ERRORLEVEL 1 CALL :DEFAULT_CASE

ECHO Done.
EXIT /B

:CASE_pki
  runas /user:Administrator icacls main\resources\jks\*.jks /inheritance:d
  icacls main\resources\jks\*.jks /grant:r %IDENTITY%:F
  GOTO END_CASE
:CASE_siem
  runas /user:Administrator icacls main\resources\jks\*.jks /inheritance:d
  icacls main\resources\jks\*.jks /grant:r %IDENTITY%:R
  GOTO END_CASE
:CASE_user
  runas /user:Administrator icacls main\resources\jks\*.jks /inheritance:d
  icacls main\resources\jks\*.jks /deny %IDENTITY%:RW
  GOTO END_CASE
:DEFAULT_CASE
  ECHO Unknown user "%USER%"
  GOTO END_CASE
:END_CASE
  VER > NUL
  GOTO :EOF