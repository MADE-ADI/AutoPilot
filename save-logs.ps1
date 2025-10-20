# Script untuk save logs ke file

$adb = "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe"
$timestamp = Get-Date -Format "yyyy-MM-dd_HH-mm-ss"
$logFile = "autopilot-logs_$timestamp.txt"

Write-Host "==================================" -ForegroundColor Cyan
Write-Host "   AutoPilot Log Saver" -ForegroundColor Cyan
Write-Host "==================================" -ForegroundColor Cyan
Write-Host ""

Write-Host "Collecting logs..." -ForegroundColor Yellow
Write-Host "Saving to: $logFile" -ForegroundColor Green
Write-Host ""

# Ambil semua logs AutoPilot dan save ke file
& $adb logcat -d -v time | Select-String -Pattern "AutoPilot" | Out-File -FilePath $logFile -Encoding UTF8

$lineCount = (Get-Content $logFile | Measure-Object -Line).Lines

Write-Host "Done!" -ForegroundColor Green
Write-Host "Total log entries: $lineCount" -ForegroundColor Cyan
Write-Host "File saved to: $PWD\$logFile" -ForegroundColor Yellow
Write-Host ""
Write-Host "==================================" -ForegroundColor Cyan
