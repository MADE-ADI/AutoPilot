# 🔍 AUTO PILOT - DEBUGGING GUIDE (BAHASA INDONESIA)

## Fitur Debugging yang Ditambahkan

Aplikasi Auto Pilot sekarang memiliki fitur debugging lengkap untuk membantu Anda memastikan autoscroll bekerja dengan benar.

---

## ✨ Fitur Baru

### 1. **Debug Panel di UI**
- Tombol "Tampilkan Debug" di aplikasi
- Menampilkan informasi real-time
- Daftar aplikasi yang diizinkan
- Status autoscroll
- Perintah logcat

### 2. **App Filtering (Pembatasan Aplikasi)**
Autoscroll hanya bekerja di aplikasi tertentu:
```
✓ Instagram (com.instagram.android)
✓ TikTok (com.zhiliaoapp.musically)
✓ Facebook (com.facebook.katana)
✓ Twitter/X (com.twitter.android)
✓ YouTube (com.google.android.youtube)
✓ Reddit (com.reddit.frontpage)
✓ Telegram (org.telegram.messenger)
✓ WhatsApp (com.whatsapp)
✓ Snapchat (com.snapchat.android)
✓ LinkedIn (com.linkedin.android)
✓ Threads (com.instagram.threads)
✓ Bluesky (xyz.blueskyweb.app)
```

### 3. **Comprehensive Logging**
Semua aktivitas dicatat di logcat dengan tag `AutoPilot`

---

## 🔧 Cara Menggunakan Debug Panel

### Step 1: Buka Aplikasi Auto Pilot
1. Aplikasi sudah aktif dan service sudah diaktifkan
2. Klik tombol "Tampilkan Debug" (abu-abu)

### Step 2: Lihat Debug Information
Panel debugging akan menampilkan:
- **🔍 DEBUG PANEL** - Judul panel
- **Aplikasi yang Diizinkan** - Daftar app yang support
- **Status Autoscroll** - Sedang berjalan atau berhenti
- **Delay** - Nilai delay saat ini
- **Perintah Logcat** - Untuk debugging lebih detail

### Step 3: Troubleshooting
Jika ada masalah, ikuti saran di debug panel

---

## 📱 Cara Menggunakan Logcat

### Method 1: Via Android Studio (MUDAH)
```
1. Buka Android Studio
2. Menu: View → Tool Windows → Logcat
3. Di filter box, ketik: AutoPilot
4. Jalankan app
5. Lihat messages secara real-time
```

### Method 2: Via Terminal/PowerShell
```powershell
# Real-time logs
adb logcat | findstr "AutoPilot"

# Simpan ke file
adb logcat | findstr "AutoPilot" > debug_logs.txt

# Clear logcat
adb logcat -c
```

---

## 📊 Apa Yang Akan Anda Lihat di Logcat

### Service Started
```
D/AutoPilot: AutoscrollService created
I/AutoPilot: Autoscroll started - Package: Instagram, Delay: 2000 ms
```

### Scroll Performed
```
D/AutoPilot: Performing scroll - Direction: DOWN, Speed: 250px
D/AutoPilot: Gesture completed successfully
D/AutoPilot: Next scroll scheduled in 2100ms
```

### App Changed
```
D/AutoPilot: Window changed: TikTok
W/AutoPilot: App changed to: com.whatsapp, stopping scroll
```

### Error
```
E/AutoPilot: Current package not allowed: com.chrome
E/AutoPilot: Autoscroll not ready
```

---

## ✅ Checklist Debugging

### Aplikasi tidak aktif?
- [ ] Cek logcat untuk error
- [ ] Pastikan service sudah diaktifkan
- [ ] Restart app dan device
- [ ] Cek di Debug Panel

### Autoscroll tidak jalan di aplikasi tertentu?
- [ ] Cek nama package aplikasi
- [ ] Buka Debug Panel → lihat daftar aplikasi
- [ ] Jika ada, berarti sudah support
- [ ] Jika tidak ada, aplikasi tidak didukung

### Bagaimana menambah aplikasi baru?

Edit file `DebugUtils.kt`, bagian `ALLOWED_PACKAGES`:

```kotlin
private val ALLOWED_PACKAGES = setOf(
    // ... existing packages ...
    "com.contoh.app",  // Tambah di sini
    "com.aplikasi.baru"
)
```

Lalu di `getPackageDisplayName()`, tambahkan:

```kotlin
"com.contoh.app" -> "Nama Aplikasi"
"com.aplikasi.baru" -> "Aplikasi Baru"
```

---

## 🐛 Diagnostic Commands

### Cek Service Status
```powershell
adb shell settings get secure enabled_accessibility_services | findstr autopilot
```

### Cek Package Saat Ini
```powershell
adb shell dumpsys window windows | grep "mCurrentFocus"
```

### Lihat Semua Logs
```powershell
adb logcat
```

### Lihat Hanya Error
```powershell
adb logcat | findstr "ERROR|AutoPilot"
```

---

## 📝 Debug Information Explained

### Gesture Capability Check
```
Service enabled: true/false
  → Apakah service sudah aktif
  
Service info exists: true/false
  → Apakah service terdaftar dengan benar
```

### Window Content Check
```
Root node exists: true/false
  → Apakah ada window terbuka
  
Package: com.instagram.android
  → Package aplikasi yang sedang aktif
```

### Diagnostics Result
```
✓ Package Allowed: Aplikasi dalam daftar whitelist
✓ Gesture Capability: Service bisa melakukan gesture
✓ Window Content: Ada window untuk di-scroll
✓ Ready to Scroll: Semua kondisi OK
```

---

## 🎯 Scenario Troubleshooting

### Scenario 1: "Autoscroll tidak berjalan di Instagram"

**Debugging Steps:**
```
1. Buka Debug Panel
2. Cek: Apakah Instagram ada di daftar?
   → YA: Lanjut ke step 3
   → TIDAK: Aplikasi tidak didukung

3. Buka logcat
4. Cari: "Instagram" atau "com.instagram.android"
5. Lihat error message
6. Jika ada error, lihat penjelasan di bawah
```

### Scenario 2: "Error: Current package not allowed"

**Solusi:**
```
Ini berarti aplikasi tidak ada di whitelist.

1. Cari nama package aplikasi:
   adb shell dumpsys window windows | grep mCurrentFocus

2. Tambahkan ke ALLOWED_PACKAGES di DebugUtils.kt

3. Rebuild dan install ulang

4. Coba lagi
```

### Scenario 3: "Gesture tidak performed"

**Debugging:**
```
1. Cek logcat untuk "Gesture"
2. Jika ada error, catat error-nya
3. Buka Debug Panel
4. Cek: Gesture Capability
5. Jika false, ada masalah dengan service
6. Nonaktifkan dan aktifkan service lagi
```

---

## 🔐 Security Note

Debug panel hanya menampilkan informasi publik:
- ✅ Nama aplikasi yang diizinkan
- ✅ Status autoscroll
- ✅ Nilai delay
- ❌ Tidak menampilkan data sensitif

---

## 📞 Quick Debug Commands

| Perintah | Fungsi |
|----------|--------|
| `adb logcat \| findstr "AutoPilot"` | Lihat logs app |
| `adb logcat \| findstr "ERROR"` | Lihat error saja |
| `adb logcat -c` | Clear logcat |
| `adb shell pm grant com.example.autopilot android.permission.BIND_ACCESSIBILITY_SERVICE` | Grant manual permission |
| `adb shell dumpsys accessibility \| findstr autopilot` | Check service status |

---

## 🎓 Tips Debugging Profesional

### 1. Save Logs for Analysis
```powershell
# Jalankan ini sebelum test
adb logcat > logs_session.txt &

# Lakukan test
# Buka Instagram, jalankan autoscroll, dll

# Jika ada error, buka logs_session.txt dan analisis
```

### 2. Filter Multiple Keywords
```powershell
adb logcat | findstr "AutoPilot|ERROR|FAIL"
```

### 3. Real-time Monitoring
```powershell
# Terminal 1: Monitor logs
adb logcat | findstr "AutoPilot"

# Terminal 2: Interact dengan app
adb shell input tap 500 500
```

### 4. Capture Crash Logs
```powershell
# Jika app crash
adb shell dumpsys dropbox
```

---

## ✨ Contoh Debug Output

### Success Case
```
D/AutoPilot: ===== APP INFO =====
D/AutoPilot: Package: com.instagram.android
D/AutoPilot: Display: Instagram
D/AutoPilot: Allowed: true
D/AutoPilot: ====================
I/AutoPilot: Autoscroll started - Package: Instagram, Delay: 2000 ms
D/AutoPilot: Performing scroll - Direction: DOWN, Speed: 256px
D/AutoPilot: Gesture completed successfully
D/AutoPilot: Next scroll scheduled in 1950ms
```

### Failure Case
```
W/AutoPilot: Package name is null
E/AutoPilot: Current package not allowed: com.whatsapp
E/AutoPilot: Autoscroll not ready: {
  "isPackageAllowed": false,
  "hasGestureCapability": true,
  "hasWindowContent": true
}
```

---

## 🚀 Advanced Debugging

### Custom Logging
Jika ingin menambah log custom di code:

```kotlin
DebugLogger.d("Custom debug message")
DebugLogger.i("Info message")
DebugLogger.w("Warning message")
DebugLogger.e("Error message", exception)
```

### Add New Allowed App
Dalam `AllowedApps.kt`:

```kotlin
fun addPackage(packageName: String) {
    (ALLOWED_PACKAGES as? MutableSet)?.add(packageName)
    DebugLogger.i("Added package: $packageName")
}
```

---

## 📈 Performance Debugging

### Monitor Memory
```powershell
adb shell dumpsys meminfo com.example.autopilot
```

### Monitor CPU
```powershell
adb shell top -p $(adb shell pgrep -f com.example.autopilot)
```

### Monitor Battery
```powershell
adb shell dumpsys battery
```

---

## 🎊 Conclusion

Dengan debug panel dan logging, Anda bisa:
- ✅ Memastikan app bekerja dengan benar
- ✅ Mengatasi masalah dengan cepat
- ✅ Menambah aplikasi baru
- ✅ Monitor performa
- ✅ Analisis behavior

**Happy debugging! 🔍**

---

**Created: Oktober 2025**
**Status: Debugging Guide v1.0**
