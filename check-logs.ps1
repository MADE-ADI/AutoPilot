# Script untuk cek logs AutoPilot dari HP yang terhubung via kabel

$adb = "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe"

Write-Host "==================================" -ForegroundColor Cyan
Write-Host "   AutoPilot Log Checker" -ForegroundColor Cyan
Write-Host "==================================" -ForegroundColor Cyan
Write-Host ""

# Cek device terhubung
Write-Host "Checking connected devices..." -ForegroundColor Yellow
& $adb devices
Write-Host ""

Write-Host "Starting live log monitoring..." -ForegroundColor Green
Write-Host "Press Ctrl+C to stop" -ForegroundColor Yellow
Write-Host ""
Write-Host "==================================" -ForegroundColor Cyan
Write-Host ""

# Filter logs hanya untuk AutoPilot
& $adb logcat -v time | Select-String -Pattern "AutoPilot"
