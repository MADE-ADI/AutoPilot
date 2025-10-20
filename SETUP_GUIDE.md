# SETUP GUIDE - Auto Pilot Autoscroll App

## 🚀 Quick Start (5 Menit)

### Step 1: Build & Install
```powershell
# Di terminal PowerShell di folder project
./gradlew installDebug
```

### Step 2: Aktifkan Accessibility Service
1. Buka aplikasi **Auto Pilot** di device/emulator
2. Klik tombol **"Aktifkan Service"** (warna merah)
3. Akan terbuka **Accessibility Settings**
4. Cari dan tap "**Auto Pilot**" atau "**AutoscrollService**"
5. Tap toggle untuk mengaktifkan
6. Berikan semua permission yang diminta
7. Kembali ke aplikasi Auto Pilot

### Step 3: Gunakan Autoscroll
1. Di aplikasi Auto Pilot:
   - Adjust slider untuk kecepatan scroll (semakin kecil = semakin cepat)
   - Default 2000ms adalah rekomendasi untuk terasa natural
2. Klik **"Mulai Autoscroll"** (tombol hijau)
3. Buka aplikasi media sosial (Instagram, TikTok, Facebook, etc)
4. Aplikasi akan mulai scroll otomatis! 🎉
5. Klik **"Hentikan Autoscroll"** untuk berhenti

## 📊 Konfigurasi Delay

| Delay (ms) | Kecepatan | Use Case |
|-----------|-----------|----------|
| 500 - 1000 | Sangat Cepat | Testing, Demo |
| 1000 - 2000 | Normal | Media Sosial |
| 2000 - 3000 | Sedang | Browsing Santai |
| 3000 - 5000 | Lambat | Reading Content |

## 🎨 Fitur Aplikasi

### 1. Service Status Card
- Menampilkan status service (Aktif/Tidak)
- Tombol untuk mengaktifkan service
- Real-time status updates

### 2. Kontrol Autoscroll
- **Slider Kecepatan**: 500ms - 5000ms
- **Tombol Start/Stop**: Kontrol autoscroll
- **Live Status**: Menampilkan "Autoscroll Berjalan..."

### 3. Info Features
- Menampilkan fitur human-like yang digunakan
- Penjelasan behavior naturalnya

## 🔧 Human-Like Behavior Details

Aplikasi menggunakan multiple techniques untuk terasa seperti manusia:

```
1. Random Scroll Speed
   └─ 100-400 pixels per gesture
   └─ Berbeda setiap kali scroll

2. Variable Delays
   └─ Base delay ± 30% variation
   └─ Tidak fixed interval

3. Random Directions
   └─ 90% scroll down
   └─ 10% scroll up (kadang-kadang)

4. Natural Gesture Duration
   └─ 300-800ms per gesture
   └─ Simulasi actual swipe speed

5. Timing Variation
   └─ Tidak perfect rhythm
   └─ Terasa like real human
```

## 🐛 Debugging & Logcat

Untuk melihat logs, gunakan:
```powershell
# Real-time logs
adb logcat | findstr "AutoscrollService"

# Atau dengan Android Studio
# Device Explorer → Logcat
# Filter: "AutoscrollService"
```

## ❓ FAQ

### Q: Berapa delay optimal?
A: 2000ms (2 detik) paling natural, tapi sesuaikan dengan kebutuhan.

### Q: Bisa digunakan untuk aplikasi apa saja?
A: Aplikasi apapun yang support scroll gesture (Instagram, TikTok, Twitter, dll)

### Q: Apakah aman?
A: Ya, menggunakan official Android Accessibility API.

### Q: Bisa di-background?
A: Ya, service berjalan di background. Lock screen juga tidak masalah.

### Q: Konsumsi battery berapa?
A: Minimal, hanya gesture tanpa rendering berat.

## 📝 File Structure

```
AutoPilot/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── AndroidManifest.xml (+ permissions & service)
│   │       ├── java/com/example/autopilot/
│   │       │   ├── MainActivity.kt (UI utama)
│   │       │   ├── AutoscrollService.kt (Accessibility Service)
│   │       │   ├── AutoscrollServiceManager.kt (Manager)
│   │       │   └── AutoscrollServiceImpl.kt (Implementation)
│   │       └── res/
│   │           ├── values/strings.xml
│   │           └── xml/accessibility_service_config.xml
│   └── build.gradle.kts
└── README.md
```

## 🎯 Next Steps (Optional Enhancement)

Fitur yang bisa ditambahkan:
- [ ] Click detection & interaction
- [ ] App-specific profiles (Instagram, TikTok settings)
- [ ] Recording & playback
- [ ] Analytics
- [ ] Custom gesture builder
- [ ] Schedule automation

## 💡 Tips & Tricks

1. **Untuk Instagram**: Delay 1500-2000ms paling natural
2. **Untuk TikTok**: Delay 2000-3000ms (videos lebih panjang)
3. **Night Mode**: Slide ke bawah lebih lambat di malam hari
4. **Demo Mode**: Gunakan 800ms untuk presentasi cepat

## 🆘 Troubleshooting

### Service tidak aktif?
- Restart aplikasi
- Cek Accessibility Settings manually
- Restart device jika perlu

### Scroll tidak jalan?
- Pastikan service truly enabled
- Check logcat untuk error
- Coba aplikasi lain untuk test

### Lambat/Laggy?
- Tutup background apps
- Increase delay value
- Clear app cache

---
**Created with ❤️ using Kotlin & Jetpack Compose**
