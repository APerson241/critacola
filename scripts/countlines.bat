@echo off
pushd .
cd C:\Users\John\Documents\GitHub\critacola\src
echo There are:
grep '' -R . | wc -l
echo lines of code. Woohoo.
popd
echo on