# Script untuk cek aplikasi yang sedang berjalan (foreground app)

$adb = "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe"

Write-Host "==================================" -ForegroundColor Cyan
Write-Host "   Current Foreground App" -ForegroundColor Cyan
Write-Host "==================================" -ForegroundColor Cyan
Write-Host ""

# Ambil informasi aplikasi yang sedang aktif
$output = & $adb shell dumpsys window windows 2>$null | Select-String -Pattern "mCurrentFocus"

if ($output) {
    Write-Host "Current app:" -ForegroundColor Green
    Write-Host $output -ForegroundColor Yellow
    Write-Host ""
    
    # Extract package name
    if ($output -match "([a-z0-9.]+)/") {
        $packageName = $matches[1]
        Write-Host "Package name: " -NoNewline -ForegroundColor Green
        Write-Host $packageName -ForegroundColor Cyan
    }
} else {
    Write-Host "Could not detect current app" -ForegroundColor Red
}

Write-Host ""
Write-Host "==================================" -ForegroundColor Cyan
