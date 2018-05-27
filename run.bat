title Minecraft GDK Run Script
cls
@ECHO OFF
ECHO ##########################################################################
ECHO #									 #
ECHO #		Minecraft GDK Run Script				 #
ECHO #									 #
ECHO ##########################################################################
ECHO.
if not exist ./builds/Minecraft-GDK-Alpha-1.jar (
	ECHO File not found, Starting a new build !
	ECHO.
	call build.bat && call run.bat
) else (
	java -jar ./builds/Minecraft-GDK-Alpha-1.jar
)

ECHO.
pause