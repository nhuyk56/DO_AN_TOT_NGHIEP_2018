@echo off
set /p mota= Hay~_mo_ta_truoc_khi_backup:
For /f "tokens=2-4 delims=/ " %%a in ('date /t') do (set mydate=Ngay%%b_Thang%%a_Nam%%c)
For /f "tokens=1-2 delims=/:" %%a in ('time /t') do (set mytime=%%aGh%%b)
echo %mydate%_%mytime%
cd C:\"Program Files"\WinRAR
Rar.exe a -r C:\Users\Nguyen.Nhu.Y\Desktop\DoAn\backup_project\ynn_shop[%mydate%]_[%mytime%]_[%mota: =_%]_.zip C:\Users\Nguyen.Nhu.Y\AndroidStudioProjects\ynn_shop\*.*
Rar.exe a -r C:\Users\Nguyen.Nhu.Y\Desktop\DoAn\backup_project\asp[%mydate%]_[%mytime%]_[%mota: =_%]_.zip C:\Users\Nguyen.Nhu.Y\Documents\"Visual Studio 2013"\Projects\WebApplication2\*.*
explorer.exe C:\Users\Nguyen.Nhu.Y\Desktop\DoAn\backup_project

