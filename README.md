# Auto Pilot - Autoscroll untuk Media Sosial

Aplikasi Android sederhana untuk autoscroll dengan interaksi seperti manusia.

## 📋 Fitur

- ✓ **Autoscroll Otomatis** - Scroll halaman secara otomatis
- ✓ **Human-Like Behavior** - Gerakan yang terasa natural seperti manusia
  - Scroll dengan kecepatan random (100-400px)
  - Delay antar scroll bervariasi (±30%)
  - Durasi gesture natural (300-800ms)
  - Sebagian besar scroll ke bawah (90%), sesekali ke atas (10%)
- ✓ **Kontrol Mudah** - UI simple dengan slider untuk kecepatan
- ✓ **Accessibility Service** - Menggunakan system accessibility service

## 🔧 Persyaratan

- Android 13+ (API 33+)
- Android Studio versi terbaru

## 📱 Setup & Instalasi

### Langkah 1: Build Aplikasi
```bash
./gradlew build
```

### Langkah 2: Jalankan di Device
```bash
./gradlew installDebug
```

### Langkah 3: Setup Service
1. Buka aplikasi "Auto Pilot"
2. Klik tombol "Aktifkan Service"
3. Akan membuka Accessibility Settings
4. Cari "Auto Pilot" atau "AutoscrollService"
5. Tap dan aktifkan service
6. Berikan permission yang diminta

### Langkah 4: Gunakan Aplikasi
1. Kembali ke aplikasi Auto Pilot
2. Atur kecepatan scroll dengan slider (500ms - 5000ms)
3. Klik "Mulai Autoscroll" untuk memulai
4. Buka aplikasi media sosial (Instagram, TikTok, dll)
5. Aplikasi akan mulai scroll otomatis
6. Klik "Hentikan Autoscroll" untuk berhenti

## 🎮 Kontrol

- **Slider Delay**: Atur interval waktu antar scroll
  - Semakin kecil = semakin cepat scroll
  - Minimum: 500ms (0.5 detik)
  - Maximum: 5000ms (5 detik)
  - Default: 2000ms (2 detik)

- **Tombol Mulai/Hentikan**: Kontrol autoscroll

## 🔒 Permissions

Aplikasi memerlukan permissions berikut:
- `BIND_ACCESSIBILITY_SERVICE` - Untuk accessibility service
- `SYSTEM_ALERT_WINDOW` - Untuk system overlay (jika diperlukan)
- `INTERNET` - Untuk koneksi (standar)

## 📝 Catatan

- Service hanya bekerja jika sudah diaktifkan di Accessibility Settings
- Aplikasi perlu tetap berjalan di background
- Bisa digunakan pada berbagai aplikasi yang support scroll gesture
- Human-like behavior membuat scroll terasa natural

## 🛠️ Troubleshooting

### Service tidak muncul di Accessibility Settings
- Restart aplikasi
- Coba uninstall dan install ulang
- Restart device

### Scroll tidak bekerja
- Pastikan service sudah aktif
- Pastikan aplikasi target support gesture
- Check logcat untuk error messages

### Performa lambat
- Turunkan scroll speed (naikkan delay)
- Close aplikasi lain
- Restart device

## 📄 Lisensi

Proyek ini dibuat untuk kebutuhan edukasi dan development.

## 👨‍💻 Author

Dibuat dengan ❤️ menggunakan Kotlin dan Jetpack Compose
