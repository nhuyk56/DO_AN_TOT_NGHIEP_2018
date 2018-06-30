@echo off
For /f "tokens=2-4 delims=/ " %%a in ('date /t') do (set mydate=%%c-%%a-%%b)
For /f "tokens=1-2 delims=/:" %%a in ('time /t') do (set mytime=%%a%%b)
echo %mydate%_%mytime%
cd C:\"Program Files"\WinRAR
Rar.exe a -r C:\Users\Nguyen.Nhu.Y\Desktop\DoAn\backup_project\ynn_shop%mydate%_%mytime%.zip C:\Users\Nguyen.Nhu.Y\AndroidStudioProjects\ynn_shop\*.*
explorer.exe C:\Users\Nguyen.Nhu.Y\Desktop\DoAn\backup_project

