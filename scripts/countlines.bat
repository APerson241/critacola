@echo off
cd ..\src
echo There are:
grep '' -R . | wc -l
echo lines of code. Woohoo.
cd ..\scripts
echo on