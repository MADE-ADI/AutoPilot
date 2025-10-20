# Script untuk clear logs lama dan mulai fresh monitoring

$adb = "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe"

Write-Host "==================================" -ForegroundColor Cyan
Write-Host "   AutoPilot Fresh Log Monitor" -ForegroundColor Cyan
Write-Host "==================================" -ForegroundColor Cyan
Write-Host ""

# Clear logs lama
Write-Host "Clearing old logs..." -ForegroundColor Yellow
& $adb logcat -c
Write-Host "Logs cleared!" -ForegroundColor Green
Write-Host ""

Write-Host "Starting fresh log monitoring..." -ForegroundColor Green
Write-Host "Press Ctrl+C to stop" -ForegroundColor Yellow
Write-Host ""
Write-Host "==================================" -ForegroundColor Cyan
Write-Host ""

# Monitor logs real-time hanya untuk AutoPilot
& $adb logcat -v time | Select-String -Pattern "AutoPilot"
