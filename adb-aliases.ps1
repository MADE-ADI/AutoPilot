# AutoPilot ADB Shortcuts
# Simpan script ini dan jalankan: . .\adb-aliases.ps1

$ADB_PATH = "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe"

# Alias untuk ADB
function adb { & $ADB_PATH @args }

# Quick commands
function Check-Device { 
    Write-Host "Connected devices:" -ForegroundColor Cyan
    adb devices 
}

function Check-Logs { 
    Write-Host "Monitoring AutoPilot logs (Ctrl+C to stop)..." -ForegroundColor Green
    adb logcat -v time | Select-String -Pattern "AutoPilot"
}

function Clear-Logs { 
    adb logcat -c
    Write-Host "Logs cleared!" -ForegroundColor Green
}

function Check-CurrentApp { 
    $output = adb shell dumpsys window windows 2>$null | Select-String -Pattern "mCurrentFocus"
    Write-Host "Current app: " -ForegroundColor Cyan
    Write-Host $output -ForegroundColor Yellow
}

function Install-App { 
    Write-Host "Building and installing app..." -ForegroundColor Yellow
    .\gradlew installDebug
}

# Export functions
Export-ModuleMember -Function adb, Check-Device, Check-Logs, Clear-Logs, Check-CurrentApp, Install-App

Write-Host "✅ ADB aliases loaded!" -ForegroundColor Green
Write-Host ""
Write-Host "Available commands:" -ForegroundColor Cyan
Write-Host "  adb              - Run ADB commands"
Write-Host "  Check-Device     - Check connected device"
Write-Host "  Check-Logs       - Monitor logs real-time"
Write-Host "  Clear-Logs       - Clear all logs"
Write-Host "  Check-CurrentApp - See current foreground app"
Write-Host "  Install-App      - Build and install APK"
Write-Host ""
