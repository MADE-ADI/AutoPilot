# ✅ AUTO PILOT - DEPLOYMENT CHECKLIST

## Pre-Build Checklist

- [ ] Android SDK latest version installed
- [ ] Gradle synced properly
- [ ] No gradle errors
- [ ] compileSdk set to 36
- [ ] minSdk set to 33 (Android 13+)
- [ ] Kotlin version compatible

## Build Checklist

```powershell
# Step 1: Clean
.\gradlew clean

# Step 2: Build
.\gradlew build

# Expected: BUILD SUCCESSFUL in ~2-5 min
```

- [ ] Build completes without errors
- [ ] Build completes without warnings (optional)
- [ ] APK generated in app/build/outputs/apk/

## Installation Checklist

```powershell
# Prerequisites
# - Device connected via USB (or emulator running)
# - USB debugging enabled (if physical device)

# Install
.\gradlew installDebug
```

- [ ] Device detected by adb
- [ ] APK transfers to device
- [ ] APK installs successfully
- [ ] App icon appears on home screen
- [ ] App opens without crashes

## First Launch Checklist

- [ ] App starts successfully
- [ ] UI renders correctly
  - [ ] Header "Auto Pilot" visible
  - [ ] Service Status Card visible (merah/red)
  - [ ] "Aktifkan Service" button visible
- [ ] No crashes or errors
- [ ] Layout responsive (text readable)

## Service Activation Checklist

- [ ] Click "Aktifkan Service" button
- [ ] Accessibility Settings opens
- [ ] AutoscrollService listed in apps
- [ ] Toggle switch present
- [ ] Toggle can be turned ON
- [ ] Permission dialogs appear (if any)
- [ ] Permission granted successfully
- [ ] App returns to home screen

## Post-Activation Checklist

- [ ] Return to Auto Pilot app
- [ ] Service Status Card now HIJAU/green (✓ Aktif)
- [ ] Scroll Delay slider visible
- [ ] "Mulai Autoscroll" button visible
- [ ] Features info card displayed
- [ ] All UI elements interactive

## Slider & Controls Checklist

- [ ] Slider moves smoothly
- [ ] Delay value updates (500-5000ms range)
- [ ] Value displays correctly
- [ ] Slider boundaries work (min/max)
- [ ] Text descriptions visible

## Start Autoscroll Checklist

- [ ] Click "Mulai Autoscroll" button
- [ ] Button text changes to "Hentikan Autoscroll"
- [ ] Button color changes to RED
- [ ] "Autoscroll Berjalan..." text appears
- [ ] No immediate crashes

## Testing with Social Media Apps

### Instagram
- [ ] Install/open Instagram
- [ ] Autoscroll performs gesture
- [ ] Feed scrolls automatically
- [ ] Multiple scrolls happen
- [ ] Scroll appears natural (not robotic)

### TikTok
- [ ] Install/open TikTok
- [ ] Videos scroll automatically
- [ ] Scrolling works smoothly
- [ ] No app crashes
- [ ] Gesture timing feels natural

### Twitter/X
- [ ] Install/open Twitter
- [ ] Timeline scrolls automatically
- [ ] Works without issues
- [ ] Timing appropriate

### Facebook
- [ ] Install/open Facebook
- [ ] Feed scrolls automatically
- [ ] Works without issues

### YouTube (if applicable)
- [ ] Install/open YouTube
- [ ] Recommendation scrolls
- [ ] Works appropriately

## Stop Autoscroll Checklist

- [ ] Click "Hentikan Autoscroll" button
- [ ] Button text changes to "Mulai Autoscroll"
- [ ] Button color changes to GREEN
- [ ] "Autoscroll Berjalan..." text disappears
- [ ] Scrolling stops immediately
- [ ] App remains responsive

## Stability Testing

- [ ] Run for 5+ minutes continuously
- [ ] No crashes or force closes
- [ ] No memory leaks (check RAM in Settings)
- [ ] No freezes or hangs
- [ ] App responsive to user input

### Stress Testing
- [ ] Start/Stop multiple times
- [ ] Change delay while running
- [ ] Stop and start quickly
- [ ] Switch apps and back
- [ ] Lock and unlock screen

## Edge Cases

- [ ] Delay set to minimum (500ms)
  - [ ] Works without crashes
  - [ ] Still appears natural
  
- [ ] Delay set to maximum (5000ms)
  - [ ] Long waits between scrolls
  - [ ] Still functional
  
- [ ] Lock device while scrolling
  - [ ] Service continues
  - [ ] Unlock works normally
  
- [ ] Switch apps while scrolling
  - [ ] Service continues
  - [ ] Can return to Auto Pilot
  
- [ ] Close app while scrolling
  - [ ] Gesture might complete
  - [ ] Service remains active

## Performance Checklist

- [ ] Battery drain minimal (~1-2% per hour)
- [ ] Memory usage stable (~30-50MB)
- [ ] CPU usage low when scrolling
- [ ] Smooth 60fps UI
- [ ] No stuttering

## UI/UX Checklist

- [ ] Text sizes readable
- [ ] Colors visible and clear
  - [ ] Green = Aktif
  - [ ] Red = Tidak aktif
  - [ ] Red = Hentikan
  - [ ] Green = Mulai
  
- [ ] Buttons have good touch targets (48dp minimum)
- [ ] No overlapping elements
- [ ] Responsive to all screen sizes
- [ ] Landscape and portrait modes

## Documentation Checklist

- [ ] README.md present
- [ ] SETUP_GUIDE.md present
- [ ] PANDUAN_ID.md present
- [ ] ARCHITECTURE.md present
- [ ] QUICK_START.txt present
- [ ] FILE_STRUCTURE.md present

## Code Quality Checklist

- [ ] No compiler warnings
- [ ] No logcat errors
- [ ] No resource errors
- [ ] Proper error handling
- [ ] Null safety (Kotlin)
- [ ] No deprecated APIs

## Final Release Checklist

- [ ] All tests passed
- [ ] Documentation complete
- [ ] Version number set correctly
- [ ] versionCode incremented (if releasing)
- [ ] versionName updated (if releasing)
- [ ] ProGuard rules configured (release build)
- [ ] Signing certificate ready (if signing)

## Post-Release Checklist

- [ ] App distributed to users
- [ ] Users can install successfully
- [ ] Users report normal operation
- [ ] No critical bugs reported
- [ ] Performance as expected

---

## Quick Status Check Commands

```powershell
# Check if device connected
adb devices

# Check if app installed
adb shell pm list packages | findstr autopilot

# Open app
adb shell am start -n com.example.autopilot/.MainActivity

# View logs
adb logcat | findstr "AutoscrollService"

# Check app info
adb shell dumpsys package com.example.autopilot

# Uninstall (if needed)
adb uninstall com.example.autopilot
```

---

## Emergency Recovery

If something goes wrong:

### App crashes on launch
```powershell
.\gradlew clean
.\gradlew build
.\gradlew installDebug
```

### Service not appearing
```powershell
adb shell am start -n com.example.autopilot/.MainActivity
# Restart device
adb reboot
```

### Need to uninstall and reinstall
```powershell
adb uninstall com.example.autopilot
.\gradlew installDebug
```

---

**Status**: ✅ Ready for Deployment
**Last Check**: Oktober 2025
