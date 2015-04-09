::ASW L1
@echo off
color 0A

:ini
cls
echo 0 iniciar MongoDB por defecto
echo 1 iniciar MongoDB con configuración
echo 2 saír

set/p var=escoja una opcion:

if %var%==0 mongod
	
if %var%==1 goto bdExec
	
if %var%==2 goto ext

:bdExec
echo  ...
echo pulse cntrl + C para terminár la ejecución de la base de datos
echo  ...
echo  ..
echo  .
mongod -config mongo.config

pause
goto ini
pause
:ext
color 0C
echo      Sexy?Sex
echo     ?Sexy?Sexy
echo    y?Sexy?Sexy?
echo    ?Sexy?Sexy?S
echo    ?Sexy?Sexy?S
echo   ?Sexy?Sexy?Se
echo  ?Sexy?Sexy?Se
echo  ?Sexy?Sexy?Se
echo  ?Sexy?Sexy?Sexy?
echo ?Sexy?Sexy?Sexy?Sexy
echo ?Sexy?Sexy?Sexy?Sexy?Se
echo ?Sexy?Sexy?Sexy?Sexy?Sex
echo  ?Sexy?  ?Sexy?Sexy?Sex
echo    ?Sex    ?Sexy?Sexy?
echo    ?Sex     ?Sexy?Sexy
echo    ?Sex     ?Sexy?Sexy
echo     ?Sex    ?Sexy?Sexy
echo      ?Se    ?Sexy?Sex
echo       ?Se  ?Sexy?Sexy
echo        ?Sexy?Sexy?Sex
echo         ?Sexy?Sexy?sex
echo        ?Sexy?Sexy?Sexy?Se
echo        ?Sexy?Sexy?Sexy?Sexy?
echo        ?Sexy?Sexy?Sexy?Sexy?Sexy
echo        ?Sexy?Sexy?Sexy?Sexy?Sexy?S
echo         ?Sexy?Sexy    ?Sexy?Sexy?se
echo          ?Sexy?Se       ?Sexy?Sexy?
echo          ?Sexy?Se     ?Sexy?Sexy?
echo          ?Sexy?S    ?Sexy?Sexy
echo          ?Sexy?S ?Sexy?Sexy
echo         ?Sexy?Sexy?Sexy
echo         ?Sexy?Sexy?S
echo         ?Sexy?Sexy
echo        ?Sexy?Se
echo        ?Sexy?
echo       ?Sexy?
echo       ?Sexy?
echo       ?Sexy?
echo       ?Sexy
echo       ?Sexy
echo        ?Sex
echo        ?Sex
echo        ?Sex
echo       ?Sexy
echo       ?Sexy
echo        Sexy
echo         Sexy?
echo         SexY

echo adios chao:

pause