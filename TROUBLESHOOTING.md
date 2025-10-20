# 🆘 AUTO PILOT - TROUBLESHOOTING GUIDE

## 🚨 Common Issues & Solutions

### Issue #1: "Service tidak muncul di Accessibility Settings"

**Gejala:**
- Klik "Aktifkan Service" → Settings terbuka
- Tapi tidak bisa menemukan "Auto Pilot" atau "AutoscrollService"

**Solusi:**

#### Solusi 1 (Coba ini dulu):
```
1. Tutup Accessibility Settings
2. Tutup sepenuhnya aplikasi Auto Pilot
3. Buka ulang aplikasi Auto Pilot
4. Klik "Aktifkan Service" lagi
5. Cari dengan scroll ke bawah di list
```

#### Solusi 2:
```
1. Buka Settings → Apps & Notifications
2. Find "Auto Pilot" (atau "AutoPilot")
3. Tap "Uninstall"
4. Buka Terminal / PowerShell
5. Run: .\gradlew installDebug
6. Coba lagi "Aktifkan Service"
```

#### Solusi 3:
```
1. Restart device (power off → on)
2. Buka Auto Pilot
3. Klik "Aktifkan Service"
4. Sekarang harus muncul
```

---

### Issue #2: "Service aktif tapi scroll tidak bekerja"

**Gejala:**
- Service sudah aktif (kartu hijau)
- Tombol berubah merah saat klik "Mulai"
- Tapi tidak ada scrolling di app media sosial

**Solusi:**

#### Langkah 1: Verify Service Truly Active
```
1. Buka Settings → Accessibility
2. Cari "Auto Pilot" atau "AutoscrollService"
3. PASTIKAN TOGGLE BENAR-BENAR ON (biru)
4. Kembali ke app → refresh
```

#### Langkah 2: Test di App yang Tepat
```
1. Buka Instagram, TikTok, atau Facebook
2. Scroll manual dulu (pastikan app normal)
3. Buka Auto Pilot (jangan tutup app sosial)
4. Klik "Mulai Autoscroll"
5. Lihat di app sosial apakah scroll
```

#### Langkah 3: Check Logcat
```
1. Buka Android Studio
2. View → Tool Windows → Logcat
3. Filter: "AutoscrollService"
4. Check untuk error messages
5. Screenshot error jika ada
```

#### Langkah 4: Reset Service
```
1. Buka Settings → Accessibility
2. Turn OFF "Auto Pilot"
3. Buka Auto Pilot app
4. Check status (merah = expected)
5. Turn ON lagi di Accessibility
6. Coba "Mulai Autoscroll"
```

---

### Issue #3: "Aplikasi lag / freeze / terasa lambat"

**Gejala:**
- UI responsiveness turun
- Scroll lambat
- Buttons tidak respond cepat

**Solusi:**

#### Quick Fix:
```
1. Tutup app lain yang heavy (games, browser, dll)
2. Restart device
3. Buka ulang Auto Pilot
```

#### Settings Adjustment:
```
1. Di Auto Pilot, geser slider ke kanan (increase delay)
2. Ubah dari 2000ms → 3000ms atau lebih
3. Click "Mulai" lagi
4. Check apakah lebih smooth
```

#### Full Reset:
```
1. Buka Settings → Apps → Auto Pilot
2. Tap "Storage" → "Clear Cache"
3. Tutup app sepenuhnya
4. Buka ulang
```

---

### Issue #4: "Scroll tidak konsisten / random"

**Gejala:**
- Kadang scroll, kadang tidak
- Delay tidak sesuai
- Unpredictable behavior

**Catatan:** Ini NORMAL untuk human-like behavior!

**Jika benar-benar masalah:**
```
1. Pastikan phone RAM cukup (Settings → About → RAM)
2. Tutup background apps
3. Clear cache: Settings → Apps → Auto Pilot → Clear Cache
4. Restart device
```

---

### Issue #5: "App crash saat startup"

**Gejala:**
- Klik app → langsung crash
- Error message muncul

**Solusi:**

#### Langkah 1: Clear Data
```
1. Buka Settings → Apps → Auto Pilot
2. Tap "Storage" → "Clear Cache"
3. Tap "Storage" → "Clear Data"
4. Buka app lagi
```

#### Langkah 2: Reinstall
```
1. Buka PowerShell / Terminal
2. adb uninstall com.example.autopilot
3. .\gradlew clean
4. .\gradlew installDebug
5. Buka app
```

#### Langkah 3: Check Device Compatibility
```
- Pastikan Android 13+ (Settings → About → Android)
- Coba di emulator jika di physical device fail
- Check logcat untuk crash reason
```

---

### Issue #6: "Permission denied / cannot enable service"

**Gejala:**
- Toggle tidak bisa aktif
- Error message: "Cannot enable"
- Permission denied

**Solusi:**

#### Check Permissions:
```
1. Buka Settings → Apps → Auto Pilot → Permissions
2. Check untuk missing permissions
3. Grant all permissions yang diminta
```

#### Alternative:
```
1. Buka Settings → Accessibility
2. Scroll down, cari "Auto Pilot"
3. Tap manual untuk enable
4. Grant permissions yang diminta
```

---

### Issue #7: "Service keeps disabling automatically"

**Gejala:**
- Service aktif, tapi setelah beberapa saat jadi non-aktif
- Ketika buka app, sudah off

**Solusi:**

```
1. Buka Settings → Accessibility → Auto Pilot
2. Pastikan "Allow" semua permissions
3. Pastikan toggle truly ON (biru)
4. Check Settings → Battery → Battery Saver OFF
5. Add app to battery optimization whitelist:
   - Settings → Battery → Battery Optimization
   - Find "Auto Pilot"
   - Select "Don't optimize"
```

---

## 📋 Diagnostic Checklist

Sebelum minta bantuan, cek ini:

- [ ] Android version 13+? (Settings → About)
- [ ] Service truly enabled? (Settings → Accessibility)
- [ ] App fully closed sebelum test?
- [ ] Social media app yang tepat? (Instagram, TikTok, etc)
- [ ] Delay bukan terlalu kecil? (minimum 500ms)
- [ ] Coba di emulator juga (untuk physical device users)
- [ ] RAM cukup? (minimum 2GB free)
- [ ] Battery tidak dalam mode extreme saving?

---

## 🔍 How to Get Logcat Messages

Untuk debugging lebih detail:

### Method 1: Via Android Studio (Easiest)
```
1. Connect device via USB
2. Buka Android Studio
3. Menu: View → Tool Windows → Logcat
4. Di filter box, search: "AutoscrollService"
5. Run app and look for messages
```

### Method 2: Via Terminal/PowerShell
```powershell
# Real-time logs
adb logcat | findstr "AutoscrollService"

# Save to file
adb logcat | findstr "AutoscrollService" > logs.txt

# Check specific app
adb logcat | findstr "com.example.autopilot"
```

---

## 📝 When to Seek Help

Provide these information:

```
Device:
- Brand: _________
- Model: _________
- Android Version: _________
- RAM: _________

Issue:
- What happened: _________
- When: _________
- Steps to reproduce: _________

Logcat Output:
[paste relevant logs]
```

---

## 🚀 Advanced Troubleshooting

### Clear App Data Completely
```powershell
adb shell pm clear com.example.autopilot
```

### Uninstall & Reinstall Clean
```powershell
adb uninstall com.example.autopilot
./gradlew clean
./gradlew build
./gradlew installDebug
```

### Check Service Registration
```powershell
adb shell dumpsys accessibility | findstr "autopilot"
```

### Verify Permissions Granted
```powershell
adb shell pm dump com.example.autopilot | findstr permission
```

### Force Stop and Restart
```powershell
adb shell am force-stop com.example.autopilot
adb shell am start -n com.example.autopilot/.MainActivity
```

---

## 🎯 Prevention Tips

Agar masalah tidak terjadi:

1. **Update Regular**
   - Check for app updates
   - Update Android system

2. **Keep Resources Free**
   - Close unnecessary apps
   - Don't fill storage 100%
   - Keep RAM free (2GB+)

3. **Proper Shutdown**
   - Always use "Hentikan Autoscroll"
   - Don't force-close app
   - Respect permission dialogs

4. **Monitor Logcat**
   - Check logcat regularly
   - Report warnings early
   - Screenshot errors

5. **Test Incrementally**
   - Test after each change
   - Don't change multiple things at once
   - Document what works

---

## 🆘 When Nothing Works

Last resort troubleshooting:

```powershell
# 1. Restart device
adb reboot

# 2. Wait 2 minutes, then:
adb devices  # verify connection

# 3. Completely remove app
adb uninstall com.example.autopilot

# 4. Clear build cache
./gradlew clean
./gradlew build --refresh-dependencies

# 5. Install fresh
./gradlew installDebug

# 6. Open app fresh
adb shell am start -n com.example.autopilot/.MainActivity
```

---

## 📞 Getting Support

**For bugs/issues:**
1. Check this troubleshooting guide
2. Check logcat messages
3. Try solutions above
4. Document everything
5. Report with details

**Information to include:**
- Device model & Android version
- Exact error/issue description
- Steps to reproduce
- Logcat output
- Screenshots if helpful

---

**Last Updated: Oktober 2025**
**Status: Regular updates as needed**
