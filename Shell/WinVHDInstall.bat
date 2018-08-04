@echo off
color 87
SET file=WLrLx-vIA$03_8WG5#6Ii+QBd
SET envType=GEN
cls
wpeutil UpdateBootInfo  >nul 2>&1
reg query HKLM\System\CurrentControlSet\Control /v PEFirmwareType >nul 2>&1
if %ERRORLEVEL% == 1 goto pescanfail
SET envType=PE
reg query HKLM\System\CurrentControlSet\Control /v PEFirmwareType > %file%1
(
echo.
echo HKEY_LOCAL_MACHINE\System\CurrentControlSet\Control
echo     PEFirmwareType    REG_DWORD    0x2
echo.
)>%file%2
SET frmwretype=B
for /f "delims=" %%x in (%file%1) do set val1=%%x
for /f "delims=" %%x in (%file%2) do set val2=%%x
IF "%val1%" == "%val2%" SET frmwretype=U
del /f /q %file%1
del /f /q %file%2
rem goto pescancont											rem ################ Uncomment ################
echo  Current Firmware mode is: %frmwretype%

:pescanfail
echo.
set /p frmwretype="Select Installation Firmware Type (BIOS or UEFI) ? (B)ios:(U)efi: "
:pescancont
goto frmwretype-%frmwretype%
cls
goto end


:frmwretype-U
SET hdefidriv=M
SET vdefidriv=P

:frmwretype-B
SET hdosdriv=N
SET osdrvlabel=OSDrive
SET hdosparttype=ntfs
SET vdosdriv=Q
SET vhdosdrvlabel=WinVHD
cls
color 17
echo                                                     - Created By Sandeep Ganjir
echo.
set /p choice1="Do you want to CLEAN and prepare your HardDrive? (Y)es:(N)o: "
goto preparehdd-%choice1%
cls
goto end

:errPreparehdd
cls
echo WARNING: Invalid Disk id or partition size provided for HardDrive

:preparehdd-Y
echo list disk > %file%
diskpart /s %file%
del /f /q %file%
echo.
echo WARNING: You will lose all data in the selected disk
set /p hddDisk=Enter the disk Id of HardDrive: 
set /p ossize=Enter Boot, OS partition size in MB (0 to fully occupy): 
SET osdrivsztx=size=%ossize%
if %ossize% == 0 set osdrivsztx= 
goto prephdfirm-%frmwretype%
:prephdfirm-B
(
echo select disk %hddDisk%
echo online disk noerr
echo attributes disk clear readonly noerr
echo clean
echo convert mbr
echo create partition primary %osdrivsztx%
echo select part 1
echo active
echo format fs=%hdosparttype% label=%osdrvlabel% quick
echo assign letter %hdosdriv%
)>%file%
goto prephdfirmcont
:prephdfirm-U
(
echo select disk %hddDisk%
echo online disk noerr
echo attributes disk clear readonly noerr
echo clean
echo convert gpt
echo create partition primary size=100 ID=c12a7328-f81f-11d2-ba4b-00a0c93ec93b
echo format fs=fat32 quick
echo assign letter %hdefidriv%
echo create partition primary %osdrivsztx%
echo format fs=%hdosparttype% label=%osdrvlabel% quick
echo assign letter %hdosdriv%
)>%file%
:prephdfirmcont
diskpart /s %file%
if %ERRORLEVEL% == 0 goto prephdnoer
goto errPreparehdd
:prephdnoer
del /f /q %file%
if %frmwretype% == B SET hdefidriv=%hdosdriv%
bootsect /nt60 %hdefidriv%: /force

:preparehdd-N
cls
color 57
echo.
set /p choice2="Do you want to create and attach vhd? (Y)es:(N)o: "
goto createvhd-%choice2%
cls
goto end

:errCreatevhd
cls
echo WARNING: Invalid Path or size provided for VHD

:createvhd-Y
echo list volume > %file%
diskpart /s %file%
del /f /q %file%
echo.
echo.

set /p vhdadd=Enter fully qualified VHD path: 
(
echo select vdisk file=%vhdadd% noerr
echo detach vdisk noerr
)>%file%
diskpart /s %file% > nul
del /f /q %file%
del /f /q %vhdadd% > nul rem 2>&1

set /p vhdsize=Enter VHD size in MB: 
goto prepvdfirm-%frmwretype%
:prepvdfirm-B
(
echo create vdisk file=%vhdadd% maximum=%vhdsize% type=expandable
echo attach vdisk
echo online disk noerr
echo attributes disk clear readonly
echo clean
echo convert mbr
echo create partition primary
echo select part 1
echo active
echo format fs=ntfs label=%vhdosdrvlabel% quick
echo assign letter %vdosdriv%
)>%file%
goto prepvdfirmcont
:prepvdfirm-U
(
echo create vdisk file=%vhdadd% maximum=%vhdsize% type=expandable
echo attach vdisk
echo online disk noerr
echo attributes disk clear readonly
echo clean
echo convert gpt
echo create partition primary size=100 ID=c12a7328-f81f-11d2-ba4b-00a0c93ec93b
echo format fs=fat32 quick
echo assign letter %vdefidriv%
echo create partition primary
echo format fs=ntfs label=%vhdosdrvlabel% quick
echo assign letter %vdosdriv%
)>%file%
:prepvdfirmcont
diskpart /s %file%
if %ERRORLEVEL% == 0 goto crtvdnoer
goto errCreatevhd
:crtvdnoer
del /f /q %file%

cls
echo list disk > %file%
diskpart /s %file%
del /f /q %file%
echo list volume > %file%
diskpart /s %file%
del /f /q %file%
echo.

:createvhd-N
cls
color 47
echo.
set /p choice3="Do you want to install Windows wim to the vhd? (Y)es:(N)o: "
goto installwim-%choice3%
cls
goto end

:installwim-Y
echo list volume > %file%
diskpart /s %file%
del /f /q %file%
:wrongInstallMedia
echo.
set /p mediaDrive="Enter the drive letter of the Windows Installation Media: "
if exist %mediaDrive%:\sources goto dsmaply-Y%choice2%
echo WARNING: Wrong drive letter provided.
goto wrongInstallMedia
:dsmaply-YN
echo.
set /p vdosdriv="Enter the drive letter of OS install partition of VHD: "
if exist %vdosdriv%:\ goto dsmaply-YY
echo WARNING: Wrong VHD partition letter
goto dsmaply-YN
:dsmaply-YY
if exist %mediaDrive%:\sources\install.wim goto aplyWIM
goto aplyESD
:aplyWIM
rem Dism /Get-WimInfo /WimFile:%mediaDrive%:\sources\install.wim /index:1
Dism /Get-WimInfo /WimFile:%mediaDrive%:\sources\install.wim
set /p verIndex="Enter the Index of the OS version you want to Install: "
Dism /apply-image /imagefile:%mediaDrive%:\sources\install.wim /index:%verIndex% /ApplyDir:%vdosdriv%:\
goto installwim-N
:aplyESD
Dism /Get-WimInfo /WimFile:%mediaDrive%:\sources\install.esd
set /p verIndex="Enter the Index of the OS version you want to Install: "
Dism /apply-image /imagefile:%mediaDrive%:\sources\install.esd /index:%verIndex% /ApplyDir:%vdosdriv%:\

:installwim-N
cls
color 27
echo.
set /p choice4="Do you want to write bootsector to your drives? (Y)es:(N)o: "
goto bootsec-%choice4%
cls
goto end

:bootsec-Y
goto instwim-Y%choice3%
:instwim-YN
echo list volume > %file%
diskpart /s %file%
del /f /q %file%
:wrongInstallMedia2
echo.
set /p mediaDrive="Enter the drive letter of the Windows Installation Media: "
if exist %mediaDrive%:\sources goto instwim-YY
echo WARNING: Wrong drive letter provided.
goto wrongInstallMedia2
:instwim-YY
%mediaDrive%:
cd \boot
if %choice1% == Y goto hddset1
echo.
set /p hdefidriv="Enter the drive letter of boot partition of HardDrive: "
if exist %hdefidriv%:\ goto hddset1
echo WARNING: Wrong HardDisk partition letter
goto instwim-YY
:hddset1
if %choice2% == Y goto vdset2
if %choice3% == Y goto vdset1
echo.
set /p vdosdriv="Enter the drive letter of windows install partition of VHD: "
if exist %vdosdriv%:\ goto vdset1
echo WARNING: Wrong VHD partition letter
goto hddset1
:vdset1
if %frmwretype% == B goto vdset2
echo.
set /p vdefidriv="Enter the drive letter of windows install partition of VHD: "
if exist %vdefidriv%:\ goto vdset2
echo WARNING: Wrong VHD partition letter
goto vdset1
:vdset2
if %frmwretype% == B SET vdefidriv=%vdosdriv%
bootsect /nt60 %vdefidriv%: /force
%vdosdriv%:
cd \Windows\system32
BCDBOOT %vdosdriv%:\Windows /s %hdefidriv%: /f ALL
BCDBOOT %vdosdriv%:\Windows /s %vdefidriv%: /f ALL

:bootsec-N
:end
cls
color ED
echo list volume > %file%
diskpart /s %file%
del /f /q %file%
echo.
pause
IF %envType% == GEN goto wpeshut-N
echo.
set /p choice5="Do you want to turnoff your computer? (Y)es:(N)o: "
IF %choice5% == Y wpeutil shutdown
:wpeshut-N
%mediaDrive%:
cd \sources
cls
color