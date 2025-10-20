# 🔧 AUTO PILOT - COMMAND REFERENCE & CHEAT SHEET

## 📋 Quick Command Reference

### Build & Installation

```powershell
# 1. Build the project
.\gradlew build

# 2. Install to device
.\gradlew installDebug

# 3. Combined (build + install)
.\gradlew clean build && .\gradlew installDebug
```

---

## 🚀 Development Commands

```powershell
# Clean previous builds
.\gradlew clean

# Build only
.\gradlew build

# Build with specific variant
.\gradlew assembleDebug

# Install release build
.\gradlew installRelease

# Run tests
.\gradlew test

# Check for linting issues
.\gradlew lint
```

---

## 📱 Device Management (ADB)

```powershell
# List connected devices
adb devices

# Check device status
adb shell pm list packages | findstr autopilot

# Install APK
adb install app/build/outputs/apk/debug/app-debug.apk

# Uninstall app
adb uninstall com.example.autopilot

# Open app
adb shell am start -n com.example.autopilot/.MainActivity

# Force stop app
adb shell am force-stop com.example.autopilot

# Clear app data
adb shell pm clear com.example.autopilot

# View installed version
adb shell dumpsys package com.example.autopilot | findstr version
```

---

## 🔍 Logging Commands

```powershell
# View all logs
adb logcat

# Filter AutoscrollService logs
adb logcat | findstr "AutoscrollService"

# Filter app-specific logs
adb logcat | findstr "com.example.autopilot"

# Save logs to file
adb logcat > logs.txt

# Clear logcat
adb logcat -c

# Real-time filtered logs
adb logcat | findstr "AutoscrollService|AutoscrollServiceImpl"

# View crash logs
adb logcat | findstr "FATAL|CRASH|ERROR"
```

---

## 🔐 Permission Commands

```powershell
# Check granted permissions
adb shell pm dump com.example.autopilot | findstr permission

# Grant specific permission
adb shell pm grant com.example.autopilot android.permission.BIND_ACCESSIBILITY_SERVICE

# Check accessibility service enabled
adb shell settings get secure enabled_accessibility_services
```

---

## 📊 Device Info Commands

```powershell
# Check Android version
adb shell getprop ro.build.version.release

# Check API level
adb shell getprop ro.build.version.sdk

# Check device model
adb shell getprop ro.product.model

# Check available RAM
adb shell cat /proc/meminfo | findstr MemTotal

# Check current CPU usage
adb shell top -n 1 | findstr "autopilot"
```

---

## 🎯 Service Management

```powershell
# Start app with specific action
adb shell am start -a android.intent.action.MAIN -n com.example.autopilot/.MainActivity

# Start accessibility service
adb shell am start-service com.example.autopilot/.AutoscrollService

# Query service status
adb shell dumpsys accessibility | findstr autopilot

# Dump app info
adb shell dumpsys package com.example.autopilot
```

---

## 📦 Build Variants

```powershell
# Build debug variant
.\gradlew assembleDebug

# Build release variant
.\gradlew assembleRelease

# Build all variants
.\gradlew assemble

# Create release APK with signing
.\gradlew bundleRelease
```

---

## 🧹 Cleanup Commands

```powershell
# Clean build directory
.\gradlew clean

# Delete gradle cache
.\gradlew cleanBuildCache

# Delete all build artifacts
Remove-Item -Path app/build -Recurse -Force

# Delete gradle wrapper cache
.\gradlew --stop
```

---

## 📝 Gradle Diagnostic Commands

```powershell
# Show gradle version
.\gradlew --version

# Check gradle properties
.\gradlew properties

# Debug gradle build
.\gradlew build --debug

# Show task graph
.\gradlew tasks

# Show all available tasks
.\gradlew tasks --all
```

---

## 💾 Database & File Management

```powershell
# List app files on device
adb shell ls -la /data/data/com.example.autopilot

# Pull app cache
adb pull /data/data/com.example.autopilot/cache ./local_cache

# Pull app preferences
adb pull /data/data/com.example.autopilot/shared_prefs ./local_prefs

# View app crash logs
adb pull /data/anr ./crash_logs
```

---

## 🎮 Interaction Commands

```powershell
# Tap screen at coordinates (x, y)
adb shell input tap 500 500

# Swipe (from x1,y1 to x2,y2)
adb shell input swipe 500 1000 500 500 500

# Long press
adb shell input touchscreen longpress 500 500

# Type text
adb shell input text "Hello"

# Press key (home = 3, back = 4)
adb shell input keyevent 3

# Take screenshot
adb shell screencap -p /sdcard/screenshot.png
adb pull /sdcard/screenshot.png ./screenshot.png
```

---

## 🔄 Emulator Commands

```powershell
# List available emulators
emulator -list-avds

# Start emulator
emulator -avd emulator_name

# Start with optimizations
emulator -avd emulator_name -acceleration on -gpu on

# Start in background
emulator -avd emulator_name > nul 2>&1 &
```

---

## 📊 Performance Monitoring

```powershell
# Check memory usage
adb shell dumpsys meminfo com.example.autopilot

# Monitor real-time memory
adb shell dumpsys meminfo com.example.autopilot --local

# Check CPU usage
adb shell top -p $(adb shell pgrep -f com.example.autopilot)

# View battery usage
adb shell dumpsys batterystats --checkin | findstr autopilot
```

---

## 🐛 Debugging Commands

```powershell
# Forward port (for debugging)
adb forward tcp:5555 tcp:5555

# Reverse port
adb reverse tcp:8080 tcp:8080

# Connect to remote device
adb connect IP_ADDRESS:5555

# Disconnect
adb disconnect IP_ADDRESS:5555

# Restart ADB daemon
adb kill-server
adb start-server
```

---

## 📱 Emulator File Operations

```powershell
# Push file to emulator
adb push local_file /sdcard/remote_file

# Pull file from emulator
adb pull /sdcard/remote_file local_file

# Delete file on emulator
adb shell rm /sdcard/file_name

# List files
adb shell ls -la /sdcard/
```

---

## 🎯 Commonly Used Sequences

### Build, Install & Run
```powershell
.\gradlew clean build && .\gradlew installDebug && adb shell am start -n com.example.autopilot/.MainActivity
```

### Build & Check Logs
```powershell
.\gradlew installDebug && adb logcat | findstr "AutoscrollService"
```

### Clean Install & Test
```powershell
adb uninstall com.example.autopilot
.\gradlew clean build installDebug
adb shell am start -n com.example.autopilot/.MainActivity
```

### Check Everything
```powershell
adb devices
adb shell getprop ro.build.version.release
adb shell pm list packages | findstr autopilot
adb logcat | findstr "AutoscrollService"
```

---

## 💡 Pro Tips

### Batch Commands
```powershell
# Multiple commands in sequence
.\gradlew clean
.\gradlew build
.\gradlew installDebug
adb shell am start -n com.example.autopilot/.MainActivity
```

### Logging to File
```powershell
# Save logs while building
adb logcat > debug.log &
.\gradlew build
adb logcat -c
```

### Device Discovery
```powershell
# Find all devices/emulators
adb devices -l

# Show detailed device info
adb shell getprop
```

### Quick Test Cycle
```powershell
# This sequence is fastest for testing
.\gradlew installDebug -x lint
adb logcat -c
adb logcat | findstr "AutoscrollService"
```

---

## 🚨 Troubleshooting Commands

### Device Not Found
```powershell
adb kill-server
adb start-server
adb devices
```

### App Won't Install
```powershell
adb uninstall com.example.autopilot
.\gradlew clean
.\gradlew installDebug
```

### Service Not Showing
```powershell
adb shell cmd settings put secure enabled_accessibility_services com.example.autopilot/.AutoscrollService
adb shell am start -n com.example.autopilot/.MainActivity
```

### Check Accessibility Service Status
```powershell
adb shell settings get secure enabled_accessibility_services | findstr autopilot
```

---

## 📋 Command Categories

| Category | Commands |
|----------|----------|
| Build | build, clean, assemble |
| Install | installDebug, installRelease, uninstall |
| Run | start, shell am start |
| Log | logcat, dumpsys |
| Device | devices, shell |
| Debug | --debug, --stacktrace |
| Test | test, lint |

---

## ⚡ Quick Aliases (Optional)

Create batch files for common commands:

`build.bat`:
```batch
@echo off
cd /d %~dp0
.\gradlew build
```

`install.bat`:
```batch
@echo off
cd /d %~dp0
.\gradlew installDebug
```

`logs.bat`:
```batch
@echo off
adb logcat | findstr "AutoscrollService"
```

---

## 🔑 Key Environment Variables

```powershell
# Set ANDROID_HOME
$env:ANDROID_HOME = "C:\Users\YourUsername\AppData\Local\Android\Sdk"

# Add to PATH
$env:Path += ";$env:ANDROID_HOME\platform-tools"

# Verify
adb version
```

---

## 📚 Help Commands

```powershell
# Gradle help
.\gradlew help

# Task help
.\gradlew tasks

# Specific task help
.\gradlew help assembleDebug

# ADB help
adb help

# Emulator help
emulator -help
```

---

## 🎯 Remember

- ✅ Always `clean` before `build` if there are issues
- ✅ Uninstall app before major changes
- ✅ Check `adb devices` before running commands
- ✅ Use `logcat` for debugging
- ✅ Screen dimensions for emulator: 1080x1920

---

## 📞 Quick Reference

| Task | Command |
|------|---------|
| Build | `.\gradlew build` |
| Install | `.\gradlew installDebug` |
| Uninstall | `adb uninstall com.example.autopilot` |
| Run | `adb shell am start -n com.example.autopilot/.MainActivity` |
| Logs | `adb logcat \| findstr "AutoscrollService"` |
| Device List | `adb devices` |
| Clear Data | `adb shell pm clear com.example.autopilot` |
| Check Status | `adb shell dumpsys accessibility \| findstr autopilot` |

---

**Save this file for quick reference!**

🚀 **Happy building!**
