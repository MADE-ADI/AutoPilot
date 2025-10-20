# Panduan Cek Logs AutoPilot dari Komputer

Panduan lengkap untuk monitoring logs aplikasi AutoPilot yang terhubung ke komputer melalui kabel USB.

## 📋 Persyaratan

1. ✅ HP Android terhubung ke komputer via USB
2. ✅ USB Debugging diaktifkan di HP
3. ✅ Android Studio terinstall (untuk ADB tools)
4. ✅ HP sudah dikenali oleh komputer (authorized)

---

## 🚀 Quick Start - Cara Tercepat

### 1. Cek HP Terhubung

Jalankan di PowerShell (di folder AutoPilot):

```powershell
& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" devices
```

**Output yang diharapkan:**
```
List of devices attached
ABC123XYZ    device
```

Jika muncul `unauthorized`, cek HP dan accept USB debugging permission.

---

## 📊 Cara Monitor Logs

### **Metode 1: Live Monitoring (Real-time)**

Gunakan script yang sudah dibuat:

```powershell
.\check-logs.ps1
```

**Atau manual:**
```powershell
& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" logcat -v time | Select-String -Pattern "AutoPilot"
```

**Keterangan:**
- Logs akan muncul secara real-time
- Hanya menampilkan logs dengan tag "AutoPilot"
- Tekan `Ctrl+C` untuk berhenti

---

### **Metode 2: Fresh Monitoring (Clear old logs first)**

```powershell
.\check-logs-fresh.ps1
```

**Keterangan:**
- Menghapus semua logs lama
- Memulai monitoring dari 0
- Bagus untuk testing dari awal

---

### **Metode 3: Save Logs ke File**

```powershell
.\save-logs.ps1
```

**Keterangan:**
- Menyimpan semua logs AutoPilot ke file `.txt`
- File otomatis diberi timestamp
- Berguna untuk analisis atau share ke developer

---

### **Metode 4: Cek Aplikasi yang Sedang Aktif**

```powershell
.\check-current-app.ps1
```

**Keterangan:**
- Melihat aplikasi apa yang sedang terbuka di HP
- Membantu verify apakah berada di aplikasi yang diizinkan
- Menampilkan package name

---

## 🔍 Membaca Logs

### Format Log:
```
10-21 15:30:45.123 D/AutoPilot: Window changed: Instagram
10-21 15:30:47.456 I/AutoPilot: Autoscroll started - Package: Instagram, Delay: 2000 ms
10-21 15:30:47.789 D/AutoPilot: Performing scroll - Direction: DOWN, Speed: 245px
10-21 15:30:48.012 D/AutoPilot: Gesture completed successfully
```

### Level Logs:
- **D** (Debug) = Informasi detail untuk debugging
- **I** (Info) = Informasi umum
- **W** (Warning) = Peringatan
- **E** (Error) = Error/kesalahan

---

## 🎯 Workflow Testing yang Disarankan

### 1. **Persiapan**
```powershell
# Cek HP terhubung
& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" devices

# Install app terbaru
.\gradlew installDebug
```

### 2. **Clear Logs & Start Monitoring**
```powershell
.\check-logs-fresh.ps1
```

### 3. **Test di HP**
- Buka aplikasi AutoPilot
- Enable Accessibility Service
- Buka Instagram/TikTok/aplikasi lain yang didukung
- Klik tombol Start Autoscroll
- Lihat Debug Panel untuk status

### 4. **Cek Logs di Komputer**
Perhatikan logs yang muncul:
- ✅ "AutoscrollService created" → Service jalan
- ✅ "Window changed: Instagram" → Deteksi aplikasi berhasil
- ✅ "Autoscroll started" → Autoscroll mulai
- ✅ "Performing scroll - Direction: DOWN" → Gesture berjalan
- ✅ "Gesture completed successfully" → Gesture berhasil
- ⚠️ "Current package not allowed" → Aplikasi tidak di whitelist
- ❌ "Error performing scroll" → Ada error

### 5. **Save Logs (Opsional)**
```powershell
# Stop monitoring (Ctrl+C)
# Lalu save logs
.\save-logs.ps1
```

---

## 🔧 Troubleshooting

### Problem: ADB not found
**Solusi:**
```powershell
# Cek lokasi ADB
Get-ChildItem -Path "$env:LOCALAPPDATA\Android\Sdk\platform-tools" -Filter "adb.exe"
```

### Problem: Device not found
**Solusi:**
1. Pastikan kabel USB bagus (gunakan kabel data, bukan charging only)
2. Di HP: Settings → Developer Options → USB Debugging → ON
3. Cabut-pasang kabel
4. Accept "Allow USB debugging" di HP

### Problem: Device unauthorized
**Solusi:**
1. Cek notifikasi di HP
2. Klik "Always allow from this computer"
3. Klik "OK"

### Problem: No logs appearing
**Solusi:**
1. Pastikan aplikasi AutoPilot sudah running
2. Pastikan Accessibility Service enabled
3. Coba clear logs dulu: `.\check-logs-fresh.ps1`
4. Cek apakah DebugLogger.ENABLE_LOGGING = true di DebugUtils.kt

---

## 📱 Commands ADB Berguna Lainnya

### Install APK
```powershell
& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" install -r app\build\outputs\apk\debug\app-debug.apk
```

### Uninstall App
```powershell
& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" uninstall com.example.autopilot
```

### Screenshot
```powershell
& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" shell screencap -p /sdcard/screen.png
& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" pull /sdcard/screen.png
```

### Screen Record
```powershell
& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" shell screenrecord /sdcard/demo.mp4
# Tekan Ctrl+C untuk stop
& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" pull /sdcard/demo.mp4
```

### Restart ADB Server (jika ada masalah)
```powershell
& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" kill-server
& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" start-server
```

---

## 📝 Filter Logs Spesifik

### Hanya Debug logs
```powershell
& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" logcat -v time AutoPilot:D *:S
```

### Hanya Error logs
```powershell
& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" logcat -v time AutoPilot:E *:S
```

### Cari kata spesifik (contoh: "scroll")
```powershell
& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" logcat -v time | Select-String -Pattern "AutoPilot.*scroll"
```

### Last 100 logs saja
```powershell
& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" logcat -v time -t 100 | Select-String -Pattern "AutoPilot"
```

---

## 💡 Tips

1. **Gunakan 2 Terminal:**
   - Terminal 1: Monitor logs (`.\check-logs-fresh.ps1`)
   - Terminal 2: Build & install app (`.\gradlew installDebug`)

2. **Warna di PowerShell:**
   Script sudah menggunakan warna untuk memudahkan membaca

3. **Timestamp:**
   Logs otomatis ada timestamp, memudahkan tracking waktu kejadian

4. **Save Important Logs:**
   Jika menemukan bug, save logs dengan `.\save-logs.ps1` dan share file-nya

5. **Debug Panel di App:**
   Gunakan Debug Panel di aplikasi bersamaan dengan logcat untuk monitoring lengkap

---

## 🆘 Bantuan

Jika ada masalah:
1. Cek DEBUG_GUIDE.md untuk troubleshooting aplikasi
2. Cek logs dengan metode di atas
3. Save logs dan share untuk analisis

---

**Last Updated:** October 2025
