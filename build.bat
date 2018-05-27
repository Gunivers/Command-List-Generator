title Minecraft GDK Build Script
cls
@ECHO OFF
ECHO ##########################################################################
ECHO 		Minecraft GDK Build Script
ECHO ##########################################################################

gradlew --refresh-dependencies && gradlew build && gradlew clean