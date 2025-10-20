# 📱 AutoPilot - Log Monitoring Tools

Collection of PowerShell scripts untuk monitoring logs aplikasi AutoPilot dari komputer.

## 🚀 Quick Start

### 1. Pastikan HP Terhubung
```powershell
& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" devices
```

### 2. Jalankan Salah Satu Script:

#### ✨ **Recommended untuk Testing**
```powershell
.\check-logs-fresh.ps1
```
Membersihkan logs lama dan mulai monitoring fresh.

#### 📊 **Monitor Logs Real-time**
```powershell
.\check-logs.ps1
```
Monitor semua logs AutoPilot secara real-time.

#### 💾 **Save Logs ke File**
```powershell
.\save-logs.ps1
```
Simpan logs ke file dengan timestamp.

#### 📱 **Cek Aplikasi yang Aktif**
```powershell
.\check-current-app.ps1
```
Lihat aplikasi apa yang sedang terbuka di HP.

---

## 📖 Dokumentasi Lengkap

Baca **[LOGS_GUIDE.md](LOGS_GUIDE.md)** untuk:
- Panduan lengkap monitoring logs
- Troubleshooting
- Commands ADB berguna
- Tips & tricks
- Workflow testing yang disarankan

---

## 🛠️ Scripts Available

| Script | Fungsi |
|--------|--------|
| `check-logs.ps1` | Monitor logs real-time |
| `check-logs-fresh.ps1` | Clear old logs & start fresh monitoring |
| `save-logs.ps1` | Save logs to timestamped file |
| `check-current-app.ps1` | Check foreground app on device |
| `adb-aliases.ps1` | Load ADB shortcuts (optional) |

---

## ⚡ Quick Commands

### Cek Device
```powershell
& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" devices
```

### Install App
```powershell
.\gradlew installDebug
```

### Monitor + Install (2 terminal)
**Terminal 1:**
```powershell
.\check-logs-fresh.ps1
```

**Terminal 2:**
```powershell
.\gradlew installDebug
```

---

## 🎯 Typical Workflow

1. **Build & Install**
   ```powershell
   .\gradlew clean build installDebug
   ```

2. **Start Log Monitoring**
   ```powershell
   .\check-logs-fresh.ps1
   ```

3. **Test di HP**
   - Buka app AutoPilot
   - Enable Accessibility Service
   - Buka Instagram/TikTok
   - Start Autoscroll
   - Lihat Debug Panel

4. **Watch Logs di Komputer**
   - Perhatikan logs yang muncul real-time
   - Cari error/warning jika ada

5. **Save Logs (jika perlu)**
   ```powershell
   # Ctrl+C untuk stop monitoring
   .\save-logs.ps1
   ```

---

## 💡 Tips

- **2 Terminal**: Satu untuk logs, satu untuk build
- **Fresh Start**: Gunakan `check-logs-fresh.ps1` setiap kali testing
- **Save Important Logs**: Jika ada bug, save dengan `save-logs.ps1`
- **Debug Panel**: Gunakan Debug Panel di app + logcat bersamaan

---

## 🆘 Troubleshooting

### Device not found?
1. Cek USB Debugging enabled di HP
2. Accept "Allow USB debugging" permission
3. Gunakan kabel USB data (bukan charging only)

### No logs appearing?
1. Pastikan app sudah running
2. Pastikan Accessibility Service enabled
3. Coba `.\check-logs-fresh.ps1`

### ADB not recognized?
```powershell
# Cek ADB location
Get-ChildItem -Path "$env:LOCALAPPDATA\Android\Sdk\platform-tools" -Filter "adb.exe"
```

---

**📚 Untuk dokumentasi lengkap, baca [LOGS_GUIDE.md](LOGS_GUIDE.md)**
